import "./App.css";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {Navbar} from "./components/navbar";
import {Shop} from "./pages/shop/shop";
import {Cart} from "./pages/cart/cart";
import {Checkout} from "./pages/checkout/checkout";
import {CheckOrders} from "./pages/orders/CheckOrders"; // Import the CheckOrders component
import {ShopContextProvider} from "./context/shop-context";

function App() {
    return (
        <div className="App">
            <ShopContextProvider>
                <Router>
                    <Navbar/>
                    <Routes>
                        <Route path="/" element={<Shop/>}/>
                        <Route path="/cart" element={<Cart/>}/>
                        <Route path="/checkout" element={<Checkout/>}/>
                        <Route path="/check-orders" element={<CheckOrders/>}/> // Add the CheckOrders route
                    </Routes>
                </Router>
            </ShopContextProvider>
        </div>
    );
}

export default App;
