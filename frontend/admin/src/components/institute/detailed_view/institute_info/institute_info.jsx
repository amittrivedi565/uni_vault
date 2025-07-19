import "./institute_info.css"
import { useParams } from "react-router-dom";
function box({data, loading, error}) {
    const { id } = useParams();
     if (loading) return <p>Loading...</p>;
     if (error) return <p>Error: {error}</p>;
     return (
        <>
            <div className="institute-info-container">
                <div className="institute-info-container-left">
                    <p>{data.name}</p>
                    <p className="meta-tags">{data?.established ? `Established ${data.established}` : "Established year not available"}</p>
                    <p className="last-updated">Last updated: {data.updatedAt}</p>
                </div>
                <div className="institute-info-container-right">
                    <p className="stats-label">Total Downloads</p>
                    <p className="stats-value">*345</p>
                </div>
            </div>

        </>
    )
}
export default box