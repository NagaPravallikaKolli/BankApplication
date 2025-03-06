import React from "react"
import { Link } from 'react-router-dom';

export default function CustomerPage(){
  return (
    <div>
    <nav className="cust-page">
      <h3><Link to="/addaccount"><button className="btn">Add an account</button></Link></h3>
      <h3><Link to="/deposit"><button className="btn">Deposit amount</button></Link></h3>
      <h3><Link to="/withdraw"><button className="btn">Withdraw amount</button></Link></h3>
      <h3><Link to="/balance"><button className="btn">View your current balance</button></Link></h3>
    </nav> 
    </div>   
  )
}