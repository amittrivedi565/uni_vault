import { BreadCrumbProvider } from "./context/breadcrumb_context";
import router from "./router/index_routes";
import { RouterProvider } from "react-router-dom";

function App() {
  return (
    <BreadCrumbProvider>
      <RouterProvider router={router} />
    </BreadCrumbProvider>
  );
}

export default App;
