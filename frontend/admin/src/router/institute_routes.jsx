import InstituteGet from "../pages/institute/view_delete";
import InstitutePost from "../pages/institute/create";
import InstituteUpdate from "../pages/institute/update";
import InstituteDetaiView from "../pages/institute/detail_view"

const instituteRoutes = [
  {
    path: "/institutes/get",
    element: <InstituteGet/>,
  },
  {
    path: "/institutes/create",
    element: <InstitutePost />,
  },
  {
    path: "/institutes/update/:id",
    element: <InstituteUpdate />,
  },
  {
    path: "/institutes/details/:id",
    element: <InstituteDetaiView />,
  },
];

export default instituteRoutes;
