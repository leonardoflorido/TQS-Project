import React, {useState} from "react";
import axios from "axios";
import "./CheckOrders.css";

export const CheckOrders = () => {
    const [email, setEmail] = useState("");
    const [orderInfo, setOrderInfo] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.get(`http://34.175.95.229:8080/orders/get-by-customer/${email}`);
            setOrderInfo(response.data);
            setErrorMessage("");
        } catch (error) {
            console.error("Error retrieving orders:", error);
            setOrderInfo([]);
            setErrorMessage("No orders found for the provided email.");
        }
    };

    const calculateTotalPrice = (products) => {
        let totalPrice = 0;
        products.forEach((product) => {
            totalPrice += product.price * product.quantity;
        });
        return totalPrice.toFixed(2);
    };

    const formatDate = (dateString) => {
        const options = {year: "numeric", month: "long", day: "numeric"};
        return new Date(dateString).toLocaleDateString(undefined, options);
    };

    return (
        <div className="check-orders-container">
            <h1>Check Orders</h1>
            <form onSubmit={handleSubmit} className="customer-info-form">
                <div className="form-group">
                    <label htmlFor="email" className="customer-info-label">
                        Email:
                    </label>
                    <input
                        type="text"
                        id="email"
                        value={email}
                        onChange={handleEmailChange}
                        required
                        placeholder="Enter your email address"
                    />
                </div>
                <button type="submit" className="submit-btn">Submit</button>
            </form>
            {orderInfo.length > 0 ? (
                <div className="order-details">
                    <h2>Order Details:</h2>
                    <div className="order-grid">
                        {orderInfo.map((order, index) => (
                            <div key={index} className="order">
                                <h3>Order #{index + 1}</h3>
                                <p><strong>Email:</strong> {order.customer.email}</p>
                                <p><strong>Store:</strong> {order.eStore}</p>
                                <p><strong>Date:</strong> {formatDate(order.date)}</p>
                                <p><strong>Status:</strong> {order.status}</p>
                                <p><strong>Products:</strong></p>
                                <ul className="product-list">
                                    {order.products.map((product, productIndex) => (
                                        <li key={productIndex}>
                                            {product.name} - Price: ${product.price.toFixed(2)} -
                                            Quantity: {product.quantity}
                                        </li>
                                    ))}
                                </ul>
                                <p><strong>Total Price:</strong> ${calculateTotalPrice(order.products)}</p>
                            </div>
                        ))}
                    </div>
                </div>
            ) : (
                <p className="error-message">{errorMessage}</p>
            )}
        </div>
    );
};
