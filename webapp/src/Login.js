import React, { useEffect, useState } from 'react';
import './Login.css';
import { useNavigate } from "react-router-dom";
import { Navigate } from "react-router-dom";


const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };
  // const handleNavigation = useHandleNavigation();
  const handleLogin = async (event) => {

 
    event.preventDefault(username, password);
    const url = 'http://localhost:8080/login?' + new URLSearchParams({email: username, password: password}).toString()
    try {
        const response = await fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
            // 'Authorization': 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzb2Jha2FAZW1haWwuY29tIiwiaWF0IjoxNzEyNDk0MDg0LCJleHAiOjE3MTI0OTc2ODR9.lTb7j1IweBfCcalwH7SqPeh17bZ7yNbiONmA7u1umD0'
          },
          body: JSON.stringify({ username, password })
        });
        if (!response.ok) {
          setIsLoggedIn(false)
          throw new Error('Login failed');
        }
        console.log('Login successful');
      setIsLoggedIn(true)
        // history()
        // You can handle the successful login response here
      } catch (error) {
        console.error('Login failed:', error.message);
        // You can handle login failures here
      }
      
  };

  if (isLoggedIn) {
    return <Navigate to="/chat"/>
  } else {
  return (
    <div className="login-container">
      <h2>Login</h2>
      <form className="form-container" onSubmit={handleLogin}>
        <div className="input-container">
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={handleUsernameChange}
          />
        </div>
        <div className="input-container">
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={handlePasswordChange}
          />
        </div>
        <button className='button' type="submit">Login</button>
      </form>
    </div>
  );
  }
};

export default Login;