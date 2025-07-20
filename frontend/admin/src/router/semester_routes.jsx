import SemesterGet from "../pages/semester/view_and_delete"
import SemesterCreate from "../pages/semester/create"
import SemesterUpdate from "../pages/semester/update"

const semesterRoutes = [
    {
        path:"/semesters/get/:id",
        element: <SemesterGet/>
    },
    {
        path:"/semesters/create/:id",
        element: <SemesterCreate/>
    },
        {
        path:"/semesters/update/:id",
        element: <SemesterUpdate/>
    }
]

export default semesterRoutes;