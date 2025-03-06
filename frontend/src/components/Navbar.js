import React from 'react';
import { Link } from 'react-router-dom';

export default function Navbar(){
  return (
    <div>
    <nav className="nav-bar">
      <h2 className = "welcome">Welcome to AtYourService Bank</h2>
      <h3><Link to="/home"><button className="btn">Home</button></Link></h3>
      <h3><Link to="/register"><button className="btn">Register</button></Link></h3>
      <h3><Link to="/login"><button className="btn">Login</button></Link></h3>
    </nav> 
    </div>   
  )
}
