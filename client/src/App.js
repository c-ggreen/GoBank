import "./App.css"
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LandingPage from "./Components/LandingPage/LandingPage";
import SignUp from "./Components/SignUp/SignUp";
import HomePage from "./Components/HomePage/HomePage";
import { useState } from "react";
import { UserContext } from "./Contexts/UserContext";
import AddAccount from "./Components/AddAccount/AddAccount";
import AccountView from "./Components/AccountView/AccountView";
function App() {
  const [email, setEmail] = useState("");
  const [accountID, setAccountID] = useState();

  return (
    <UserContext.Provider value={{email, setEmail, accountID, setAccountID}}>
      <Router>
        <Routes>
          <Route path="/" element={<LandingPage />} />
          <Route path="/signup" element={<SignUp />} />
          <Route path="/homepage" element={<HomePage />} />
          <Route path="/addaccount" element={<AddAccount/>} />
          <Route path="/accountview" element={<AccountView/>} />
        </Routes>
      </Router>
    </UserContext.Provider>
  );
}

export default App;
