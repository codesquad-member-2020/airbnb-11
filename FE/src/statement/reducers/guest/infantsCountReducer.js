import { INCREASE_INFANTS_COUNT, DECREASE_INFANTS_COUNT } from "../../constants/guest/infantsActionName";

const maximumInfantsCount = 5;
const minimumInfantsCount = 0;

const infantsCountReducer = (state = 0, action) => {
  switch (action.type) {
    case INCREASE_INFANTS_COUNT: {
      if (state >= maximumInfantsCount) 
        return maximumInfantsCount;

      return state + 1;
    }
    case DECREASE_INFANTS_COUNT: {
      if (state <= minimumInfantsCount)
        return minimumInfantsCount;

      return state - 1;
    }
    default:
      return state;
  }
};

export default infantsCountReducer;
