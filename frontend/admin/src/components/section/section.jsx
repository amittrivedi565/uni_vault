import './section.css'

function section({children}){
    return(<>
        <div className="section-container">{children}</div>
    </>)
}

export default section;