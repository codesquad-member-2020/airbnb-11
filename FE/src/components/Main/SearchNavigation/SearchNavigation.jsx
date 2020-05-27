import React, { useState } from 'react';
import styled from 'styled-components'

import SearchNaviButton from './CommonComponent/SearchNaviButton'
import SearchButton from './SearchButton/SearchButton'
import DateButton from './DateButton/DateButton'

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

function SearchNavigation() {
  const [focusedId, setFocusedId] = useState(null);

  function onNaviButtonClick(key) {
    setFocusedId(key)
  }

  return (
      <S.SearchNavigation>
        <DateButton title="체크인/체크아웃" contents="날짜 추가" customKey={0} focus={0 === focusedId} onClick={() => onNaviButtonClick(0)} />
        <SearchNaviButton title="인원" contents="게스트 추가" customKey={1} focus={1 === focusedId} onClick={() => onNaviButtonClick(1)} />
        <SearchButton contents="검색"/>
      </S.SearchNavigation>
  );
}

export default SearchNavigation;