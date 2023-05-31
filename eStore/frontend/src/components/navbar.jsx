import React from "react";
import { Link } from "react-router-dom";
import { ShoppingCart } from "phosphor-react";
import "./navbar.css";

// Import the CheckOrders component
import { CheckOrders } from "../pages/orders/CheckOrders";

export const Navbar = () => {
  return (
    <div className="navbar">
      <div className="links">
        {/* Add the link to the CheckOrders page */}
        <Link to="/check-orders">Check Orders</Link>
        <Link to="/">Shop</Link>
        <Link to="/cart">
          <ShoppingCart size={32} />
        </Link>
      </div>
    </div>
  );
};
