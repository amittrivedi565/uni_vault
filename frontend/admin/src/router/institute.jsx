import InstituteGet from "../pages/institute/get"
import InstitutePost from "../pages/institute/post"
import InstituteUpdate from "../pages/institute/update"
const routes = [
    {
        path: '/institutes',
        element:
            <InstituteGet />
    },
    {
        path: '/institutes/create',
        element: (
            <InstitutePost />
        )
    },
    {
        path: '/institutes/update/:id',
        element: (
            <InstituteUpdate />
        )
    }
]

export default routes