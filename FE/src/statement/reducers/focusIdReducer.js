import { SET_FOCUS_ID } from "../constants/focusIdActionName";

const focusIdReducer = (state = null, action) => {
  switch (action.type) {
    case SET_FOCUS_ID:
      return action.payload;
    default:
      return state;
  }
};

export default focusIdReducer;
