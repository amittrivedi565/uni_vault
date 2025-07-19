import "../styles/globals.css";
import { Outlet } from 'react-router-dom';
import Navbar from "./navbar/navbar"
import Footer from "../layouts/footer/footer"


function Default() {
  return (
    <div className="default">
      <Navbar />
      <Outlet />
      <Footer />
    </div>
  );
}

export default Default;
