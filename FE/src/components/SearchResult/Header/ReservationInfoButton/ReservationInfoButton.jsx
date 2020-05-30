import React from 'react';
import styled from 'styled-components'
import TextButton from 'Components/Common/TextButton/TextButton'
import RadiusTextButton from 'Components/Common/RadiusTextButton/RadiusTextButton'

const S = {};
S.ReservationInfoButton = styled.div`
  width: 1200px;
  height: 60px;
  float: left;
  display: relative;
`;

S.ReservationInfoContentWrap = styled.div`
  width: fit-content;
  height: 60px;
  display: flex;
  margin: 0 auto;

  box-shadow: rgba(0, 0, 0, 0.18) 0px 1px 2px;
  border-color: rgb(247, 247, 247);
  border-radius: 12px;
  border-style: solid;
  border-width: 1px;
  border-image: initial;

  &:hover {
    cursor: pointer;
    box-shadow: rgba(0, 0, 0, 0.12) 0px 2px 8px;
  }
`;

S.Date = styled.div`
  font-size: 14px;
  line-height: 60px;
  padding-left: 20px;
  padding-right: 20px;
  font-weight: bold;
`;

S.Guest = styled.div`
  font-size: 14px;
  line-height: 60px;
  padding-left: 20px;
  padding-right: 20px;
  font-weight: bold;
`;

S.SearchImage = styled.div`
  width: 26px;
  height: 26px;
  background-image: url("http://dev-angelo.dlinkddns.com/glass.png");
  background-size: 100% 100%;
  margin-left: 20px;
  margin-right: 20px;
  margin-top: 17px;
  margin-bottom: 17px;
`;


function ReservationInfoButton(props) {
  return (
      <>
        <S.ReservationInfoButton>
          <S.ReservationInfoContentWrap>
            <S.Date>{props.date}</S.Date>
            <S.Guest>{props.guest}</S.Guest>
            <S.SearchImage></S.SearchImage>
          </S.ReservationInfoContentWrap>
        </S.ReservationInfoButton>
      </>
  );
}

export default ReservationInfoButton;