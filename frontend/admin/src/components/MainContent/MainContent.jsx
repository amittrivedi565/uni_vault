import "./MainContent.css"
import "../BreadCrumb/BreadCrumb"
import "../Table/TableComponent";
import TableComponent from "../Table/TableComponent";
import Header from "../Header/Header"
function MainContent(){
    return(<>
    <div className="main-content">
        <div className="main-content-container">
        <Header/>
            <div className="main-content-heading">
                <h4>Content Flow</h4>
                &nbsp;
                <button>+</button>
            </div>
            <span className="showing-results">Showing results</span>
            <TableComponent/>
        </div>
    </div>
    </>)
}

export default MainContent;

