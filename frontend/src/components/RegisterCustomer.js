import React, {useState} from 'react';
import { useNavigate } from "react-router-dom";

export default function RegisterCustomer(){
  const navigate = useNavigate();
  //console.log("RegisterCustomer component rendered!");
  const [formData, setFormData]= useState({custname:'', email:'', cust_pass:'', cust_address:'', cust_phno:''})

  function handleChange(event) {
    const {name, value} = event.target
    setFormData(prevFormData => ({
      ...prevFormData,
      [name]: value
    }));
} 
const handleSubmit = async (event) => {
  event.preventDefault();
  console.log("Submitting:", formData);
                                        
  try {
    console.log(JSON.stringify(formData))
    const response = await fetch("http://localhost:8080/customerregister/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    });

    if (!response.ok) {
      throw new Error("Registration failed.");
    }

    const responseData = await response.json();
    console.log("Customer registered successfully:", responseData);
    alert("Registration successful!");
    setTimeout(()=>{navigate("/login");},1000);

  } catch (error) {
    console.error("Error:", error);
    alert("Failed to register. Please try again.");
  }
};

  return (
    <div>
    <form onSubmit={handleSubmit}>
      <input
            type="text"
            placeholder="Full Name"
            onChange={handleChange}
            name="custname"
            value={formData.custname}
            required
      />
      <input
           type="email"
           placeholder="Email"
           onChange={handleChange}
           name="email"
           value={formData.email}
           required
      />
      <input
           type="password"
           placeholder="Password"
           onChange={handleChange}
           name="cust_pass"
           value={formData.cust_pass}
           required
      />
      <textarea 
           value={formData.cust_address}
           placeholder="Address"
           onChange={handleChange}
           name="cust_address"
      />
      <input
           type="text"
           placeholder="Contact"
           onChange={handleChange}
           name="cust_phno"
           value={formData.cust_phno}
           required
      />
      <br />
      <button>Submit</button>
    </form>
    </div>
  );
}