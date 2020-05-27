import { combineReducers } from "redux";
import dateReducer from './date/dateReducer'

const rootReducer = combineReducers({
  dateReducer,
});

export default rootReducer;
