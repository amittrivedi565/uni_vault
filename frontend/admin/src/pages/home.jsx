import Navbar from "../components/Navbar/Navbar";
import MainLayout from "../layouts/MainLayout";

function Home(){
    return(<>
        <Navbar/>
        <MainLayout>
            Home Page
        </MainLayout>
    </>)
}
export default Home;