import SubjectGet from "../pages/subject/view_and_delete";
import SubjectCreate from "../pages/subject/create";
import SubjectUpdate from "../pages/subject/update";

const subjectRoutes = [
  {
    path: "/subjects/get/:id",       // id = semesterId
    element: <SubjectGet />,
  },
  {
    path: "/subjects/create/:id",    // id = semesterId
    element: <SubjectCreate />,
  },
  {
    path: "/subjects/update/:id",    // id = subjectId
    element: <SubjectUpdate />,
  },
];

export default subjectRoutes;
