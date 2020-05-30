import React from 'react';
import styled from 'styled-components'

const S = {};

S.CheckInOutDate = styled.div`
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

S.DateInfo = styled.div`
  height: 35px;
  font-size: 15px;
  border: 1px solid lightgray;
  line-height: 35px;
  text-align: center;
`;

function CheckInOutDate(props) {
  return (
      <S.CheckInOutDate>
        <S.Title>날짜</S.Title>
        <S.DateInfo>{props.dateInfo}</S.DateInfo>
      </S.CheckInOutDate>
  );
}

export default CheckInOutDate;