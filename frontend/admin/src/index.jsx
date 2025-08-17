import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { BreadcrumbContextProvider } from './context/BreadcrumbContext';

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BreadcrumbContextProvider>
    <App />
  </BreadcrumbContextProvider>
); 