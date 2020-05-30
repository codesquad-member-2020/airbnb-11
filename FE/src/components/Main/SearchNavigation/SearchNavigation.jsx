import React, { useState } from 'react';
import { withRouter } from 'react-router-dom';
import styled from 'styled-components'
import { useSelector, useDispatch } from "react-redux";

import GuestButton from 'Components/Common/GuestButton/GuestButton'
import SearchButton from 'Components/Main/SearchNavigation/SearchButton/SearchButton'
import DateButton from 'Components/Common/DateButton/DateButton'
import { setFocusId } from "Actions/focusId/focusIdAction";

const S = {}
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
  const focusId = useSelector(({focusIdReducer}) => focusIdReducer);
  const dispatch = useDispatch();

  const [focusedId, setFocusedId] = useState(null);

  function onNaviButtonClick(key) {
    dispatch(setFocusId(key));
  }

  function onSearchButtonClick() {
    history.push('/searchresult');
  }

  return (
      <S.SearchNavigation>
        <DateButton title="체크인/체크아웃" contents="날짜 추가" customKey={0} focus={0 === focusId} onClick={() => onNaviButtonClick(0)} />
        <GuestButton title="인원" contents="게스트 추가" customKey={1} focus={1 === focusId} onClick={() => onNaviButtonClick(1)} />
        <SearchButton contents="검색" onClick={onSearchButtonClick} />
      </S.SearchNavigation>
  );
}

export default withRouter(SearchNavigation);