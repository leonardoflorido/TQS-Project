import React, { useState, useEffect } from "react";
import axios from "axios";
import "./shipping.css";
import { setSelectionRange } from "@testing-library/user-event/dist/utils";

export const ShippingForm = ({setSelectedTab}) => {
  const [deliveryOption, setDeliveryOption] = useState("pickup");
  const [pickupPoints, setPickupPoints] = useState([]);

  useEffect(() => {
    if (deliveryOption === "pickup") {
      fetchPickupPoints();
    }
  }, [deliveryOption]);

  const fetchPickupPoints = async () => {
    try {
      const response = await axios.get("http://localhost:8080/pickup/get-all");
      setPickupPoints(response.data);
    } catch (error) {
      console.error("Error fetching pickup points:", error);
    }
  };
  const handleTabChange = () => {
    setSelectedTab("tab3");
    };

  return (
    <div className="shipping-container">
      <form className="shipping-form">
        <div>
          <input
            type="radio"
            id="pickup"
            name="delivery"
            value="pickup"
            checked={deliveryOption === "pickup"}
            onChange={() => setDeliveryOption("pickup")}
          />
          <label
            htmlFor="pickup"
            className={
              deliveryOption === "pickup" ? "active" : "shipping-label"
            }
          >
            Pickup
          </label>
        </div>
        <div>
          <input
            type="radio"
            id="standard"
            name="delivery"
            value="standard"
            checked={deliveryOption === "standard"}
            onChange={() => setDeliveryOption("standard")}
          />
          <label
            htmlFor="standard"
            className={
              deliveryOption === "standard" ? "active" : "shipping-label"
            }
          >
            Standard Delivery
          </label>
        </div>
        {deliveryOption === "standard" && (
          <div className="shipping-information">
            <h3>Shipping Information</h3>
            <input type="text" placeholder="Full Name" />
            <input type="text" placeholder="Address" />
            <input type="text" placeholder="City" />
            <input type="text" placeholder="State" />
            <input type="text" placeholder="Zip Code" />
          </div>
        )}
        {deliveryOption === "pickup" && pickupPoints.length > 0 && (
          <div className="pickup-points">
            <h3>Pickup Points</h3>
            <select>
              {pickupPoints.map((pickupPoint) => (
                <option key={pickupPoint.id}>{pickupPoint.name}</option>
              ))}
            </select>
          </div>
        )}
      </form>
    </div>
  );
};
