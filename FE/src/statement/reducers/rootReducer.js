import { combineReducers } from "redux";
import dateReducer from 'Reducers/date/dateReducer'
import focusIdReducer from 'Reducers/focusId/focusIdReducer'
import adultCountReducer from 'Reducers/guest/adultCountReducer'
import childrenCountReducer from 'Reducers/guest/childrenCountReducer'
import infantsCountReducer from 'Reducers/guest/infantsCountReducer'

const rootReducer = combineReducers({
  dateReducer,
  focusIdReducer,
  adultCountReducer,
  childrenCountReducer,
  infantsCountReducer
});

export default rootReducer;
