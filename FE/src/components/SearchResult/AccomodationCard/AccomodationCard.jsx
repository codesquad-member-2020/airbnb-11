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
  border-radius: 12px;
`;

S.CountryHost = styled.div`
  float: left;
  width: 300px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  font-size: 16px;
`;

S.IsSuperHost = styled.div`
  position: relative;
  float: left;
  width: 80px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  font-size: 14px;
  font-weight: bold;
  border: 2px solid black;
  border-radius: 12px;
  text-align: center;
`;

S.Country = styled.div`
  position: relative;
  float: left;
  width: 90px;
  padding-left: 5px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 19px;
  font-size: 16px;
  text-align: left;
`;

S.Rating = styled.div`
  float: right;
  right: 0;
  width: 55px;
  height: 29px;
  line-height: 15px;
  font-size: 16px;
`;

S.RatingIcon = styled.div`
  position: relative;
  float: left;
  width: 20px;
  height: 29px;
  line-height: 24px;
  font-size: 20px;
  text-align: center;
  color: red;
`;

S.RatingRate = styled.div`
  position: relative;
  float: left;
  width: 35px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 19px;
  font-size: 16px;
  text-align: center;
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
  font-weight: bold;
`;

S.ChargePerDay = styled.div`
  float: left;
  display: relative;
  width: 300px;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  font-size: 16px;
`;

S.Charge = styled.div`
  float: left;
  padding-top: 5px;
  padding-bottom: 5px;
  line-height: 15px;
  font-size: 16px;
  font-weight: bold;
`;

S.PerDay = styled.div`
  float: left;
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
  color: gray;
`;

function AccomodationCard(props) {
  const onAccomodationCardClick = e => {
    e.stopPropagation();
    props.onClick();
  }

  return (
      <S.AccomodationCard onClick={onAccomodationCardClick}>
        <S.Image src={props.src} />
        <S.CountryHost>
          {props.isHost && <S.IsSuperHost>슈퍼호스트</S.IsSuperHost>}
          <S.Country>{props.country}</S.Country>
          <S.Rating>
            <S.RatingIcon>★</S.RatingIcon>
            <S.RatingRate>{props.rating}</S.RatingRate>
          </S.Rating>
        </S.CountryHost>
        <S.Title>{props.title}</S.Title>
        <S.ChargePerDay>
          <S.Charge>{props.chargePerDay}</S.Charge>
          <S.PerDay>/박</S.PerDay>
        </S.ChargePerDay>
        <S.TotalCharge>{props.totalCharge}</S.TotalCharge>
      </S.AccomodationCard>
  );
}

export default AccomodationCard;
