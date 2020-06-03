import React from 'react';
import styled, { keyframes } from 'styled-components'
import { withRouter } from 'react-router-dom';
import ChargePerDay from 'Components/ReservationModal/ChargePerDay/ChargePerDay'
import CheckInOutDate from 'Components/ReservationModal/CheckInOutDate/CheckInOutDate'
import GuestCount from 'Components/ReservationModal/GuestCount/GuestCount'
import ChargeDetails from 'Components/ReservationModal/ChargeDetails/ChargeDetails'
import ReservationButton from 'Components/ReservationModal/ReservationButton/ReservationButton'

const S = {};

const slidein = keyframes `
  0% { opacity: 0 }
  100% { opacity: 1; }
`;

S.ReservationModal = styled.div`
  position: fixed;
  left: 0px;
  top: 0px;
  width: 100%;
  height: 100%;
  background-color: red;
  z-index: 100;
  background-color: rgba(0, 0, 0, 0.8);
  animation-duration: 0.7s;
  animation-name: ${slidein};
`;

S.CloseButton = styled.div `
  position: relative;
  top: 0;
  right: 0;
  width: 30px;
  height: 30px;
  color: black;
  float: right;
  font-size: 24px;
  font-weight: bold;
  background-color: white;
  color: gray;
  user-select: none;

  &:hover {
    cursor: pointer;
    color: black;
  }
`;

S.ReservationModalWrap = styled.div`
  position: relative;
  width: 400px;
  height: 350px;
  padding: 15px;
  margin: 0 auto;
  z-index: 1;
  border: 2px solid white;
  border-radius: 12px;
  background-color: white;
  top: 50%;
  transform: translateY(-50%);
`;

S.ChargeWrap = styled.div`
  width: 400px;
  height: 50px;
`;

function ReservationModal({ history }) {
  function onCloseButtonClick() {
    history.goBack();
  }

  function onReservationButtonClick() {
    alert("예약이 완료되었습니다.")
    history.goBack();
  }

  return (
      <S.ReservationModal>
        <S.ReservationModalWrap>
          <S.CloseButton onClick={onCloseButtonClick}>X</S.CloseButton>
          <S.ChargeWrap>
            <ChargePerDay chargePerDay="13,620원"/>
          </S.ChargeWrap>
          <CheckInOutDate dateInfo="2020.06.02    →    2020.06.17" />
          <GuestCount guestInfo="게스트 1명" />
          <ChargeDetails totalCharge="204,294원"/>
          <ReservationButton contents="예약하기" onClick={onReservationButtonClick}/>
        </S.ReservationModalWrap>
      </S.ReservationModal>
  );
}

export default withRouter(ReservationModal);
