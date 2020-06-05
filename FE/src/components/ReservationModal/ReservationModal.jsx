import React, { useState, useEffect } from "react";
import styled, { keyframes } from 'styled-components'
import { useHistory } from 'react-router-dom';
import { useSelector } from "react-redux";
import ChargePerDay from 'Components/ReservationModal/ChargePerDay/ChargePerDay'
import CheckInOutDate from 'Components/ReservationModal/CheckInOutDate/CheckInOutDate'
import GuestCount from 'Components/ReservationModal/GuestCount/GuestCount'
import ChargeDetails from 'Components/ReservationModal/ChargeDetails/ChargeDetails'
import ReservationButton from 'Components/ReservationModal/ReservationButton/ReservationButton'
import fetchResuest from "../../utils/fetchRequest"
import calcDiffDate from "../../utils/calcDateDiff"

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
  animation-duration: 0.3s;
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

function ReservationModal(props) {
  const [searchResult, setSearchResult] = useState(undefined);
  const { adultCount, childrenCount, infantsCount } = useSelector(
    ({ guestCountReducer }) => guestCountReducer
  );
  const { startDateInfo, endDateInfo } = useSelector(
    ({ dateReducer }) => dateReducer
  );

  const history = useHistory();

  function onCloseButtonClick() {
    history.goBack();
  }

  function onReservationButtonClick() {
    alert("예약이 완료되었습니다.")
    history.goBack();
  }

  useEffect(() => {
    const cvtStartMonth = startDateInfo.month < 10 ? '0' + startDateInfo.month : startDateInfo.month;
    const cvtStartDay = startDateInfo.day < 10 ? '0' + startDateInfo.day : startDateInfo.day;
    const cvtEndMonth = endDateInfo.month < 10 ? '0' + endDateInfo.month : endDateInfo.month;
    const cvtEndDay = endDateInfo.day < 10 ? '0' + endDateInfo.day : endDateInfo.day;

    const requestInfo = {
      adult: adultCount,
      children: childrenCount,
      infants: infantsCount,
      checkIn: startDateInfo.year + "-" + cvtStartMonth + "-" + cvtStartDay,
      checkOut: endDateInfo.year + "-" + cvtEndMonth + "-" + cvtEndDay,
      roomId: props.match.params.roomId
    };

    let urlInfo = '';
    const requestInfoKeys = Object.keys(requestInfo);
    requestInfoKeys.map((data, index) => {
      urlInfo += data + "=" + requestInfo[data] + "&";
    });

    const url = process.env.REACT_APP_DETAIL_API + "?" + urlInfo;

    fetchResuest(url, "GET")
      .then((result) => result.json())
      .then((data) => {
        setSearchResult(data);
        console.log(data);
    });
  }, []);

  return (
    <>
      {searchResult && (
        <S.ReservationModal>
          <S.ReservationModalWrap>
            <S.CloseButton onClick={onCloseButtonClick}>X</S.CloseButton>
            <S.ChargeWrap>
              <ChargePerDay
                chargePerDay={
                  "₩" + searchResult.dailyPrice.toLocaleString() + "원" + "/1박"
                }
              />
            </S.ChargeWrap>
            <CheckInOutDate
              dateInfo={
                startDateInfo.year +
                "-" +
                startDateInfo.month +
                "-" +
                startDateInfo.day +
                "      →      " +
                endDateInfo.year +
                "-" +
                endDateInfo.month +
                "-" +
                endDateInfo.day
              }
            />
            <GuestCount guestInfo={"게스트 " + searchResult.guestCount + "명" + ((searchResult.infantsCount > 0) ? ', ' + "유아 " + searchResult.infantsCount + "명" : "")} />
            <ChargeDetails
              title="청소비"
              totalCharge={"₩" + searchResult.commission.toLocaleString() + "원"}
            />
            <ChargeDetails
              title="커미션"
              totalCharge={"₩" + searchResult.cleaningPrice.toLocaleString() + "원"}
            />
            <ChargeDetails
              title="서비스"
              totalCharge={"₩" + searchResult.servicePrice.toLocaleString() + "원"}
            />
            <ChargeDetails
              title="합계"
              totalCharge={
                "₩" +
                (
                  calcDiffDate(
                    {
                      year: startDateInfo.year,
                      month: startDateInfo.month,
                      day: startDateInfo.day,
                    },
                    {
                      year: endDateInfo.year,
                      month: endDateInfo.month,
                      day: endDateInfo.day,
                    }
                  ) * searchResult.dailyPrice
                ).toLocaleString() +
                "원"
              }
            />
            <ReservationButton
              contents="예약하기"
              onClick={onReservationButtonClick}
            />
          </S.ReservationModalWrap>
        </S.ReservationModal>
      )}
    </>
  );
}

export default ReservationModal;
