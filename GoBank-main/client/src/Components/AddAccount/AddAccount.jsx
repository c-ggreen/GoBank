import React, { useState, useContext, useEffect } from "react";
import "./AddAccount.css";
import { Button, TextField } from "@mui/material/";
import UserServices from "../../Services/UserServices";
import { useNavigate } from "react-router-dom";
import { UserContext } from "../../Contexts/UserContext";

function AddAccount() {
  let navigate = useNavigate();
  const { email } = useContext(UserContext);
  const [name, setName] = useState("");
  const [balance, setBalance] = useState(0);
  const [user, setUser] = useState();

  const handleName = (e) => {
    setName(e.target.value);
  };
  const handleBalance = (e) => {
    setBalance(e.target.value);
  };

  const navToHomepage = () => {
    navigate("/homepage");
  };

  // If a person is going to the "Add Account" page then getting and storing the user info in state is necessary
  const getUserOnPageLoad = () => {
    UserServices.getUserById(email).then((res) => {
      setUser(res.data);
      console.log(res.data);
    });
  };

  const submit = (name, balance) => {
    // Conditions
    // I'm going to patch the user
    // I'm going to need user creds, first+last name, password, email, and all previous accounts
    // I need to patch the ID's of old accounts so their id #'s dont update
    // I need to push the new account object onto the user account array and send that to the DB

    // Creating a new account object with name and balance fields
    // Don't need account ID as it will autogenerate in DB
    const newAccountObject = { name: name, balance: balance };

    // Pushing the new account object onto the accounts array currently in state that was pulled from the DB on page load
    user?.accounts?.push(newAccountObject);

    // Pushing the altered User object with the new account into the patch call, then navigating back to homepage
    UserServices.patchUser(user)
      .then(() => {
        navToHomepage();
      })
      .catch((e) => {
        console.log(e);
      });
  };

  useEffect(() => {
    getUserOnPageLoad();
  }, []);

  return (
    <div className="addAccountBlock">
      <div className="addAccountHeader">
        <h1 className="text">New Account</h1>
      </div>
      <div className="addAccountInputs">
        <TextField
          autoFocus
          margin="dense"
          id="name"
          label="Nickname"
          type="email"
          fullWidth
          variant="standard"
          onChange={handleName}
        />
        <TextField
          autoFocus
          margin="dense"
          id="name"
          label="Initial Amount"
          type="number"
          fullWidth
          variant="standard"
          onChange={handleBalance}
        />
      </div>
      <div className="addAccountButtons">
        <Button color="error" onClick={navToHomepage}>Cancel</Button>
        <Button color="inherit" onClick={() => submit(name, balance)}>Submit</Button>
      </div>
    </div>
  );
}

export default AddAccount;
