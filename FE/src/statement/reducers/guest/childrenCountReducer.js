import { INCREASE_CHILDREN_COUNT, DECREASE_CHILDREN_COUNT } from '../../constants/guest/childrenActionName';

const maximumChildCount = 5;
const minimumChildCount = 0;

const childrenCountReducer = (state = 0, action) => {
  switch (action.type) {
    case INCREASE_CHILDREN_COUNT: {
      if (state >= maximumChildCount) 
        return maximumChildCount;

      return state + 1;
    }
    case DECREASE_CHILDREN_COUNT: {
      if (state <= minimumChildCount)
        return minimumChildCount;

      return state - 1;
    }
    default:
      return state;
  }
};

export default childrenCountReducer;
