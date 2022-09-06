import React, { useState } from "react";
import "./SignUp.css";
import UserServices from "../../Services/UserServices";
import { TextField, Stack, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";

function SignUp(props) {
  let navigate = useNavigate();
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirmation, setPasswordConfirmation] = useState("");

  const handleFirstName = (e) => {
    setFirstName(e.target.value);
  };
  const handleLastName = (e) => {
    setLastName(e.target.value);
  };
  const handleEmail = (e) => {
    setEmail(e.target.value);
  };
  const handlePassword = (e) => {
    setPassword(e.target.value);
  };
  const handlePasswordConfirmation = (e) => {
    setPasswordConfirmation(e.target.value);
  };
  const navToLandingPage = () => {
    navigate("/");
  };

  const registerUser = () => {
    try {
      if (password !== passwordConfirmation) {
        alert("Passwords do not match.");
      } else {
        UserServices.postUser({
          email: email,
          password: password,
          firstName: firstName,
          lastName: lastName,
        })
          .then((res) => {
            console.log(res.data);
            navigate(`/`);
          })
          .catch((err) => {
            console.log(err.message);
          });
      }
    } catch (err) {
      console.log(err);
    }
  };

  return (
    <div className="containerBlock">
      <div className="cancelButton">
        <Button color="error" onClick={navToLandingPage}>
          <ArrowBackIcon />
        </Button>
      </div>
      <div className="signUpBlock">
        <Stack
          direction="column"
          justifyContent="center"
          alignItems="center"
          spacing={1}
          // border="1px solid blue"
          width={"40%"}
        >
          <h1 className="text">GoBank</h1>
          <h3 className="text">Registration</h3>
          <TextField
            label="First Name"
            variant="outlined"
            onChange={handleFirstName}
          />
          <TextField
            label="Last Name"
            variant="outlined"
            onChange={handleLastName}
          />
          <TextField
            label="Email"
            variant="outlined"
            type="email"
            onChange={handleEmail}
          />
          <TextField
            label="Password"
            variant="outlined"
            type="password"
            onChange={handlePassword}
          />
          <TextField
            label="Re-Enter Password"
            variant="outlined"
            type="password"
            onChange={handlePasswordConfirmation}
          />
          <Button variant="contained" color="inherit" onClick={registerUser}>
            Register
          </Button>
        </Stack>
      </div>
    </div>
  );
}

export default SignUp;
