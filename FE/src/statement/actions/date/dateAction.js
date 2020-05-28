import { SET_START_DATE, SET_END_DATE } from '../../constants/date/dateActionName'

const setStartDate = (startDate) => {
  return {
    type: SET_START_DATE,
    payload: startDate
  };
};

const setEndDate = (endDate) => {
  return {
    type: SET_END_DATE,
    payload: endDate
  };
};

export {
  setStartDate,
  setEndDate
};
