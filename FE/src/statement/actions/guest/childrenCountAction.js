import { INCREASE_CHILDREN_COUNT, DECREASE_CHILDREN_COUNT } from '../../constants/guest/childrenActionName'

const increase = () => {
  return {
    type: INCREASE_CHILDREN_COUNT,
  };
};

const decrease = () => {
  return {
    type: DECREASE_CHILDREN_COUNT
  };
};

export default {
  increase,
  decrease
};
