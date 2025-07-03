import './sidebar.css';

function Sidebar() {
  return (
    <div className='sidebar-container'>
      <a className='sidebar-heading'>
        <b>ACTIONS</b>
      </a>
      <a className='sidebar-sub-heading ' href='/institutes'>
        Manage Notes
      </a>
      <a className='sidebar-sub-heading ' href='/pyqp'>
        Manage Pyqp's
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
