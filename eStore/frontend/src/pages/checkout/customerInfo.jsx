import {useState} from 'react';
import './customerInfo.css';

export const CustomerInfoForm = ({setCustomerInfo, setSelectedTab}) => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [state, setState] = useState('');
    const [zipCode, setZipCode] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        const updatedCustomerInfo = {
            name: `${firstName} ${lastName}`,
            email,
            phone,
            address: `${address}, ${city}, ${state}, ${zipCode}`
        };

        setCustomerInfo(updatedCustomerInfo); // Update the customerInfo state

        setSelectedTab('tab3');

        console.log(updatedCustomerInfo);
    };

    return (
        <form onSubmit={handleSubmit} className="customer-info">
            <div>
                <label htmlFor="first-name" className='customer-info-label'>First Name:</label>
                <input type="text" id="first-name" value={firstName} onChange={(e) => setFirstName(e.target.value)}
                       required/>
            </div>
            <div>
                <label htmlFor="last-name" className='customer-info-label'>Last Name:</label>
                <input type="text" id="last-name" value={lastName} onChange={(e) => setLastName(e.target.value)}
                       required/>
            </div>
            <div>
                <label htmlFor="email" className='customer-info-label'>Email:</label>
                <input type="text" id="email" value={email} onChange={(e) => setEmail(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="phone" className='customer-info-label'>Phone:</label>
                <input type="text" id="phone" value={phone} onChange={(e) => setPhone(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="address" className='customer-info-label'>Address:</label>
                <input type="text" id="address" value={address} onChange={(e) => setAddress(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="city" className='customer-info-label'>City:</label>
                <input type="text" id="city" value={city} onChange={(e) => setCity(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="state" className='customer-info-label'>State:</label>
                <input type="text" id="state" value={state} onChange={(e) => setState(e.target.value)} required/>
            </div>
            <div>
                <label htmlFor="zip-code" className='customer-info-label'>Zip Code:</label>
                <input type="text" id="zip-code" value={zipCode} onChange={(e) => setZipCode(e.target.value)} required/>
            </div>
            <button type="submit">Submit</button>
        </form>
    );
};
