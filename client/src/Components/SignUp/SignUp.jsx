import React, { useState } from "react";
import "./SignUp.css";
import UserServices from "../../Services/UserServices";
import {
  TextField,
  Stack,
  Button,
  RadioGroup,
  Radio,
  FormControlLabel,
  FormLabel,
} from "@mui/material";
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
  const [street, setStreet] = useState("");
  const [unit, setUnit] = useState("");
  const [city, setCity] = useState("");
  const [state, setState] = useState("");
  const [zipCode, setZipCode] = useState("");
  const [country, setCountry] = useState("");
  const [day, setDay] = useState("");
  const [month, setMonth] = useState("");
  const [year, setYear] = useState("");
  const [yearlyIncome, setYearlyIncome] = useState("");
  const [monthlyIncome, setMonthlyIncome] = useState("");
  const [personalDebt, setPersonalDebt] = useState("");

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
  const handleMiddleName = (e) => {
    setMiddleName(e.target.value);
  };
  const handleLastName = (e) => {
    setLastName(e.target.value);
  };
  const handleSocialSecurity = (e) => {
    setSocialSecurity(e.target.value);
  };
  const handleGender = (e) => {
    setGender(e.target.value);
  };
  const handleStreet = (e) => {
    setStreet(e.target.value);
  };
  const handleUnit = (e) => {
    setUnit(e.target.value);
  };
  const handleCity = (e) => {
    setCity(e.target.value);
  };
  const handleState = (e) => {
    setState(e.target.value);
  };
  const handleZipCode = (e) => {
    setZipCode(e.target.value);
  };
  const handleCountry = (e) => {
    setCountry(e.target.value);
  };
  const handleDay = (e) => {
    setDay(e.target.value);
  };
  const handleMonth = (e) => {
    setMonth(e.target.value);
  };
  const handleYear = (e) => {
    setYear(e.target.value);
  };
  const handleYearlyIncome = (e) => {
    setYearlyIncome(e.target.value);
  };
  const handleMonthlyIncome = (e) => {
    setMonthlyIncome(e.target.value);
  };
  const handlePersonalDebt = (e) => {
    setPersonalDebt(e.target.value);
  };

  const navToLandingPage = () => {
    navigate("/");
  };
  const calculateFico = () => {
    //(max - min + 1) + min
    return Math.floor(Math.random() * (850 - 350 + 1) + 350);
  };
  const registerUser = () => {
      if (password !== passwordConfirmation) {
        alert("Passwords do not match.");
      } else {
        UserServices.postUser({
          email: email,
          password: password,
          name: {
            first: firstName,
            middle: middleName,
            last: lastName,
          },
          socialSecurity: socialSecurity, 
          gender: gender, 
          address: {
            street: street,
            unit: unit,
            city: city,
            state: state,
            zipCode: zipCode.toString(),
            country: country,
          }, 
          birthday: {
            day: day.toString(),
            month: month.toString(),
            year: year.toString(),
          }, 
          yearlyIncome: yearlyIncome.toString(), 
          monthlyIncome: monthlyIncome.toString(), 
          personalDebt: personalDebt.toString(), 
          ficoScore: calculateFico().toString(), 
        })
          .then((res) => {
            console.log(res.data);
            navigate(`/`);
          })
          .catch((err) => {
            console.log(err.message);
          });
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
          border="1px solid green"
          width={"75%"}
        >
          <h1 className="text">GoBank</h1>
          <h3 className="text">Registration</h3>
          <div>
            <TextField
              label="First Name"
              variant="outlined"
              onChange={handleFirstName}
            />
            <TextField
              label="Middle Name (Optional)"
              variant="outlined"
              onChange={handleMiddleName}
            />
            <TextField
              label="Last Name"
              variant="outlined"
              onChange={handleLastName}
            />
          </div>
          <div>
            <TextField
              label="Email"
              variant="outlined"
              type="email"
              onChange={handleEmail}
            />
          </div>
          <div>
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
          </div>
          <TextField
            label="Social Security"
            variant="outlined"
            type="password"
            onChange={handleSocialSecurity}
          />
          <FormLabel>Gender</FormLabel>
          <RadioGroup row value={gender} onChange={handleGender}>
            <FormControlLabel value="Male" control={<Radio />} label="Male" />
            <FormControlLabel
              value="Female"
              control={<Radio />}
              label="Female"
            />
            <FormControlLabel
              value="Not Specified"
              control={<Radio />}
              label="Prefer Not to Say"
            />
          </RadioGroup>
          <div>
            <h3 className="text">ADDRESS</h3>
            <TextField
              label="Street"
              variant="outlined"
              onChange={handleStreet}
            />
            <TextField label="Unit" variant="outlined" onChange={handleUnit} />
            <TextField label="city" variant="outlined" onChange={handleCity} />
            <TextField
              label="State"
              variant="outlined"
              onChange={handleState}
            />
            <TextField
              label="ZipCode"
              variant="outlined"
              type="number"
              onChange={handleZipCode}
            />
            <TextField
              label="Country"
              variant="outlined"
              onChange={handleCountry}
            />
          </div>
          <div>
            <h3 className="text"> BIRTHDAY</h3>
            <TextField
              label="Day(DD)"
              variant="outlined"
              type="number"
              onChange={handleDay}
            />
            <TextField
              label="Month(MM)"
              variant="outlined"
              type="number"
              onChange={handleMonth}
            />
            <TextField
              label="Year(YYYY)"
              variant="outlined"
              type="number"
              onChange={handleYear}
            />
          </div>
          <TextField
            label="Yearly Income"
            variant="outlined"
            type="number"
            onChange={handleYearlyIncome}
          />
          <TextField
            label="Monthly Income"
            variant="outlined"
            type="number"
            onChange={handleMonthlyIncome}
          />
          <TextField
            label="Personal Debt"
            variant="outlined"
            type="number"
            onChange={handlePersonalDebt}
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
