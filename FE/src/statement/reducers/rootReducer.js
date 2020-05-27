import { combineReducers } from "redux";
import dateReducer from './date/dateReducer'
import focusIdReducer from './focusIdReducer'

const rootReducer = combineReducers({
  dateReducer,
  focusIdReducer,
});

export default rootReducer;
