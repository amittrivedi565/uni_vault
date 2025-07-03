import './navbar.css';

function navbar() {
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
        </div>
      </nav>
    </>
  );
}

export default navbar;
