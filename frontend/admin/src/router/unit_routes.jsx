import UnitGet from "../pages/unit/view_and_delete";
import UnitCreate from "../pages/unit/create";
import UnitUpdate from "../pages/unit/update";

const unitRoutes = [
  {
    path: "/units/get/:id",       // id = subjectId
    element: <UnitGet />,
  },
  {
    path: "/units/create/:id",    // id = subjectId
    element: <UnitCreate />,
  },
  {
    path: "/units/update/:id",    // id = unitId
    element: <UnitUpdate />,
  },
];

export default unitRoutes;
