import {useState} from "react";
import "./shipping.css";

export const ShippingForm = () => {
    const [deliveryOption, setDeliveryOption] = useState('pickup');

    return (
        <div className="shipping-container">
            <form className="shipping-form">
                <div>
                    <input
                        type="radio"
                        id="pickup"
                        name="delivery"
                        value="pickup"
                        checked={deliveryOption === 'pickup'}
                        onChange={() => setDeliveryOption('pickup')}

                    />
                    <label htmlFor="pickup"
                           className={deliveryOption === 'pickup' ? 'active' : 'shipping-label'}>Pickup</label>
                </div>
                <div>
                    <input
                        type="radio"
                        id="standard"
                        name="delivery"
                        value="standard"
                        checked={deliveryOption === 'standard'}
                        onChange={() => setDeliveryOption('standard')}
                    />
                    <label htmlFor="standard" className={deliveryOption === 'standard' ? 'active' : 'shipping-label'}>Standard
                        Delivery</label>
                </div>
                {deliveryOption === 'standard' && (
                    <div className="shipping-information">
                        <h3>Shipping Information</h3>
                        <input type="text" placeholder="Full Name"/>
                        <input type="text" placeholder="Address"/>
                        <input type="text" placeholder="City"/>
                        <input type="text" placeholder="State"/>
                        <input type="text" placeholder="Zip Code"/>
                    </div>
                )}
            </form>
        </div>
    );
}

