import { SET_FOCUS_ID } from 'ActionNames/focusId/focusIdActionName'

const setFocusId = (focusId) => {
  return {
    type: SET_FOCUS_ID,
    payload: focusId
  };
};

export {
  setFocusId,
};
