import React, { useState } from "react";
import "./SignUp.css";
import UserServices from "../../Services/UserServices";
import { TextField, Stack, Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";

function SignUp(props) {
  let navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [passwordConfirmation, setPasswordConfirmation] = useState("");
  const [firstName, setFirstName] = useState("");
  const [middleName, setMiddleName] = useState("");
  const [lastName, setLastName] = useState("");
  const [socialSecurity, setSocialSecurity] = useState("");
  const [gender, setGender] = useState("");
  const [address, setAddress] = useState({});
  const [birthday, setBirthday] = useState({});
  const [yearlyIncome, setYearlyIncome] = useState("");
  const [monthlyIncome, setMonthlyIncome] = useState("");
  const [personalDebt, setPersonalDebt] = useState("");
  const [ficoScore, setFicoScore] = useState("");

  const handleEmail = (e) => {
    setEmail(e.target.value);
  };
  const handlePassword = (e) => {
    setPassword(e.target.value);
  };
  const handlePasswordConfirmation = (e) => {
    setPasswordConfirmation(e.target.value);
  };
  const handleFirstName = (e) => {
    setFirstName(e.target.value);
  };
  const handleLastName = (e) => {
    setLastName(e.target.value);
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
          middleName: "Carter", //
          lastName: lastName,
          socialSecurity: "123456789", //
          gender: "Male", //
          address: {
            street: "1st Street",
            unit: "1A",
            city: "New York",
            state: "New York",
            zipCode: "10001",
            country: "USA",
          }, //
          birthdate: {
            day: "1",
            month: "1",
            year: "2000",
          }, //
          yearlyIncome: "125000", //
          monthlyIncome: "7500", //
          personalDebt: "20000", //
          ficoScore: "650", //
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
