import { combineReducers } from "redux";
import dateReducer from './date/dateReducer'
import focusIdReducer from './focusIdReducer'
import adultCountReducer from './guest/adultCountReducer'
import childrenCountReducer from './guest/childrenCountReducer'
import infantsCountReducer from './guest/infantsCountReducer'

const rootReducer = combineReducers({
  dateReducer,
  focusIdReducer,
  adultCountReducer,
  childrenCountReducer,
  infantsCountReducer
});

export default rootReducer;
