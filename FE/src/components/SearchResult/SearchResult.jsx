import React, { useState, useEffect } from "react";
import styled, { css } from "styled-components";
import { withRouter } from 'react-router-dom';
import AccomodationCard from "Components/SearchResult/AccomodationCard/AccomodationCard";
import Caution from "Components/SearchResult/Caution/Caution";
import ResultSummary from "Components/SearchResult/ResultSummary/ResultSummary";
import Header from "Components/SearchResult/Header/Header";
import useFetch from "CustomHooks/useFetch"
import fetchResuest from "../../utils/fetchRequest"
import Map from "Components/SearchResult/Map/Map";


const S = {};

S.SearchResult = styled.div`
  position: relative;
  width: 1560px;
  margin: 0 auto;
`;

S.SearchResultLeft = styled.div`
  position: relative;
  margin-top: 100px;
  width: 780px;
  float: left;
  overflow: hidden;
`;

S.SearchResultContentWrap = styled.div`
  position: relative;
  width: 780px;
  margin: 0 auto;
`;

S.AccomodationCardGrid = styled.div`
  width: 100%;
  display: grid;
  margin-top: 50px;
  grid-template-rows: 300px;
  grid-column-gap: 15px;
  grid-row-gap: 40px;
  grid-template-columns: repeat(2, minmax(300px, 1fr));
`;

S.LoadingImage = styled.div`
  width: 200px;
  height: 200px;
  background-image: url("https://cdn.clipart.email/4b453b9d5509587f169c211be6908ca9_loading-gif-png-picture-654600-loading-gif-png_600-450.gif");
  background-size: 100% 100%;
  margin: 0 auto;
  margin-top: 200px;
  margin-bottom: 200px;
`;


function SearchResult({ history }) {
  const [searchResult, setSearchResult] = useState(undefined);

  function onAccomodationCardClick() {
    history.push('/searchresult/reservationmodal');
  }

  useEffect(() => {
    const url = "http://13.124.223.191/api/rooms/search?adult=1&checkIn=2020-07-01&checkOut=2020-07-02&parameterMap=%7B%7D";

    fetchResuest(url, "GET")
      .then((result) => result.json())
      .then((data) => {
        console.log(data);
        setSearchResult(data);
      });
  }, []);

  return (
    <S.SearchResult>
      <Header />
      {!searchResult && <S.LoadingImage />}
      <S.SearchResultLeft>
      <S.SearchResultContentWrap>
        {searchResult && (
          <ResultSummary
            summary={
              searchResult.roomsCount +
              "개의 숙소" +
              " · " +
              "6월 15일 - 6월 18일 · 게스트 1명"
            }
          />
        )}
        <Caution
          imageSrc="http://dev-angelo.dlinkddns.com/caution.gif"
          title="예약에 앞서 여행 제한 사항을 확인하세요."
          description="에어비앤비 커뮤니티의 건강과 안전이 최우선입니다. 정부 지침을 준수하고 꼭 필요한 경우에만 여행하실 것을 부탁드립니다."
        />
        <S.AccomodationCardGrid>
          {searchResult &&
            searchResult.rooms.map((data, index) => (
              <AccomodationCard
                onClick={onAccomodationCardClick}
                key={data.id}
                src={data.mainImage}
                title={data.title}
                chargePerDay={"₩" + data.dailyPrice.toLocaleString() + "원"}
                totalCharge={data.totalCharge}
              />
            ))}
        </S.AccomodationCardGrid>
      </S.SearchResultContentWrap>
      </S.SearchResultLeft>
      {searchResult && <Map />}
    </S.SearchResult>
  );
}

export default withRouter(SearchResult);
