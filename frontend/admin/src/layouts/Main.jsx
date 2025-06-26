import Sidebar from "../components/Sidebar/Sidebar";
import MainContent from "../components/MainContent/MainContent";
import "./Main.css";

function Main() {
  return (
    <div className="layout">
      <Sidebar />
      <MainContent />
    </div>
  );
}

export default Main;
