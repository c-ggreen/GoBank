import React, { useState, useContext } from "react";
import { UserContext } from "../../Contexts/UserContext";
import "./LandingPage.css";
import UserServices from "../../Services/UserServices";
import { TextField, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import goBankImage from "./gobank.png";
import DoubleArrowIcon from "@mui/icons-material/DoubleArrow";

function LandingPage(props) {
  const { setEmail } = useContext(UserContext);
  const { email } = useContext(UserContext);
  let navigate = useNavigate();
  const [password, setPassword] = useState("");

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };
  const handlePassword = (e) => {
    setPassword(e.target.value);
  };
  const navToSignUp = () => {
    navigate("/signup");
  };

  const login = () => {
    UserServices.getUserByEmail(email)
      .then((res) => {
        console.log("Data: " + JSON.stringify(res));
        if (res.data.password === password) {
          navigate(`/homepage`);
          console.log("Navigate to homepage is working...");
        } else {
          alert("Wrong Password.");
        }
      })
      .catch((err) => {
        alert(`${err.message}. User may not exist.`);
      });
  };

  return (
    <div className="page">
      <div className="landingImage">
        <img src={goBankImage} alt="" width="544" height="459" />
      </div>
      <div className="header text">
        <h1 className="headerTitle">GoBank</h1>
        <Button
          variant="contained"
          color="inherit"
          onClick={navToSignUp}
          style={{ height: "50%", textDecoration: "none" }}
        >
          Sign Up
        </Button>
      </div>
      <div className="subHeader text">
        <p>"Sign up for a free account today!"</p>
      </div>
      <div className="signInBlock">
        <div className="inputBlocks">
          <TextField
            className="loginCredentials"
            label="Email"
            variant="outlined"
            type={"email"}
            onChange={handleEmail}
          />
          <TextField
            className="loginCredentials"
            label="Password"
            variant="outlined"
            type={"password"}
            onChange={handlePassword}
          />
        </div>

        <div className="buttons">
          <Button
            variant="contained"
            color="success"
            onClick={login}
            style={{ width: "20%" }}
          >
            <DoubleArrowIcon />
          </Button>
        </div>
      </div>
    </div>
  );
}

export default LandingPage;
