import React from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

import Main from 'Components/Main/Main';
//import SearchResult from 'Components/SearchResult/SearchResult'
import SearchResultRouter from 'Routers/SearchResultRouter'

function AppRouter() {
  return (
    <Router>
      <Route exact={true} path="/" component={Main} />
      <Route path="/searchresult" component={SearchResultRouter} />
    </Router>
  );
}

export default AppRouter;