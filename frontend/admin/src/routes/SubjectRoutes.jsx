import SubjectCreate from "../pages/Subject/SubjectCreate";
import SubjectUpdate from "../pages/Subject/SubjectUpdate";
import SubjectView from "../pages/Subject/SubjectView";

const SubjectRoutes = [
  {
    path: "/subjects/:semesterId",
    element: <SubjectView />,
  },
  {
    path: "/subjects/create/:semesterId",
    element: <SubjectCreate />,
  },
    {
    path: "/subjects/update/:subjectId",
    element: <SubjectUpdate />,
  }
];

export default SubjectRoutes;
