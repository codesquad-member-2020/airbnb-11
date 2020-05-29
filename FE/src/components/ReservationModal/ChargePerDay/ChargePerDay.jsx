import React from 'react';
import styled, { keyframes } from 'styled-components'

const S = {};

S.ChargePerDay = styled.div`
  display: block;
  margin-top: 20px;
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
`;

function ChargePerDay(props) {
  return (
      <S.ChargePerDay>
        {props.chargePerDay}
      </S.ChargePerDay>
  );
}

export default ChargePerDay;
