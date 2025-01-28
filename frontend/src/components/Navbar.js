import React from 'react';
import {Link} from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="bg-blue-500 p-4 text-white">
      <ul className="flex justify-around">
        <li><Link to="/" className="hover:underline">Home</Link></li>
        <li><Link to="/register" className="hover:underline">Register</Link></li>
        <li><Link to="/login" className="hover:underline">Login</Link></li>
        {/* <li><Link to="/account-details" className="hover:underline">Account Details</Link></li>
        <li><Link to="/transaction" className="hover:underline">Transactions</Link></li> */}
      </ul>
    </nav>
  );
};

export default Navbar;
