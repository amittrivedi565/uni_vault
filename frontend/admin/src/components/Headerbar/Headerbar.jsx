function Headerbar({
    serviceName,
    entityName
}) {
    return (
        <>
            <div className="container border-bottom d-flex justify-content-between mt-4">
                <div>
                    <b>{serviceName}</b>
                    &nbsp;
                    |
                    &nbsp;
                    <i>{entityName}</i>
                </div>
                <p className="text-muted fw-light">Service Panel</p>
            </div>
        </>
    )
}

export default Headerbar;