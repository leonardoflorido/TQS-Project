import React, {useContext} from "react";
import {ShopContext} from "../../context/shop-context";
import {CartItem} from "./cart-item";
import {useNavigate} from "react-router-dom";

import "./cart.css";

export const Cart = () => {
    const [products, setProducts] = React.useState([]);
    // Fetch api
    React.useEffect(() => {
        fetch("http://127.0.0.1:8000/api/v1/products/")
            .then((response) => response.json())
            .then((data) => setProducts(data));
    }, []);

    const {cartItems, getTotalCartAmount, checkout} = useContext(ShopContext);
    const totalAmount = getTotalCartAmount();

    const navigate = useNavigate();

    return (
        <div className="cart">
            <div>
                <h1>Your Cart Items</h1>
            </div>
            <div className="cart">
                {products.map((product) => {
                    if (cartItems[product.id] !== 0) {
                        return <CartItem data={product}/>;
                    }
                })}
            </div>

            {totalAmount > 0 ? (
                <div className="checkout">
                    <p> Subtotal: ${totalAmount} </p>
                    <button onClick={() => navigate("/")}> Continue Shopping</button>
                    <button
                        onClick={() => {
                            navigate("/checkout");
                        }}
                    >
                        {" "}
                        Checkout{" "}
                    </button>
                </div>
            ) : (
                <h1> Your Shopping Cart is Empty</h1>
            )}
        </div>
    );
};
