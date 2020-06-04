import {
  INCREASE_ADULT_COUNT,
  DECREASE_ADULT_COUNT,
  INCREASE_CHILDREN_COUNT,
  DECREASE_CHILDREN_COUNT,
  INCREASE_INFANTS_COUNT,
  DECREASE_INFANTS_COUNT,
} from "ActionNames/guest/guestActionName";

const minimumCount = 0;
const maximumAdultCount = 16;
const maximumChildrenCount = 5;
const maximumInfantsCount = 5;

const initialState = {
  adultCount: 0,
  childrenCount: 0,
  infantsCount: 0,
};

const guestCountReducer = (state = initialState, action) => {
  switch (action.type) {
    case INCREASE_ADULT_COUNT: {
      if (state.adultCount >= maximumAdultCount) return state;

      return {
        ...state,
        adultCount: state.adultCount + 1,
      };
    }
    case DECREASE_ADULT_COUNT: {
      if (
        state.adultCount <= minimumCount ||
        (state.adultCount === 1 &&
          (state.childrenCount > 0 || state.infantsCount > 0))
      )
        return state;

      return {
        ...state,
        adultCount: state.adultCount - 1,
      };
    }
    case INCREASE_CHILDREN_COUNT: {
      if (state.childrenCount >= maximumChildrenCount) return state;

      const cvtAdultCount = (!state.childrenCount && !state.adultCount) ? 1 : state.adultCount;

      return {
        ...state,
        adultCount: cvtAdultCount,
        childrenCount: state.childrenCount + 1,
      };
    }
    case DECREASE_CHILDREN_COUNT: {
      if (state.childrenCount <= minimumCount) return state;

      return {
        ...state,
        childrenCount: state.childrenCount - 1,
      };
    }
    case INCREASE_INFANTS_COUNT: {
      if (state.infantsCount >= maximumInfantsCount) return state;

      const cvtAdultCount = (!state.infantsCount && !state.adultCount) ? 1 : state.adultCount;

      return {
        ...state,
        adultCount: cvtAdultCount,
        infantsCount: state.infantsCount + 1,
      };
    }
    case DECREASE_INFANTS_COUNT: {
      if (state.infantsCount <= minimumCount) return state;

      return {
        ...state,
        infantsCount: state.infantsCount - 1,
      };
    }
    default:
      return state;
  }
};

export default guestCountReducer;
