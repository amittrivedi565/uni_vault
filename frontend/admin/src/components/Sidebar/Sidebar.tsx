import './sidebar.css';

function Sidebar() {
  return (
    <div className='sidebar-container'>
      <a className='sidebar-heading'>
        <b>ACTIONS</b>
      </a>
      <a className='sidebar-sub-heading ' href='/institutes/get'>
        Manage Uni Content
      </a>
      <a className='sidebar-sub-heading ' href='/pyqp'>
        Manage PYQP
      </a>
      <a className='sidebar-sub-heading ' href='/storage-bucket'>
        Manage Kill Interview
      </a>
      <a className='sidebar-sub-heading ' href='/storage-bucket'>
        Storage Bucket
      </a>
      <a className='sidebar-sub-heading ' href='/storage-bucket'>
        Transcation Logs
      </a>
    </div>
  );
}

export default Sidebar;
