import './Navbar.css';
import LoginSvg from "../../assets/LoginSvg"
import UserSvg from "../../assets/UserSvg"
function Navbar() {
  return (
    <>
      <nav>
        <div className='navbar-left'>
          <p className=''>uni_vault</p>
        </div>
        <div className='navbar-middle'>
          <a href=''>Analytics</a>
          <a href=''>Monitor</a>
        </div>
        <div className='navbar-right'>
          <UserSvg/>
          <LoginSvg/>
        </div>
      </nav>
    </>
  );
}

export default Navbar;
