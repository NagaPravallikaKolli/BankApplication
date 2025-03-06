import React, {useState} from 'react'
import { useNavigate } from "react-router-dom";

export default function LoginCustomer(){
    const navigate = useNavigate();
    const [loginData, setLoginData]= useState({email:'', cust_pass:''})
    const [success, setSuccess]=useState("");
    const [error, setError]=useState("");
    
    function handleChange(event){
       const {name, value} = event.target 
       setLoginData(prevLoginData => ({
        ...prevLoginData,
        [name]: value,
      }))
    }
    const handleSubmit = async (event) => {
        event.preventDefault();
        setSuccess("");
        setError("");
        console.log("Submitting login data:", loginData);
    try {
        const response = await fetch("http://localhost:8080/customerlogin/login", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(loginData),
        });
        const responseData = await response.text();
        if (!response.ok) {
            setError(responseData);
        }else{
          setSuccess(responseData);
          //setLoginData({email: "", cust_pass: ""});
        }
        setTimeout(()=>{navigate("/customerpage");},1000);
    }catch(error){
        console.error("Error:", error);
        alert("Failed to login. Please try again.");
      }
    }
    return (
      <div>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="Email"
          onChange={handleChange}
          name="email"
          value={loginData.email}
          required
        />
        <input
          type="password"
          placeholder="Password"
          onChange={handleChange}
          name="cust_pass"
          value={loginData.cust_pass}
          required
        />
        <button>Submit</button>
      </form> 
      {error && <h3 style={{ color: "red" }}>{error}</h3>} 
      {success && <h3 style={{ color: "green" }}>{success}</h3>}
    </div> 
    );
}