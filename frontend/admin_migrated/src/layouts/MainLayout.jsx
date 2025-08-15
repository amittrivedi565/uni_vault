import Sidebar from "../components/Sidebar/Sidebar";

const MainLayout = ({ children }) => {
  return (
    <div className="container-fluid">
      <div className="row">
        {/* Left Side Content */}
        <div className="vh-100 col-2 border-end d-flex flex-column px-3 d-none d-lg-block py-4">
          <Sidebar />
        </div>
        {/* Right Side Content */}
        <div className="col-12 col-lg-10 bg-light p-3 vh-100">
          {children}
        </div>
      </div>
    </div>
  );
};

export default MainLayout;
