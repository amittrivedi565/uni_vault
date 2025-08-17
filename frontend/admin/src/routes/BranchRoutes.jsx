import BranchView from "../pages/Branch/BranchView";
import BranchCreate from "../pages/Branch/BranchCreate";
import BranchUpdate from "../pages/Branch/BranchUpdate";

const BranchRoutes = [
  {
    path: "/branches/:courseId",
    element: <BranchView />,
  },
  {
    path: "/branches/create/:courseId",
    element: <BranchCreate />,
  },
    {
    path: "/branches/update/:branchId",
    element: <BranchUpdate />,
  }
];

export default BranchRoutes;
