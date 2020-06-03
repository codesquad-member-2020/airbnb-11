import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import Main from '../components/Main/Main';
import SearchResult from '../components/SearchResult/SearchResult'

function AppRouter() {
  return (
    <Router>
      <Switch>
        <Route exact={true} path="/" component={Main} />
        <Route path="/searchresult" component={SearchResult} />
      </Switch>
    </Router>
  );
}

export default AppRouter;