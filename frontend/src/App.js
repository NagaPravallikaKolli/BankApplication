import './App.css';
import './index.css';
import Navbar from "./components/Navbar";
import Home from "./components/Home";
import RegisterCustomer from './components/RegisterCustomer';
import LoginCustomer from './components/LoginCustomer';
import { Route, Routes } from 'react-router-dom';
import CustomerPage from './components/CustomerPage';
import Deposit from './components/Deposit';
import Withdraw from './components/Withdraw';
import AddAccount from './components/AddAccount';

export default function App() {
  return (
     <div>
      <Navbar />
      <Routes>
        <Route exact path="/" element={<Home/>} />
        <Route path ="/home" element={<Home/>}/>
        <Route path="/register" element={<RegisterCustomer/>} />
        <Route path="/login" element={<LoginCustomer/>} />
        <Route path="/deposit" element={<Deposit/>} />
        <Route path="/withdraw" element={<Withdraw/>} /> 
        <Route path="/addaccount" element={<AddAccount/>} /> 
        {/* <Route path="/balance" element={<Balance/>} />       */}
        <Route path="/customerpage" element={<CustomerPage/>} />
      </Routes>
     </div>
  );
}
