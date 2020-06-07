import React, { useState, useEffect } from "react";
import styled, { css } from "styled-components";
import { useSelector } from "react-redux";
import { useHistory } from 'react-router-dom';
import AccomodationCard from "Components/SearchResult/AccomodationCard/AccomodationCard";
import Caution from "Components/SearchResult/Caution/Caution";
import ResultSummary from "Components/SearchResult/ResultSummary/ResultSummary";
import Header from "Components/SearchResult/Header/Header";
import fetchResuest from "../../utils/fetchRequest"
import Map from "Components/SearchResult/Map/Map";
import calcDiffDate from "../../utils/calcDateDiff"
import IconTextButton from './IconTextButton/IconTextButton'
import Pagination from "react-pagination-js";
import "react-pagination-js/dist/styles.css"; // import css

const S = {};

S.SearchResult = styled.div`
  position: relative;
  width: 1560px;
  margin: 0 auto;
`;

S.SearchResultLeft = styled.div`
  position: relative;
  margin-top: 100px;
  width: ${props => props.isMapVisible ? "780" : "1560"}px;
  float: left;
  overflow: hidden;
`;

S.SearchResultContentWrap = styled.div`
  position: relative;
  width: 100%;
  margin: 0 auto;
`;

S.AccomodationCardGrid = styled.div`
  width: 100%;
  display: grid;
  margin-top: 50px;
  grid-template-rows: 300px;
  grid-column-gap: 15px;
  grid-row-gap: 40px;
  grid-template-columns: ${props => props.isMapVisible ? "repeat(2, minmax(300px, 1fr))" : "repeat(5, minmax(300px, 1fr))"};
`;

S.LoadingImage = styled.div`
  width: 150px;
  height: 125px;
  background-image: url("https://cdn.clipart.email/4b453b9d5509587f169c211be6908ca9_loading-gif-png-picture-654600-loading-gif-png_600-450.gif");
  background-size: 100% 100%;
  margin: 0 auto;
  margin-top: 400px;
`;

S.PagingWrap = styled.div`
  width: 500px;
  margin: 0 auto;
  margin-top: 50px;
  margin-bottom: 50px;
  display: flex;
  justify-content: center;

`;

