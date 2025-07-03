import "./header_bar.css"

function headerbar({children}){
return(<>
    <div className="header-bar">
        <div className="header-bar-left" style={{fontFamily: "Horizon, sans-serif"}}>NoteX</div>
        <div className="header-bar-right">Service Panel</div>
    </div>
</>)
}

export default headerbar;