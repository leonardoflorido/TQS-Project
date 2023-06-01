import {Route, Routes} from "react-router-dom";
import Dashboard from "./scenes/dashboard/Dashboard.jsx";
import Login from "./scenes/login/login.jsx";
import Signup from "./scenes/signup/signup.jsx";
import ACP from "./scenes/acp/ACP.jsx";

function App() {
    return (
        <Routes>
            <Route path="/admin/dashboard" element={<Dashboard/>}/>
            <Route path="/login" element={<Login user="acp"/>}/>
            <Route path="/admin/login" element={<Login user="admin"/>}/>
            <Route path="/signup" element={<Signup/>}/>
            <Route path="/acp/dashboard" element={<ACP/>}/>
            <Route path="*" element={<Login/>}/>
        </Routes>
    );
}

export default App;
