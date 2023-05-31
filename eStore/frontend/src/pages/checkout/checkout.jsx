import React, { useContext, useState, useEffect } from "react";
import { ShopContext } from "../../context/shop-context";
import { CartItem } from "../cart/cart-item";
import { CustomerInfoForm } from "./customerInfo";
import { ShippingForm } from "./shipping";

import "./checkout.css";

export const Checkout = () => {
  const [products, setProducts] = useState([]);
  const { cartItems, getTotalCartAmount } = useContext(ShopContext);
  const totalAmount = getTotalCartAmount();
  const [pickUpOption, setPickUpOption] = useState(false);
  const [selectedTab, setSelectedTab] = useState("tab1");

  useEffect(() => {
    fetch("http://127.0.0.1:8000/api/products/")
      .then((response) => response.json())
      .then((data) => setProducts(data));
  }, []);

  const handlePickUpOptionChange = (event) => {
    setPickUpOption(event.target.checked);
  };

  const handleTabChange = (event) => {
    setSelectedTab(event.target.id);
  };

  return (
    <div>
      <h1>Checkout Page</h1>

      <div className="tab_container">
        <input
          id="tab1"
          type="radio"
          name="tabs"
          checked={selectedTab === "tab1"}
          onChange={handleTabChange}
        />
        <label htmlFor="tab1">
          <span className="numberCircle">1</span>
          <span>Cart</span>
        </label>

        <input
          id="tab2"
          type="radio"
          name="tabs"
          checked={selectedTab === "tab2"}
          onChange={handleTabChange}
        />
        <label htmlFor="tab2">
          <span className="numberCircle">2</span>
          <span>Customer Information</span>
        </label>

        <input
          id="tab3"
          type="radio"
          name="tabs"
          checked={selectedTab === "tab3"}
          onChange={handleTabChange}
        />
        <label htmlFor="tab3">
          <span className="numberCircle">3</span>
          <span>Shipping</span>
        </label>

        <input
          id="tab4"
          type="radio"
          name="tabs"
          checked={selectedTab === "tab4"}
          onChange={handleTabChange}
        />
        <label htmlFor="tab4">
          <span className="numberCircle">4</span>
          <span>Payment</span>
        </label>

        <section
          id="content1"
          className={`tab-content ${selectedTab === "tab1" ? "active" : ""}`}
        >
          <h3>Cart Items</h3>
          <div className="cart">
            {products.map((product) => {
              if (cartItems[product.id] !== 0) {
                const imageUrl = `http://127.0.0.1:8000/${product.image}`;
                return (
                  <CartItem
                    key={product.id}
                    data={product}
                    imageUrl={imageUrl}
                  />
                );
              }
              return null;
            })}
          </div>
          <p>Subtotal: ${totalAmount.toFixed(2)}</p>
          <p />
          <div className="button-master-container">
            <div
              className="button-container button-continue"
              onClick={(e) => {
                e.preventDefault();
                setSelectedTab("tab2");
              }}
            >
              Continue
            </div>
          </div>
        </section>

        <section
          id="content2"
          className={`tab-content ${selectedTab === "tab2" ? "active" : ""}`}
        >
          <CustomerInfoForm setSelectedTab={setSelectedTab} />
        </section>

        <section
          id="content3"
          className={`tab-content ${selectedTab === "tab3" ? "active" : ""}`}
        >
          <ShippingForm
            pickUpOption={pickUpOption}
            handlePickUpOptionChange={handlePickUpOptionChange}
            setSelectedTab={setSelectedTab}
          />
        </section>

        <section
          id="content4"
          className={`tab-content ${selectedTab === "tab4" ? "active" : ""}`}
        >
          <h3>Payment</h3>
          <p>Payment options go here...</p>
          <div className="button-master-container">
            <div
              className="button-container button-continue"
              onClick={(e) => {
                e.preventDefault();
                setSelectedTab("tab3");
              }}
            >
              Back
            </div>
            <div
              className="button-container button-continue"
              onClick={(e) => {
                e.preventDefault();
                setSelectedTab("tab1");
              }}
            >
              Cancel
            </div>
            <div
              className="button-container button-continue"
              onClick={(e) => {
                e.preventDefault();
                setSelectedTab("tab1");
              }}
            >
              Place Order
            </div>
          </div>
        </section>
      </div>
    </div>
  );
};
