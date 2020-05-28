import { SET_START_DATE, SET_END_DATE } from "../../constants/date/dateActionName";

const initialState = {
  startDate: null,
  startDateInfo: {
    year: null,
    month: null,
    day: null,
  },
  endDate: null,
  endDateInfo: {
    year: null,
    month: null,
    day: null,
  },
};

function dateReducer(state = initialState, action) {
  switch (action.type) {
    case SET_START_DATE: {
      let dateInfo = null;

      if (action.payload) {
        dateInfo = {
          year: action.payload._d.getFullYear(),
          month: action.payload._d.getMonth() + 1,
          day: action.payload._d.getDate(),
        };
      }

      return {
        ...state,
        startDate: action.payload,
        startDateInfo: dateInfo,
      };
    }
    case SET_END_DATE: {
      let dateInfo = null;

      if (action.payload) {
        dateInfo = {
          year: action.payload._d.getFullYear(),
          month: action.payload._d.getMonth() + 1,
          day: action.payload._d.getDate(),
        };
      }

      return {
        ...state,
        endDate: action.payload,
        endDateInfo: dateInfo,
      };
    }
    default:
      return state;
  }
}

export default dateReducer;