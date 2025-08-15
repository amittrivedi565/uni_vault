import Navbar from "../components/Navbar/Navbar"

function Login() {
    return (
        <>
            <Navbar />
            <div className="d-flex justify-content-center bg-light vh-100 align-items-center">
                <div className="container-lg border rounded p-4 bg-white" style={{ width: "450px", maxHeight: "50%" }}>
                    <div className="mb-3">
                        <h2>Login</h2>
                        <label className="form-label mt-3" htmlFor="email">Enter Your Username</label>
                        <input type="email" className="form-control" placeholder="abc@univault.com" />

                        <label className="form-label mt-3" htmlFor="password">Enter Your Password</label>
                        <input type="password" className="form-control" />
                        <input class="btn btn-primary mt-4  w-100" type="submit" value="Log In"></input>

                        <p className="text-center text-muted mt-3">
                            Having trouble logging in? Contact the <strong>Service Provider</strong>.
                        </p>
                    </div>


                </div>
            </div>
        </>
    )
}

export default Login