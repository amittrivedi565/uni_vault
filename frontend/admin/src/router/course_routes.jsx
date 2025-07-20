import CourseGet from "../pages/course/view_and_delete"
import CourseCreate from "../pages/course/create"
import CourseUpdate from "../pages/course/update"

const courseRoutes = [
    {
        path:"/courses/get/:id",
        element: <CourseGet/>
    },
    {
        path:"/courses/create/:id",
        element: <CourseCreate/>
    },
        {
        path:"/courses/update/:id",
        element: <CourseUpdate/>
    }
]

export default courseRoutes;