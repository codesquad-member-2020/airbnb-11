import React from 'react';
import styled from 'styled-components'

const S = {};
S.ReservationButton = styled.div`
  position: relative;
  height: 44px;
  padding: 12px;
  border-radius: 12px;
  text-align: center;
`;

S.Button = styled.button`
  width: 104px;
  height: 44px;
  border-radius: 12px;
  color: white;
  font-size: 15px;
  font-weight: bold;
  background-image: linear-gradient(to right, #E61E4D 0%, #E31C5F 50%, #D70466 100%);
  margin-left: auto;
  margin-right: auto;

  &:hover, :active, :focus {
    cursor: pointer;
    outline: none;
  }
`;

function ReservationButton(props) {
  const onSearchButtonClick = e => {
    e.stopPropagation();
    props.onClick();
  }

  return (
    <S.ReservationButton>
      <S.Button onClick={onSearchButtonClick}>{props.contents}</S.Button>
    </S.ReservationButton>
  );
}

export default ReservationButton;