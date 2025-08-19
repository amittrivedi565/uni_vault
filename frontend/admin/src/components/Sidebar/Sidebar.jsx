import { useBreadcrumb } from "../../hooks/useBreadcrumb";
function Sidebar() {
  const {clearBreadcrumb } = useBreadcrumb();
  return (
    <>
      <b>ACTIONS</b>
      <nav className="nav flex-column">
        <a className="nav-link px-0 text-secondary" href="/institutes" onClick={(e)=>{
          clearBreadcrumb()
        }}>
          Manage Institute Content
        </a>
        <a className="nav-link px-0 text-secondary" href="/storage-bucket">
          Manage Storage Bucket
        </a>
        <a className="nav-link px-0 text-secondary" href="/logout">
          Logout
        </a>
      </nav>
    </>
  );
}
export default Sidebar;
