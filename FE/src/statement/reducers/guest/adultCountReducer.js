import { INCREASE_ADULT_COUNT, DECREASE_ADULT_COUNT } from 'ActionNames/guest/adultActionName';

const maximumAdultCount = 16;
const minimumAdultCount = 0;

const adultCountReducer = (state = 0, action) => {
  switch (action.type) {
    case INCREASE_ADULT_COUNT: {
      if (state >= maximumAdultCount) 
        return maximumAdultCount;

      return state + 1;
    }
    case DECREASE_ADULT_COUNT: {
      if (state <= minimumAdultCount)
        return minimumAdultCount;

      return state - 1;
    }
    default:
      return state;
  }
};

export default adultCountReducer;
