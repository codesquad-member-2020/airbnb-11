import React from 'react';
import styled from 'styled-components'

const S = {};

S.GuestCount = styled.div`
  display: block;
  width: 400px;
  height: 57px;
  margin-top: 20px;
  margin-bottom: 20px;
`;

S.Title = styled.div`
  display: block;
  line-height: 20px;
  font-size: 15px;
  font-weight: bold;
`;

S.GuestInfo = styled.div`
  height: 35px;
  font-size: 15px;
  border: 1px solid lightgray;
  line-height: 35px;
  text-align: center;
`;

function GuestCount(props) {
  return (
      <S.GuestCount>
        <S.Title>인원</S.Title>
        <S.GuestInfo>{props.guestInfo}</S.GuestInfo>
      </S.GuestCount>
  );
}

export default GuestCount;