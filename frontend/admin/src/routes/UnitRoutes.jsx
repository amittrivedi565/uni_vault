import UnitView from "../pages/Unit/UnitView";
import UnitCreate from "../pages/Unit/UnitCreate";
import UnitUpdate from "../pages/Unit/UnitUpdate";

const UnitRoutes = [
  {
    path: "/units/:subjectId",
    element: <UnitView />,
  },
  {
    path: "/units/create/:subjectId",
    element: <UnitCreate />,
  },
    {
    path: "/units/update/:unitId",
    element: <UnitUpdate />,
  }
];

export default UnitRoutes;
