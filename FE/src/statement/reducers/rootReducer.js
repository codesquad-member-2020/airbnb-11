import { combineReducers } from "redux";
import dateReducer from 'Reducers/date/dateReducer'
import focusIdReducer from 'Reducers/focusId/focusIdReducer'
import guestCountReducer from 'Reducers/guest/guestCountReducer'

const rootReducer = combineReducers({
  dateReducer,
  focusIdReducer,
  guestCountReducer,
});

export default rootReducer;
