import React from 'react';
import {
  BrowserRouter as Router,
  Route
} from "react-router-dom";

import SearchResult from 'Components/SearchResult/SearchResult'
import ReservationModal from 'Components/ReservationModal/ReservationModal'

function SearchresultRouter() {
  return (
    <Router>
      <Route path="/searchresult" component={SearchResult} />
      <Route path="/searchresult/reservationmodal" component={ReservationModal} />
    </Router>
  );
}

export default SearchresultRouter;
