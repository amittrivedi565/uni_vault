import { useState } from "react";
import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie"; // install with: npm install js-cookie
import "./login.css";
import Navbar from "../../layouts/navbar/navbar";
import Footer from "../../layouts/footer/footer";

function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const res = await fetch("http://localhost:8011/auth/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ username, password })
            });

            if (!res.ok) {
                throw new Error("Login failed");
            }

            const token = await res.text(); // token is a plain string

            // ✅ Store token in cookies
            Cookies.set("token", token, { expires: .5 }); // expires in 1 day

            // ✅ Redirect to dashboard (or desired route)
            navigate("/institutes/get");
        } catch (err) {
            console.error("Login error:", err);
            alert("Invalid credentials!");
        }
    };

    return (
        <>
            <Navbar />
            <div className="login-container">
                <div className="login-input-wrapper">
                    <div className="login-input-heading">
                        <h1>Admin Login</h1>
                    </div>
                    <div className="login-input">
                        <label htmlFor="username">Enter Username:</label>
                        <input
                            type="text"
                            placeholder="Username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                        />
                    </div>
                    <div className="login-input">
                        <label htmlFor="password">Enter Password:</label>
                        <input
                            type="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                    </div>
                    <button type="submit" onClick={handleLogin} className="login-btn">
                        Sign In
                    </button>
                </div>
            </div>
            <Footer />
        </>
    );
}

export default Login;
