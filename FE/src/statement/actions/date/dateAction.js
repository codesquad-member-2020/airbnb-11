//import { SET_START_DATE, SET_END_DATE } from '../../constants/dateActionName'

const SET_START_DATE = "DATE/SET_START_DATE";
const SET_END_DATE = "DATE/SET_END_DATE";

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
