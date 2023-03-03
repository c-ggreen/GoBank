import React, { useState, useContext, useEffect } from "react";
import "./AccountView.css";
import { UserContext } from "../../Contexts/UserContext";
import AccountService from "../../Services/AccountService";
import TransactionService from "../../Services/TransactionService";
import { useNavigate } from "react-router-dom";
import {
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogContentText,
  TextField,
  DialogActions,
} from "@mui/material";
import Transaction from "../Transaction/Transaction";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";


function AccountView(props) {
  let navigate = useNavigate();
  const { accountID } = useContext(UserContext);
  const [account, setAccount] = useState({});
  const [action, setAction] = useState("");
  const [amount, setAmount] = useState();
  const [transferAccount, setTransferAccount] = useState();
  const w = "Withdraw";
  const d = "Deposit";
  const t = "Transfer";

  const getAccountOnPageLoad = () => {
    AccountService.getAccountById(accountID).then((res) => {
      setAccount(res.data);
    });
  };
  const navToHomepage = () => {
    navigate("/homepage");
  };

  const handleAmount = (e) => {
    setAmount(e.target.value);
  };
  const handleTransferAccount = (e) => {
    setTransferAccount(e.target.value);
  };

  const executeAction = (action, amount) => {
    if (action === "Withdraw" && amount <= account.balance) {
      account.balance -= parseInt(amount);
      account.transactions.push({ description: action, amount: amount });
      AccountService.patchAccount(account).then((res) => {
        console.log(res.data);
        getAccountOnPageLoad();
      });
    } else if (action === "Deposit") {
      account.balance += parseInt(amount);
      account.transactions.push({ description: action, amount: amount });
      AccountService.patchAccount(account).then((res) => {
        console.log(res.data);
        getAccountOnPageLoad();
      });
    } else if (action === "Transfer" && amount <= account.balance) {
      account.balance -= parseInt(amount);
      account.transactions.push({ description: `${action} to ACC# ${transferAccount}`, amount: amount });
      AccountService.patchAccount(account).then((res) => {
        console.log(res.data);
        getAccountOnPageLoad();
      });
      AccountService.getAccountById(transferAccount).then((res)=>{
        let transferAccountObject = res.data
        transferAccountObject.balance += parseInt(amount)
        transferAccountObject.transactions.push({description:`${action} from ACC# ${accountID}`, amount:amount})
        AccountService.patchAccount(transferAccountObject)
      })
    }
    else{
      alert("Insufficient Funds.")
    }
  };

  useEffect(() => {
    getAccountOnPageLoad();
  }, []);

  // For Deposit and Withdraw
  const [open, setOpen] = useState(false);

  const handleClickOpen = (action) => {
    setOpen(true);
    setAction(action);
  };

  const handleClose = () => {
    setOpen(false);
    setAction("")
  };

  // For Transfer
  const [openTransfer, setOpenTransfer] = useState(false);
  const handleTransferOpen = (action) => {
    setOpenTransfer(true);
    setAction(action);
  };
  const handleTransferClose = () => {
    setOpenTransfer(false);
    setAction("")
  };

  return (
    <div className="accountViewBlock">
      <div className="backButton">
        <Button  color="error" onClick={navToHomepage}>
          <ArrowBackIcon/>
        </Button>
      </div>
      <div className="accountViewHeader">
        <h2 className="text">{`#${account.id}`}</h2>
        <h2 className="text">{`${account.name}`}</h2>
        <h2 className="text">{`$${account.balance}`}</h2>
      </div>

      {/* Dialog Stuff */}
      <div className="actionButtons">
        <Button variant="outlined" color="inherit" onClick={() => handleClickOpen(w)}>
          Withdraw
        </Button>
        <Button variant="outlined" color="inherit" onClick={() => handleClickOpen(d)}>
          Deposit
        </Button>
        <Button variant="outlined" color="inherit" onClick={() => handleTransferOpen(t)}>
          Transfer
        </Button>
      </div>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>{action}</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Amount"
            type="number"
            fullWidth
            variant="standard"
            className="input"
            onChange={handleAmount}
            />
            <DialogContentText>Enter amount.</DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button color="error" onClick={handleClose}>Cancel</Button>
          <Button
          color="inherit"
            onClick={() => {
              executeAction(action, amount);
              handleClose();
            }}
          >
            {action}
          </Button>
        </DialogActions>
      </Dialog>

      <Dialog open={openTransfer} onClose={handleTransferClose}>
        <DialogTitle>{action}</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            label="Receiving Account"
            type="number"
            fullWidth
            variant="standard"
            className="input"
            onChange={handleTransferAccount}
            />
            <DialogContentText>Enter account.</DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            label="Amount"
            type="number"
            fullWidth
            variant="standard"
            className="input"
            onChange={handleAmount}
            />
            <DialogContentText>Enter amount.</DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button color="error" onClick={handleTransferClose}>Cancel</Button>
          <Button
          color="inherit"
            onClick={() => {
              executeAction(action, amount);
              handleTransferClose();
            }}
          >
            {action}
          </Button>
        </DialogActions>
      </Dialog>

      <h2 className="text">Transactions</h2>
      <div className="transactionBlock">
        {account.transactions?.map((transaction, i) => {
          return (
            <Transaction
              key={i}
              id={transaction.id}
              description={transaction.description}
              amount={transaction.amount}
            />
          );
        })}
      </div>
    </div>
  );
}

// Should show Account #, Name, Balance, and Transactions
// Should be able to Withdraw, Deposit, Transfer money, and Delete Account

export default AccountView;
