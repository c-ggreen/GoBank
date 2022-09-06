import React, { useState, useEffect, useContext } from "react";
import { UserContext } from "../../Contexts/UserContext";
import UserServices from "../../Services/UserServices";
import "./HomePage.css";
import { Button } from "@mui/material";
import Account from "../Account/Account";
import { useNavigate } from "react-router-dom";
import LogoutIcon from '@mui/icons-material/Logout';

function HomePage(props) {
  let navigate = useNavigate();
  const { setEmail } = useContext(UserContext);
  const { email } = useContext(UserContext);
  const [user, setUser] = useState();

  const fetchData = () => {
    UserServices.getUserById(email).then((res) => {
      const data = res.data;
      console.log(data);
      setUser(data);
    });
  };

  useEffect(() => {
    fetchData();
    console.log(email);
  }, []);

  const navToAddAccount = () => {
    navigate("/addaccount");
  };

  const navToLandingPage = () => {
    navigate("/");
    setEmail("");
  };

  return (
    <div>
      <div className="homeHeader">
        <h1 className="text">{user?.firstName + " " + user?.lastName}</h1>
        <Button
          className="signOutButton"
          color="error"
          onClick={navToLandingPage}
        >
          <LogoutIcon/>
        </Button>
      </div>
      {/* <button onClick={fetchData}>Fetch again</button> */}
      <div className="homeAccounts">
        <div className="accountsHeader">
          <h3 className="text">Accounts</h3>
          <Button color="inherit" onClick={navToAddAccount}>Add Account</Button>
        </div>
        {user?.accounts?.map((account, i) => {
          return (
            <Account
              key={i}
              id={account.id}
              name={account.name}
              balance={account.balance}
            />
          );
        })}
      </div>
    </div>
  );
}

export default HomePage;
