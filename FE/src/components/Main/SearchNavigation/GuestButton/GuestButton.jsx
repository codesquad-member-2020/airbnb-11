import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import styled from "styled-components";
import SearchNaviButton from "../CommonComponent/SearchNaviButton";

import adultCountAction from "../../../../statement/actions/guest/adultCountAction";
import childrenCountAction from "../../../../statement/actions/guest/childrenCountAction";
import infantsCountAction from "../../../../statement/actions/guest/infantsCountAction";

const TYPE_ADULT = "TYPE/ADULT";
const TYPE_CHILDREN = "TYPE/CHILDREN";
const TYPE_INFANTS = "TYPE/INFANTS";

const S = {};
S.GuestButton = styled.div`
  width: 250px;
  height: 70px;
`;

S.GuestArea = styled.div`
  position: absolute;
  top: 72px;
  right: 130px;
  width: 354px;
  height: 220px;
  padding: 16px 24px;
  display: ${(props) => (props.focus ? "block" : "none")};
  background-color: white;
  border-bottom-left-radius: 12px;
  border-bottom-right-radius: 12px;
  box-shadow: rgba(0, 0, 0, 0.12) 0px 5px 12px;
  border-color: rgb(247, 247, 247);
`;

S.Guest = styled.div`
  position: relative;
  width: 337px;
  height: 38px;
  padding: 16px 4px 16px 0px;
`;

S.GuestDescription = styled.div`
  float: left;
  width: 240px;
  font-size: 16px;
  font-weight: bold;
  line-height: 20px;
`;

S.GuestType = styled.div`
  width: 240px;
  font-size: 16px;
  font-weight: bold;
  line-height: 20px;
`;

S.GuestTypeDescription = styled.p`
  position: relative;
  width: 240px;
  font-size: 14px;
  line-height: 18px;
  color: #717171;
`;

S.GuestController = styled.div`
  position: relative;
  height: 32px;
  float: left;
  vertical-align: center;
`;

S.Count = styled.div`
  position: relative;
  float: left;
  padding-left: 5px;
  padding-right: 5px;
  line-height: 32px;
  font-size: 16px;
`;

S.MinusButton = styled.div`
  background-color: red;
  width: 32px;
  height: 32px;
  float: left;

  &:hover {
    cursor: pointer;
  }
`;

S.PlusButton = styled.div`
  background-color: red;
  width: 32px;
  height: 32px;
  float: left;

  &:hover {
    cursor: pointer;
  }
`;

function GuestButton(props) {
  const adultCount = useSelector(({adultCountReducer}) => adultCountReducer);
  const childrenCount = useSelector(({childrenCountReducer}) => childrenCountReducer);
  const infantsCount = useSelector(({infantsCountReducer}) => infantsCountReducer);

  const dispatch = useDispatch();

  function onDropDownAreaClick(e) {
    e.stopPropagation();
  }

  function onPlusButtonClick(type) {
    let increaseFunc = null;

    switch (type) {
      case TYPE_ADULT: {
        increaseFunc = adultCountAction.increase;
        break;
      }
      case TYPE_CHILDREN: {
        increaseFunc = childrenCountAction.increase;
        break;
      }
      case TYPE_INFANTS: {
        increaseFunc = infantsCountAction.increase;
        break;
      }
      default: {
        break;
      }
    }

    dispatch(increaseFunc());
  }

  function onMinusButtonClick(type) {
    let decreaseFunc = null;

    switch (type) {
      case TYPE_ADULT: {
        decreaseFunc = adultCountAction.decrease;
        break;
      }
      case TYPE_CHILDREN: {
        decreaseFunc = childrenCountAction.decrease;
        break;
      }
      case TYPE_INFANTS: {
        decreaseFunc = infantsCountAction.decrease;
        break;
      }
      default: {
        break;
      }
    }

    dispatch(decreaseFunc());
  }

  return (
    <S.GuestButton onClick={onDropDownAreaClick}>
      <SearchNaviButton
        title={props.title}
        contents={(adultCount + childrenCount + infantsCount) > 0 ? 
          ((adultCount + childrenCount > 0) ? "게스트 " + (adultCount + childrenCount) + "명" : "") +
          ((infantsCount > 0) ? ', ' + "유아 " + (infantsCount) + "명" : "")
          : props.contents}
        onClick={props.onClick}
        focus={props.focus}
        customKey={props.customKey}
      />
      <S.GuestArea focus={props.focus}>
        <S.Guest>
          <S.GuestDescription>
            <S.GuestType>성인</S.GuestType>
            <S.GuestTypeDescription>만 13세 이상</S.GuestTypeDescription>
          </S.GuestDescription>
          <S.GuestController>
            <S.MinusButton onClick={() => onMinusButtonClick(TYPE_ADULT)} />
            <S.Count>{adultCount}</S.Count>
            <S.PlusButton onClick={() => onPlusButtonClick(TYPE_ADULT)} />
          </S.GuestController>
        </S.Guest>
        <S.Guest>
          <S.GuestDescription>
            <S.GuestType>어린이</S.GuestType>
            <S.GuestTypeDescription>2~12세</S.GuestTypeDescription>
          </S.GuestDescription>
          <S.GuestController>
            <S.MinusButton onClick={() => onMinusButtonClick(TYPE_CHILDREN)} />
            <S.Count>{childrenCount}</S.Count>
            <S.PlusButton onClick={() => onPlusButtonClick(TYPE_CHILDREN)} />
          </S.GuestController>
        </S.Guest>
        <S.Guest>
          <S.GuestDescription>
            <S.GuestType>유아</S.GuestType>
            <S.GuestTypeDescription>2세 미만</S.GuestTypeDescription>
          </S.GuestDescription>
          <S.GuestController>
            <S.MinusButton onClick={() => onMinusButtonClick(TYPE_INFANTS)} />
            <S.Count>{infantsCount}</S.Count>
            <S.PlusButton onClick={() => onPlusButtonClick(TYPE_INFANTS)} />
          </S.GuestController>
        </S.Guest>
      </S.GuestArea>
    </S.GuestButton>
  );
}

export default GuestButton;
