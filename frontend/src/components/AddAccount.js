import React, {useState} from 'react';
import { useNavigate } from "react-router-dom";

export default function AddAccount(){
  const navigate = useNavigate();
  const [formData, setFormData]= useState({custid:'', balance:'', acc_type:''})
  const [success, setSuccess]=useState("");
  const [error, setError]=useState("");

  function handleChange(event) {
    const {name, value} = event.target
    setFormData(prevFormData => ({
      ...prevFormData,
      [name]: value
    }));
} 
const handleSubmit = async (event) => {
  event.preventDefault();
  setSuccess("");
  setError("");
  console.log("Submitting:", formData);
                                        
  try {
    console.log(JSON.stringify(formData))
    const response = await fetch("http://localhost:8080/account/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    });

    if (!response.ok) {
        setError("Failed to add account. Please try again.");
    }else{
        setSuccess("Account added successfully!!");
      }
    setTimeout(()=>{navigate("/customerpage");},1000);

  } catch (error) {
    console.error("Error:", error);
    alert("Failed to add account. Please try again.");
  }
};

  return (
    <div>
    <form onSubmit={handleSubmit}>
      <input
            type="number"
            placeholder="Enter your ID (Customer ID)"
            onChange={handleChange}
            name = "custid"
            value={formData.custid}
            required
      />
      <input
           type="number"
           placeholder="Enter your balance"
           onChange={handleChange}
           name = "balance"
           value={formData.balance}
           required
      />
      <input
           type="text"
           placeholder="Enter account type"
           onChange={handleChange}
           name="acc_type"
           value={formData.acc_type}
           required
      />
      <br />
      <button>Submit</button>
    </form>
    {error && <h3 style={{ color: "red" }}>{error}</h3>} 
    {success && <h3 style={{ color: "green" }}>{success}</h3>}
    </div>
  );
}