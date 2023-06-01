import React, {useContext, useEffect, useState} from "react";
import {ShopContext} from "../../context/shop-context";
import {CartItem} from "./cart-item";
import {useNavigate} from "react-router-dom";

import "./cart.css";

export const Cart = () => {
    const {cartItems, getTotalCartAmount, checkout} = useContext(ShopContext);
    const totalAmount = getTotalCartAmount();
    const navigate = useNavigate();
    const [products, setProducts] = useState([]);

    useEffect(() => {
        fetch("http://127.0.0.1:8000/api/products/")
            .then((response) => response.json())
            .then((data) => setProducts(data));
    }, []);

    return (
        <div className="cart">
            <div>
                <h1>Your Cart Items</h1>
            </div>
            <div className="cart">
                {products.map((product) => {
                    const cartItemCount = cartItems[product.id];
                    if (cartItemCount !== 0) {
                        const imageUrl = `http://127.0.0.1:8000${product.image}`;
                        return <CartItem key={product.id} data={product} imageUrl={imageUrl}/>;
                    }
                    return null;
                })}
            </div>

            {totalAmount > 0 ? (
                <div className="checkout">
                    <p>Subtotal: ${totalAmount.toFixed(2)}</p>
                    <button onClick={() => navigate("/")}>Continue Shopping</button>
                    <button onClick={() => navigate("/checkout")}>Checkout</button>
                </div>
            ) : (
                <h1>Your Shopping Cart is Empty</h1>
            )}
        </div>
    );
};
