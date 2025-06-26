import './Sidebar.css';

function Sidebar() {
  return (
    <>
      <div className='sidebar'>
        <a className='sidebar-heading'>
          <b>ACTIONS</b>
        </a>
        <a className='sidebar-sub-heading ' href='/'>
          Manage Notes
        </a>
        <a className='sidebar-sub-heading ' href='/'>
          Manage Pyqp's
        </a>
        <a className='sidebar-sub-heading ' href='/'>
          Storage bucket
        </a>
      </div>
    </>
  );
}

export default Sidebar;
