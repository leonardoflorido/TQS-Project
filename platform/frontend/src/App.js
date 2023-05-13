import {Route, Routes} from "react-router-dom";
import Dashboard from "./scenes/dashboard/Dashboard.tsx";
import Login from "./scenes/login/login.tsx";
import Signup from "./scenes/signup/signup.tsx";
import ACP from "./scenes/acp/ACP.tsx";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Dashboard/>}/>
      <Route path="/login" element={<Login/>}/>
      <Route path="/signup" element={<Signup/>}/>
      <Route path="/acp" element={<ACP/>}/>
      <Route path="*" element={<Dashboard/>}/>
    </Routes>
  );
}

export default App;