import { createBrowserRouter } from "react-router-dom";
import instituteRoutes from "./institute";
import Home from "../pages/home";
import Default from "../layouts/default";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Default />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      ...instituteRoutes,
      {
        path: "*",
        element: <h1>404 not found</h1>,
      },
    ],
  },
]);

export default router;
