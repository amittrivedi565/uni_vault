import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/home";
import Default from "../layouts/default";
import instituteRoutes from "./institute_routes";
import courseRoutes from "./course_routes"

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
      ...courseRoutes,
      {
        path: "*",
        element: <h1>404 not found</h1>,
      },
    ],
  },
]);

export default router;
