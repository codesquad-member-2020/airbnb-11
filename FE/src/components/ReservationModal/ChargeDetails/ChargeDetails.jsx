import React from 'react';
import styled from 'styled-components'

const S = {};

S.ChargeDetails = styled.div`
  display: block;
  width: 400px;
  height: 20px;
  margin-top: 20px;
  margin-bottom: 20px;
`;

S.Title = styled.div`
  float: left;
  line-height: 20px;
  font-size: 15px;
  font-weight: bold;
`;

S.TotalCharge = styled.div`
  float: right;
  line-height: 20px;
  font-size: 15px;
  font-weight: bold;
`;

function ChargeDetails(props) {
  return (
      <S.ChargeDetails>
        <S.Title>합계</S.Title>
        <S.TotalCharge>{props.totalCharge}</S.TotalCharge>
      </S.ChargeDetails>
  );
}

export default ChargeDetails;