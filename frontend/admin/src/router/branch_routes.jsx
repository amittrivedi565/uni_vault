import BranchGet from "../pages/branch/view_and_delete"
import BranchCreate from "../pages/branch/create"
import BranchUpdate from "../pages/branch/update"

const courseRoutes = [
    {
        path: "/branches/get/:id",
        element: <BranchGet />
    },
    {
        path: "/branches/create/:id",
        element: <BranchCreate />
    },
    {
        path: "/branches/update/:id",
        element: < BranchUpdate/>
    }
]

export default courseRoutes;