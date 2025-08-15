import { useBreadcrumb } from "../../hooks/useBreadcrumb";
function Breadcrumb({ createLink,addExtraCreateButton=false }) {
  const { breadcrumb, trimBreadcrumbAfter, clearBreadcrumb } = useBreadcrumb();
  return (
    <>
      <div className="container d-flex justify-content-between mt-4 align-items-center">

        {/* Breacrumb Component, showing e.g., /RGPV/B.Tech/CSE.. */}
        <nav aria-label="breadcrumb">
          <ol className="breadcrumb mb-0">
            <li className="breadcrumb-item " aria-current="page"><a href="/institutes" className="text-decoration-none"
              onClick={() => {
                clearBreadcrumb()
              }}
            >Home</a></li>
            {breadcrumb.map((item, idx) => (
              <li
                key={item.path}
                className={`breadcrumb-item ${idx === breadcrumb.length - 1 ? "active" : ""}`}
                aria-current={idx === breadcrumb.length - 1 ? "page" : undefined}
              >
                {idx === breadcrumb.length - 1 ? (
                  item.label
                ) : (
                  <a href={item.path} className="text-decoration-none" onClick={()=>{trimBreadcrumbAfter(item.path)}}>{item.label}</a>
                )}
              </li>
            ))}
          </ol>
        </nav>

        {/* Create form button, getting @POST link for the specific entity as prop */}
        <a href={createLink} className="btn btn-outline-primary">+</a>
      </div>
    </>
  );
}

export default Breadcrumb;
