function Navbar() {
  return (
    <>
      <nav className={`navbar navbar-expand-lg navbar-light border-bottom bg-white`}>
        <div className="container-fluid">
          <a className="navbar-brand px-1" href="/">
            uni_vault
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNavSidebar"
            aria-controls="navbarNavSidebar"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          {/* Below screen size of Large, the navbar is blocked, and expandable hamburger is shown */}
          {/* These fields are present in sidebar, only shown below large */}
          <div className="collapse navbar-collapse" id="navbarNavSidebar">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item d-lg-none">
                <a className="nav-link active" href="#">
                  Manage Institute Content
                </a>
              </li>
              <li className="nav-item d-lg-none">
                <a className="nav-link" href="#">
                  Storage Bucket
                </a>
              </li>
              <li className="nav-item d-lg-none">
                <a className="nav-link" href="#">
                  Logout
                </a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </>
  );
}

export default Navbar;
