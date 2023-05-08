import React from "react";
import {Product} from "./product";
import "./shop.css";

export const Shop = () => {
    const [products, setProducts] = React.useState([]);
    // Fetch api
    React.useEffect(() => {
        fetch("http://127.0.0.1:8000/api/v1/products/")
            .then((response) => response.json())
            .then((data) => setProducts(data));
    }, []);


    return (
        <div className="shop">
            <div className="shopTitle">
                <h1>TQS Shop</h1>
            </div>

            <div className="products">
                {products.map((product) => (
                    <Product data={product}/>
                ))}
            </div>
        </div>
    );
};
