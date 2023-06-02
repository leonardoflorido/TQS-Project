import React from "react";
import {Product} from "./product";
import "./shop.css";

export const Shop = () => {
    const [products, setProducts] = React.useState([]);
    // Fetch api
    React.useEffect(() => {
        fetch("http://34.175.80.212:8000/api/products/")
            .then((response) => response.json())
            .then((data) => setProducts(data));
    }, []);

    return (
        <div className="shop">
            <div className="shopTitle">
                <h1>eStore</h1>
            </div>

            <div className="products">
                {products.map((product) => (
                    <Product key={product.id} data={product}/>
                ))}
            </div>
        </div>
    );
};
