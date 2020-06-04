import React, { useState } from "react";
import { withRouter } from "react-router-dom";
import styled from "styled-components";
import { useSelector, useDispatch } from "react-redux";

import GuestButton from "Components/Common/GuestButton/GuestButton";
import SearchButton from "Components/Main/SearchNavigation/SearchButton/SearchButton";
import DateButton from "Components/Common/DateButton/DateButton";
import { setFocusId } from "Actions/focusId/focusIdAction";
import { componentId } from "Constants/mainpage";

const S = {};
S.SearchNavigation = styled.div`
  position: relative;
  display: flex;
  height: 70px;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  color: black;
  width: fit-content;
  margin: 0 auto;
  border-radius: 12px;
  border: 1px solid #f7f7f7;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.08);
`;

function SearchNavigation({ history }) {
  const { startDate, endDate, startDateInfo, endDateInfo } = useSelector(
    ({ dateReducer }) => dateReducer
  );
  const { adultCount, childrenCount, infantsCount } = useSelector(
    ({ guestCountReducer }) => guestCountReducer
  );
  const focusId = useSelector(({ focusIdReducer }) => focusIdReducer);
  const dispatch = useDispatch();

  const [focusedId, setFocusedId] = useState(null);

  function onNaviButtonClick(key) {
    dispatch(setFocusId(key));
  }

  function onSearchButtonClick() {
    if ( startDate === null || endDate === null ) {
      alert("체크인/체크아웃 날짜를 입력해주세요.")
      return;
    }
    else if ( (adultCount + childrenCount + infantsCount) === 0 ) {
      alert("인원을 입력해주세요.")
      return;
    }

    history.push("/searchresult");
  }

  return (
    <S.SearchNavigation>
      <DateButton
        title="체크인/체크아웃"
        contents="날짜 추가"
        focus={componentId.dateButton === focusId}
        onClick={() => onNaviButtonClick(componentId.dateButton)}
      />
      <GuestButton
        title="인원"
        contents="게스트 추가"
        focus={componentId.guestButton === focusId}
        onClick={() => onNaviButtonClick(componentId.guestButton)}
      />
      <SearchButton contents="검색" onClick={onSearchButtonClick} />
    </S.SearchNavigation>
  );
}

export default withRouter(SearchNavigation);
