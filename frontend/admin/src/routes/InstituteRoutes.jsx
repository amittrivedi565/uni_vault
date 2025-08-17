import InstituteView from "../pages/Institute/InstituteView";
import InstituteCreate from "../pages/Institute/InstituteCreate";
import InstituteUpdate from "../pages/Institute/InstituteUpdate";

const InstituteRoutes = [
  {
    path: "/institutes",
    element: <InstituteView />,
  },
  {
    path: "/institutes/create",
    element: <InstituteCreate />,
  },
  {
    path: "/institutes/update/:instituteId",
    element: <InstituteUpdate />,
  }
];

export default InstituteRoutes;
