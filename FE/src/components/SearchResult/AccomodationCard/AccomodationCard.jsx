import React from 'react';
import styled, { css } from 'styled-components'

const S = {};

S.AccomodationCard = styled.div`
  width: 300px;
  height: 300px;

  &:hover {
    cursor: pointer;
  }
`;

S.Image = styled.div`
  width: 300px;
  height: 200px;
  background-image: url(${props => props.src});
  background-size: 100% 100%;
`;

S.TypeInfo = styled.div`
  width: 300px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  font-size: 16px;
`;

S.Title = styled.div`
  width: 300px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  font-size: 16px;
`;

S.ChargePerDay = styled.div`
  width: 300px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  font-size: 16px;
`;

S.TotalCharge = styled.div`
  width: 300px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  font-size: 14px;
`;

function AccomodationCard(props) {
  const onAccomodationCardClick = e => {
    e.stopPropagation();
    props.onClick();
  }

  return (
      <S.AccomodationCard onClick={onAccomodationCardClick}>
        <S.Image src={props.src} />
        <S.TypeInfo>{props.typeInfo}</S.TypeInfo>
        <S.Title>{props.title}</S.Title>
        <S.ChargePerDay>{props.chargePerDay}</S.ChargePerDay>
        <S.TotalCharge>{props.totalCharge}</S.TotalCharge>
      </S.AccomodationCard>
  );
}

export default AccomodationCard;
