import CourseView from "../pages/Course/CourseView";
import CourseUpdate from "../pages/Course/CourseUpdate";
import CourseCreate from "../pages/Course/CourseCreate";

const CourseRoutes = [
  {
    path: "/courses/:instituteId",
    element: <CourseView />,
  },
  {
    path: "/courses/create/:instituteId",
    element: <CourseCreate />,
  },
    {
    path: "/courses/update/:courseId",
    element: <CourseUpdate />,
  }
];

export default CourseRoutes;
