import React, {useEffect, useState} from "react";
import axios from "axios";
import "./shipping.css";

export const ShippingForm = ({setSelectedTab, setPickupId}) => {
    const [deliveryOption, setDeliveryOption] = useState("pickup");
    const [pickupPoints, setPickupPoints] = useState([]);
    const [selectedPickupPoint, setSelectedPickupPoint] = useState(null);

    useEffect(() => {
        if (deliveryOption === "pickup") {
            fetchPickupPoints();
        }
    }, [deliveryOption]);

    const fetchPickupPoints = async () => {
        try {
            const response = await fetch("http://34.175.80.212:8080/pickup/get-partners");
            
            setPickupPoints(response.data);
        } catch (error) {
            console.error("Error fetching pickup points:", error);
        }
    };

    const handleTabChange = () => {
        setSelectedTab("tab3");
    };

    const handlePickupPointChange = (event) => {
        const selectedPointId = event.target.value;
        const selectedPoint = pickupPoints.find(
            (point) => point.id === selectedPointId
        );
        setSelectedPickupPoint(selectedPoint);
        setPickupId(selectedPointId); // Update the pickupId state in Checkout
    };

    const handleContinue = () => {
        setSelectedTab("tab4"); // Set selected tab to tab4
    };

    return (
        <div className="shipping-container">
            <form className="shipping-form">
                {/* Radio buttons for delivery options */}
                {/* Shipping information inputs */}
                {deliveryOption === "pickup" && pickupPoints.length > 0 && (
                    <div className="pickup-points">
                        <h3>Pickup Points</h3>
                        {/* Render pickup points in a dropdown */}
                        <select onChange={handlePickupPointChange}>
                            <option value="">Select Pickup Point</option>
                            {pickupPoints.map((pickupPoint) => (
                                <option key={pickupPoint.id} value={pickupPoint.id}>
                                    {pickupPoint.name}
                                </option>
                            ))}
                        </select>
                        {selectedPickupPoint && (
                            <div className="pickup-address">
                                <h4>Address: {selectedPickupPoint.address}</h4>
                            </div>
                        )}
                    </div>
                )}
                {/* Add other shipping options and form inputs */}
                <div className="button-master-container">
                    <div
                        className="button-container button-continue"
                        onClick={handleContinue}
                    >
                        Continue
                    </div>
                </div>
            </form>
        </div>
    );
};
