import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/home";
import Default from "../layouts/default";
import instituteRoutes from "./institute_routes";
import courseRoutes from "./course_routes"
import branchRoutes from "./branch_routes"
import semesterRoutes from "./semester_routes"
import SubjectRoutes from "./subject_routes";
import UnitRoutes from "./unit_routes";

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
      ...branchRoutes,
      ...semesterRoutes,
      ...SubjectRoutes,
      ...UnitRoutes,
      {
        path: "*",
        element: <h1>404 not found</h1>,
      },
    ],
  },
]);

export default router;
