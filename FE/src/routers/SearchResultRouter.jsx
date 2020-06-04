import React from 'react';
import {
  BrowserRouter as Router,
  Route
} from "react-router-dom";

import SearchResult from 'Components/SearchResult/SearchResult'
import ReservationModal from 'Components/ReservationModal/ReservationModal'

function SearchresultRouter(props) {
  return (
    <Router>
      <Route path="/searchresult/:pageNumber" component={SearchResult} />
      <Route path="/searchresult/:pageNumber/reservationmodal" component={ReservationModal} />
    </Router>
  );
}

export default SearchresultRouter;
