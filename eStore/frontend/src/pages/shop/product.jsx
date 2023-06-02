import React, {useContext} from "react";
import {ShopContext} from "../../context/shop-context";

export const Product = (props) => {
    const {id, name, price, image} = props.data;
    const {addToCart, cartItems} = useContext(ShopContext);

    const cartItemCount = cartItems[id];

    const handleAddToCart = () => {
        addToCart(id);
        console.log("Item added to cart:", id);
    };

    return (
        <div className="product">
            <img alt={name} src={`http://34.175.95.229:8000${image}`}/>
            <div className="description">
                <p>
                    <b>{name}</b>
                </p>
                <p>€{price}</p>
            </div>
            <button className="addToCartBttn" onClick={handleAddToCart}>
                Add To Cart {cartItemCount > 0 && <> ({cartItemCount})</>}
            </button>
        </div>
    );
};
