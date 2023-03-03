import React, {useContext} from "react";
import "./Account.css";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import { UserContext } from "../../Contexts/UserContext";
import VisibilityIcon from '@mui/icons-material/Visibility';


function Account({ id, name, balance }) {
  let navigate = useNavigate();
  const {accountID} = useContext(UserContext)
  const {setAccountID} = useContext(UserContext)

  const navToAccountView = () => {
    navigate("/accountview");
  };

  return (
    <div className="container">
      <p>{`Acc. #: ${id}`}</p>
      <p>{name}</p>
      <p>{`$${balance}`}</p>
      <Button
      color="inherit"
        onClick={() => {
          setAccountID(id)
          navToAccountView();
        }}
      >
        <VisibilityIcon/>
        
      </Button>
    </div>
  );
}

export default Account;

// Account Name
// Account Balance
// Buttons to Withdraw/Deposit
// Should be able to see a list of transactions