function SearchResult(props) {
  const history = useHistory();

  const [searchResult, setSearchResult] = useState(undefined);
  const [isMapVisible, setIsMapVisible] = useState(true);
  const { adultCount, childrenCount, infantsCount } = useSelector(
    ({ guestCountReducer }) => guestCountReducer
  );
  const { startDateInfo, endDateInfo } = useSelector(
    ({ dateReducer }) => dateReducer
  );
  const [centerPosition, setCenterPosition] = useState([0, 0]);
  const [mapMarkers, setMapMarkers] = useState(undefined);
  const [currentPage, setCurrentPage] = useState(1);

  function onAccomodationCardClick(roomId) {
    history.push("/searchresult/" + props.match.params.pageNumber + "/reservationmodal/" + roomId);
  }

  function onPositionClick(position) {
    setMapMarkers(undefined);
    setCenterPosition(position);

    const requestInfo = {
      adult: adultCount,
      children: childrenCount,
      infants: infantsCount,
      checkIn: startDateInfo.year + "-" + startDateInfo.month + "-" + startDateInfo.day,
      checkOut: endDateInfo.year + "-" + endDateInfo.month + "-" + endDateInfo.day,
      latitude: position[0] + 0.0027,
      longitude: position[1] - 0.0283
    };

    let urlInfo = '';
    const requestInfoKeys = Object.keys(requestInfo);
    requestInfoKeys.map((data, index) => {
      urlInfo += data + "=" + requestInfo[data] + "&";
    });

    const url = process.env.REACT_APP_SEARCH_NEAR_API + "?" + urlInfo;

    fetchResuest(url, "GET")
      .then((result) => result.json())
      .then((data) => {
        setMapMarkers(data);
    });
  }

  function onMapOnClick() {
    setIsMapVisible(true);
  }

  function onCloseButtonClick() {
    setIsMapVisible(false);
  }

  function changeCurrentPage(numPage) {
    setSearchResult(undefined);
    window.scrollTo(0, 0);
    setCurrentPage(numPage);
    history.push("/searchresult/" + numPage);
  }

  function onCenterChanged({latitude, longitude}) {
    setMapMarkers(undefined);

    const requestInfo = {
      adult: adultCount,
      children: childrenCount,
      infants: infantsCount,
      checkIn: startDateInfo.year + "-" + startDateInfo.month + "-" + startDateInfo.day,
      checkOut: endDateInfo.year + "-" + endDateInfo.month + "-" + endDateInfo.day,
      latitude: latitude + 0.0027,
      longitude: longitude - 0.0283
    };

    let urlInfo = '';
    const requestInfoKeys = Object.keys(requestInfo);
    requestInfoKeys.map((data, index) => {
      urlInfo += data + "=" + requestInfo[data] + "&";
    });

    const url = process.env.REACT_APP_SEARCH_NEAR_API + "?" + urlInfo;

    fetchResuest(url, "GET")
      .then((result) => result.json())
      .then((data) => {
        setMapMarkers(data);
    });
  }

  useEffect(() => {
    const requestInfo = {
      adult: adultCount,
      children: childrenCount,
      infants: infantsCount,
      checkIn: startDateInfo.year + "-" + startDateInfo.month + "-" + startDateInfo.day,
      checkOut: endDateInfo.year + "-" + endDateInfo.month + "-" + endDateInfo.day,
      page: props.match.params.pageNumber
    };

    let urlInfo = '';
    const requestInfoKeys = Object.keys(requestInfo);
    requestInfoKeys.map((data, index) => {
      urlInfo += data + "=" + requestInfo[data] + "&";
    });

    const url = process.env.REACT_APP_SEARCH_API + "?" + urlInfo;

    fetchResuest(url, "GET")
      .then((result) => result.json())
      .then((data) => {
        setCenterPosition([(data.rooms)[0].latitude, (data.rooms)[0].longitude]);
        setSearchResult(data);
        onCenterChanged({"latitude": (data.rooms)[0].latitude, "longitude": (data.rooms)[0].longitude});
    });
  }, [props.match.params.pageNumber]);

  return (
    <S.SearchResult>
      <Header />
      {!searchResult && <S.LoadingImage />}
      {searchResult && (
        <>
          <S.SearchResultLeft isMapVisible={isMapVisible}>
            <S.SearchResultContentWrap>
              {searchResult && (
                <ResultSummary
                  summary={
                    (searchResult.roomsCount >= 300 ? "300개 이상의 " : searchResult.roomsCount + "개의 ") +
                    "숙소" + " · " +
                    startDateInfo.month + "월 " + startDateInfo.day + " 일" +
                    " - " +
                    endDateInfo.month + "월 " + endDateInfo.day + " 일"
                  }
                />
              )}
              <Caution
                imageSrc="http://dev-angelo.dlinkddns.com/caution.gif"
                title="예약에 앞서 여행 제한 사항을 확인하세요."
                description="에어비앤비 커뮤니티의 건강과 안전이 최우선입니다. 정부 지침을 준수하고 꼭 필요한 경우에만 여행하실 것을 부탁드립니다."
              />
              {!isMapVisible && <IconTextButton onMapOnClick={onMapOnClick} />}
              <S.AccomodationCardGrid isMapVisible={isMapVisible}>
                {searchResult.rooms.map((data, index) => (
                  <AccomodationCard
                    isMapVisible={isMapVisible}
                    onAccomodationCardClick={onAccomodationCardClick}
                    onPositionClick={onPositionClick}
                    key={index}
                    id={data.id}
                    src={data.mainImage}
                    title={data.title}
                    chargePerDay={"₩" + data.dailyPrice.toLocaleString() + "원"}
                    totalCharge={
                      "총 요금: " +
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
                        ) * data.dailyPrice
                      ).toLocaleString() +
                      "원"
                    }
                    isHost={data.superHost}
                    country={data.country}
                    rating={data.rating}
                    longitude={data.longitude}
                    latitude={data.latitude}
                    checkIn={startDateInfo.year + "-" + startDateInfo.month + "-" + startDateInfo.day}
                    checkOut={endDateInfo.year + "-" + endDateInfo.month + "-" + endDateInfo.day}
                  />
                ))}
              </S.AccomodationCardGrid>
              <S.PagingWrap>
        <Pagination
          currentPage={currentPage}
          totalSize={searchResult.roomsCount}
          sizePerPage={20}
          changeCurrentPage={changeCurrentPage}
          firstPageText="first"
          lastPageText="last"
          showFirstLastPages={true}
          nextPageText="next"
          previousPageText="prev"
        />
      </S.PagingWrap>
            </S.SearchResultContentWrap>
          </S.SearchResultLeft>
          {isMapVisible && <Map onCloseButtonClick={onCloseButtonClick} centerPosition={centerPosition} rooms={searchResult.rooms} nearRooms={mapMarkers && mapMarkers.rooms} onCenterChanged={onCenterChanged}/>}
        </>
      )}
    </S.SearchResult>
  );
}

export default SearchResult;
