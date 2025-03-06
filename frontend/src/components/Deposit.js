import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function Deposit(){
    const navigate = useNavigate();
    const [formData, setFormData]= useState({accid:" ",amount:" "});
    const [success, setSuccess]=useState("");
    const [error, setError]=useState("");
    function handleChange(event) {
        const {name, value} = event.target
        setFormData(prevFormData => ({
          ...prevFormData,
          [name]: value
        }));
    } 

    const handleDeposit = async (event) => {
        event.preventDefault();
        setSuccess("");
        setError("");
                                              
        try {
          console.log(JSON.stringify(formData))
          const response = await fetch(`http://localhost:8080/account/deposit/${formData.accid}`, {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({amount: parseFloat(formData.amount)}),
          });

          if (!response.ok) {
            setError("Failed to deposit. Please try again.");
          }else{
            setSuccess("Amount Deposited successfully!!");
          }
      
          setTimeout(()=>{navigate("/customerpage");},1000);
      
        } catch (error) {
          console.error("Error:", error);
          alert("Failed to deposit. Please try again!");
        }
      };

    return (
        <div>
            <form onSubmit={handleDeposit}>
            <h2>Deposit Amount</h2>
            <input
                type="number"
                placeholder="Enter Account ID"
                onChange={handleChange}
                name = "accid"
                value={formData.accid}
                required
            />
            <input
                type="number"
                placeholder="Enter Deposit Amount"
                onChange={handleChange}
                name="amount"
                value={formData.amount}
                required
            />
            <br />
            <button>Deposit</button>
            </form>
            {error && <h3 style={{ color: "red" }}>{error}</h3>} 
            {success && <h3 style={{ color: "green" }}>{success}</h3>}
        </div>
    );
};

