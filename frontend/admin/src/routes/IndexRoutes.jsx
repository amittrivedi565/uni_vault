import { createBrowserRouter } from "react-router-dom";
import Home from "../pages/Home";
import InstituteRoutes from "./InstituteRoutes";
import CourseRoutes from "./CourseRoutes";
import BranchRoutes from "./BranchRoutes";
import SemesterRoutes from "./SemesterRoutes";
import SubjectRoutes from "./SubjectRoutes";
import UnitRoutes from "./UnitRoutes";
import Login from "../pages/Login";

const childRoutes = [
  {
    index: true,
    element: <Home />,
  },{
    path: "/login",
    element: <Login/>,
  },
  ...InstituteRoutes,
  ...CourseRoutes,
  ...BranchRoutes,
  ...SemesterRoutes,
  ...SubjectRoutes,
  ...UnitRoutes,
  {
    path: "*",
    element: <h1>404 not found</h1>,
  },
];

const router = createBrowserRouter(
  [
    {
      path: "/",
      children: childRoutes,
    },
  ],
  {
    future: {
      v7_startTransition: true,       // smoother navigation, non-blocking
      v7_relativeSplatPath: true,     // new relative path handling for splats
      v7_fetcherPersist: true,        // keep fetcher data during revalidation
      v7_partialHydration: true,      // improve hydration in SSR scenarios
    },
  }
);

export default router;
