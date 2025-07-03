import { Outlet } from 'react-router-dom';
import Navbar from "./navbar/navbar"
import Footer from "../layouts/footer/footer"

import "../index.css";

function Default() {
  return (
    <div className="default">
        <Navbar/>
        <Outlet/> 
        <Footer/>
    </div>
  );
}

export default Default;
