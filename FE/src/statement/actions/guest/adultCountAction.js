import { INCREASE_ADULT_COUNT, DECREASE_ADULT_COUNT } from '../../constants/guest/adultActionName'

const increase = () => {
  return {
    type: INCREASE_ADULT_COUNT,
  };
};

const decrease = () => {
  return {
    type: DECREASE_ADULT_COUNT
  };
};

export default {
  increase,
  decrease
};
