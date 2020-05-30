import React, { useState, useEffect } from "react";
import styled, { css } from "styled-components";
import { withRouter } from 'react-router-dom';
import AccomodationCard from "Components/SearchResult/AccomodationCard/AccomodationCard";
import Caution from "Components/SearchResult/Caution/Caution";
import ResultSummary from "Components/SearchResult/ResultSummary/ResultSummary";
import Header from "Components/SearchResult/Header/Header";
import searchResultData from "MockData/searchResult"

const S = {};

S.SearchResult = styled.div`
  display: relative;
  width: 100%;
  margin: 0 auto;
`;

S.SearchResultContentWrap = styled.div`
  display: relative;
  width: 1560px;
  margin: 0 auto;
`;

S.AccomodationCardGrid = styled.div`
  display: grid;
  grid-template-rows: 300px;
  grid-column-gap: 15px;
  grid-row-gap: 40px;
  grid-template-columns: repeat(5, minmax(300px, 1fr));
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
  const [searchResult, setSearchResult] = useState();

  function onAccomodationCardClick() {
    history.push('/searchresult/reservationmodal');
  }

  useEffect(() => {
    setTimeout(() => {
      setSearchResult(searchResultData);
    }, 2000);
  }, []);

  return (
    <S.SearchResult>
      <Header />
      <S.SearchResultContentWrap>
        <ResultSummary summary="300개 이상의 숙소 · 6월 15일 - 6월 18일 · 게스트 1명" />
        <Caution
          imageSrc="http://dev-angelo.dlinkddns.com/caution.gif"
          title="예약에 앞서 여행 제한 사항을 확인하세요."
          description="에어비앤비 커뮤니티의 건강과 안전이 최우선입니다. 정부 지침을 준수하고 꼭 필요한 경우에만 여행하실 것을 부탁드립니다."
        />
        {!searchResult && <S.LoadingImage/>}
        <S.AccomodationCardGrid>
          {searchResult &&
            searchResult.map((data, index) => (
              <AccomodationCard
                onClick={onAccomodationCardClick}
                key={index}
                src={data.src}
                title={data.title}
                typeInfo={data.typeInfo}
                chargePerDay={data.chargePerDay}
                totalCharge={data.totalCharge}
              />
            ))}
        </S.AccomodationCardGrid>
      </S.SearchResultContentWrap>
    </S.SearchResult>
  );
}

export default withRouter(SearchResult);
