import React from "react";
import styled, { css } from "styled-components";
import AccomodationCard from "Components/SearchResult/AccomodationCard/AccomodationCard";
import Caution from "Components/SearchResult/Caution/Caution";
import ResultSummary from "Components/SearchResult/ResultSummary/ResultSummary";
import Header from "Components/SearchResult/Header/Header";

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

function SearchResult() {
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
        <S.AccomodationCardGrid>
          <AccomodationCard
            src="https://a0.muscache.com/im/pictures/2e8af4e2-480c-4dac-880d-a3d3dda13215.jpg"
            typeInfo="개인실 · 침대 0개"
            title="ONe Bedroom * Long term * Short term * Every Term"
            chargePerDay="13,907원/1박"
            totalCharge="총 요금: 55,629원"
          />
          <AccomodationCard
            src="https://a0.muscache.com/im/pictures/2e8af4e2-480c-4dac-880d-a3d3dda13215.jpg"
            typeInfo="개인실 · 침대 0개"
            title="ONe Bedroom * Long term * Short term * Every Term"
            chargePerDay="13,907원/1박"
            totalCharge="총 요금: 55,629원"
          />
          <AccomodationCard
            src="https://a0.muscache.com/im/pictures/2e8af4e2-480c-4dac-880d-a3d3dda13215.jpg"
            typeInfo="개인실 · 침대 0개"
            title="ONe Bedroom * Long term * Short term * Every Term"
            chargePerDay="13,907원/1박"
            totalCharge="총 요금: 55,629원"
          />
          <AccomodationCard
            src="https://a0.muscache.com/im/pictures/2e8af4e2-480c-4dac-880d-a3d3dda13215.jpg"
            typeInfo="개인실 · 침대 0개"
            title="ONe Bedroom * Long term * Short term * Every Term"
            chargePerDay="13,907원/1박"
            totalCharge="총 요금: 55,629원"
          />
          <AccomodationCard
            src="https://a0.muscache.com/im/pictures/2e8af4e2-480c-4dac-880d-a3d3dda13215.jpg"
            typeInfo="개인실 · 침대 0개"
            title="ONe Bedroom * Long term * Short term * Every Term"
            chargePerDay="13,907원/1박"
            totalCharge="총 요금: 55,629원"
          />
          <AccomodationCard
            src="https://a0.muscache.com/im/pictures/2e8af4e2-480c-4dac-880d-a3d3dda13215.jpg"
            typeInfo="개인실 · 침대 0개"
            title="ONe Bedroom * Long term * Short term * Every Term"
            chargePerDay="13,907원/1박"
            totalCharge="총 요금: 55,629원"
          />
        </S.AccomodationCardGrid>
      </S.SearchResultContentWrap>
    </S.SearchResult>
  );
}

export default SearchResult;
