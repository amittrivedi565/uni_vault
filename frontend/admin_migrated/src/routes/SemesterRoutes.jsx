import SemesterCreate from "../pages/Semester/SemesterCreate";
import SemesterUpdate from "../pages/Semester/SemesterUpdate";
import SemesterView from "../pages/Semester/SemesterView";

const SemesterRoutes = [
  {
    path: "/semesters/:branchId",
    element: <SemesterView />,
  },
  {
    path: "/semesters/create/:branchId",
    element: <SemesterCreate />,
  },
    {
    path: "/semesters/update/:semesterId",
    element: <SemesterUpdate />,
  }
];

export default SemesterRoutes;
