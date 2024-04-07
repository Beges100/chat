import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import './Login.css';

const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = username('');

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };


  const handleSubmit = async (event) => {
    event.preventDefault();
    try {
        // const response = await fetch('http://localhost:8080/register', {
        //   method: 'POST',
        //   headers: {
        //     'Content-Type': 'application/json'
        //   },
        //   body: JSON.stringify({ username, password , email})
        // });
        // if (!response.ok) {
        //   throw new Error('Login failed');
        // }
        console.log('Login successful');

        history.push('/chat');
        // You can handle the successful login response here
      } catch (error) {
        console.error('Login failed:', error.message);
        // You can handle login failures here
      }
      
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form className="form-container" onSubmit={handleSubmit}>
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
        <button className='button' type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;