import { INCREASE_INFANTS_COUNT, DECREASE_INFANTS_COUNT } from 'ActionNames/guest/infantsActionName'

const increase = () => {
  return {
    type: INCREASE_INFANTS_COUNT,
  };
};

const decrease = () => {
  return {
    type: DECREASE_INFANTS_COUNT
  };
};

export default {
  increase,
  decrease
};
