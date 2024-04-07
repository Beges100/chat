import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
// import Home from './Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Login';
import ChatApp from './App';
import Register from './register/Register';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
  <Router> {/* Wrap your components with Router */}
      <Routes>
      <Route path="/" Component={Register} />
      <Route path="/chat" Component={ChatApp} />
      <Route path="/login" Component={Login} />
     
      </Routes>
    </Router>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
