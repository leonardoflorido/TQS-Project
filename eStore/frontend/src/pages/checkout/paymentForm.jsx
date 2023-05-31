import React, { useState } from "react";
import "./paymentForm.css"; // Import the CSS file

export const PaymentForm = () => {
  const [cardName, setCardName] = useState("");
  const [cardNumber, setCardNumber] = useState("");
  const [csv, setCsv] = useState("");
  const [expirationDate, setExpirationDate] = useState("");

  return (
    <div className="payment-form">
      <h3>Payment Information</h3>
      <input
        type="text"
        placeholder="Cardholder Name"
        value={cardName}
        onChange={(e) => setCardName(e.target.value)}
      />
      <input
        type="text"
        placeholder="Card Number"
        value={cardNumber}
        onChange={(e) => setCardNumber(e.target.value)}
      />
      <input
        type="text"
        placeholder="CSV"
        value={csv}
        onChange={(e) => setCsv(e.target.value)}
      />
      <input
        type="text"
        placeholder="Expiration Date"
        value={expirationDate}
        onChange={(e) => setExpirationDate(e.target.value)}
      />
      <div className="logo-container">
        <img src="/mastercard.png" alt="Mastercard Logo" className="logo" />
      </div>
    </div>
  );
};


