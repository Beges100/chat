import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Login from './Login';
import App from './App'; // Import your ChatPage component

const Home = () => {
  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Login} />
        <Route path="/chat" component={App} />
      </Switch>
    </Router>
  );
};

export default Home;