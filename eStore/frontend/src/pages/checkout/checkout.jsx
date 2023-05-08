import React, {useContext, useState} from "react";
import {ShopContext} from "../../context/shop-context";
import "./checkout.css";
import {CartItem} from "../cart/cart-item";
import {CustomerInfoForm} from "./customerInfo";
import {ShippingForm} from "./shipping";

export const Checkout = () => {
    const [products, setProducts] = React.useState([]);
    // Fetch api
    React.useEffect(() => {
        fetch("http://127.0.0.1:8000/api/v1/products/")
            .then((response) => response.json())
            .then((data) => setProducts(data));
    }, []);

    const {cartItems, getTotalCartAmount} = useContext(ShopContext);
    console.log(cartItems);
    const totalAmount = getTotalCartAmount();

    const [pickUpOption, setPickUpOption] = useState(false);

    const handlePickUpOptionChange = (event) => {
        setPickUpOption(event.target.checked);
    };

    const [selectedTab, setSelectedTab] = useState("tab1");

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
                    className={`tab-content ${
                        selectedTab === "tab1" ? "active" : ""
                    }`}
                >
                    <h3>Cart Items</h3>
                    <div className="cart">
                        {products.map((product) => {
                            if (cartItems[product.id] !== 0) {
                                return <CartItem data={product}/>;
                            }
                        })}
                    </div>
                    <p> Subtotal: ${totalAmount} </p>

                    <p/>
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
                    className={`tab-content ${
                        selectedTab === "tab2" ? "active" : ""
                    }`}
                >
                    <h3>Customer Information</h3>
                    <CustomerInfoForm/>
                    <div className="button-master-container">
                        <div
                            className="button-container button-continue"
                            onClick={(e) => {
                                e.preventDefault();
                                setSelectedTab("tab3");
                            }}
                        >
                            Continue
                        </div>
                    </div>
                </section>

                <section
                    id="content3"
                    className={`tab-content ${
                        selectedTab === "tab3" ? "active" : ""
                    }`}
                >
                    <h3>Shipping</h3>
                    <ShippingForm/>
                    <div className="button-master-container">
                        <div
                            className="button-container button-continue"
                            onClick={(e) => {
                                e.preventDefault();
                                setSelectedTab("tab4");
                            }}
                        >
                            Continue
                        </div>
                    </div>
                </section>
                <section
                    id="content4"
                    className={`tab-content ${
                        selectedTab === "tab4" ? "active" : ""
                    }`}
                >
                    <h4 className="payment-title">
                        Choose your payment method
                    </h4>
                    <form>
                        <div className="pymt-radio">

                        </div>
                        <div className="pymt-radio">
                            <div className="row-payment-method payment-row-last">
                                <div className="select-icon hr">
                                    <input
                                        type="radio"
                                        id="radio2"
                                        name="radios"
                                        defaultValue="pp"
                                        defaultChecked
                                    />
                                    <label htmlFor="radio2"/>
                                </div>
                                <div className="select-txt hr">
                                    <p className="pymt-type-name">
                                        Credit Card
                                    </p>
                                    <p className="pymt-type-desc">
                                        Safe money transfer using your bank
                                        account. Safe payment online. Credit
                                        card needed. Visa, Maestro, Discover,
                                        American Express
                                    </p>
                                </div>
                                <div className="select-logo">
                                    <div className="select-logo-sub logo-spacer">
                                        <img
                                            src="https://www.dropbox.com/s/by52qpmkmcro92l/logo-visa.png?raw=1"
                                            alt="Visa"
                                        />
                                    </div>
                                    <div className="select-logo-sub">
                                        <img
                                            src="https://www.dropbox.com/s/6f5dorw54xomw7p/logo-mastercard.png?raw=1"
                                            alt="MasterCard"
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="form-cc">
                            <div className="row-cc">
                                <div className="cc-field">
                                    <div className="cc-title">
                                        Credit Card Number
                                    </div>
                                    <input
                                        type="text"
                                        className="input cc-txt text-validated"
                                    />
                                </div>
                            </div>
                            <div className="row-cc">
                                <div className="cc-field">
                                    <div className="cc-title">Expiry Date</div>
                                    <select className="input cc-ddl">
                                        <option>01</option>
                                        <option>02</option>
                                        <option>03</option>
                                        <option>04</option>
                                        <option>05</option>
                                        <option>06</option>
                                        <option>07</option>
                                        <option>08</option>
                                        <option>09</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>
                                    </select>
                                    <select className="input cc-ddl">
                                        <option>01</option>
                                        <option>02</option>
                                        <option>03</option>
                                        <option>04</option>
                                        <option>05</option>
                                        <option>06</option>
                                        <option>07</option>
                                        <option>08</option>
                                        <option>09</option>
                                        <option>10</option>
                                        <option>11</option>
                                        <option>12</option>
                                        <option>13</option>
                                        <option>14</option>
                                        <option>15</option>
                                        <option>16</option>
                                        <option>17</option>
                                        <option>18</option>
                                        <option>19</option>
                                        <option>20</option>
                                        <option>21</option>
                                        <option>22</option>
                                        <option>23</option>
                                        <option>24</option>
                                        <option>25</option>
                                        <option>26</option>
                                        <option>27</option>
                                        <option>28</option>
                                        <option>29</option>
                                        <option>30</option>
                                        <option>31</option>
                                    </select>
                                </div>
                                <div className="cc-field">
                                    <div className="cc-title">
                                        CVV Code
                                        <span className="numberCircle">?</span>
                                    </div>
                                    <input
                                        type="text"
                                        className="input cc-txt"
                                    />
                                </div>
                            </div>
                            <div className="row-cc">
                                <div className="cc-field">
                                    <div className="cc-title">Name on Card</div>
                                    <input
                                        type="text"
                                        className="input cc-txt"
                                    />
                                </div>
                            </div>
                        </div>
                        <div className="button-master-container">
                            <div className="button-container button-finish">
                                Finish Order
                            </div>
                        </div>
                    </form>
                </section>
            </div>
        </div>
    );
};
