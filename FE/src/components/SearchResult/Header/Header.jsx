import React from 'react';
import styled from 'styled-components'

import Logo from 'Components/Common/Logo/Logo'
import HeaderNavigation from 'Components/SearchResult/Header/HeaderNavigation/HeaderNavigation'
import ReservationInfoButton from 'Components/SearchResult/Header/ReservationInfoButton/ReservationInfoButton'
import mainpageConstants from 'Constants/mainpage'

const S = {}
S.Header = styled.div`
  position: relative;
  border-bottom: 1px solid #dadfe0;
`;

S.HeaderWrap = styled.div`
  display: flex;
  justify-content: center;
  align-items: center; 
  position: relative;
  width: 1560px;
  height: 80px;
  color: black;
  font-size: 24px;
  margin: 0 auto;
  padding-top: 10px;
  padding-bottom: 10px;
  align-items: center;
`;

function Header() {
  return (
      <S.Header>
        <S.HeaderWrap>
          <Logo src={mainpageConstants.logoImageSrc} />
          <ReservationInfoButton date="6월 15일 - 6월 18일" guest="게스트 1명" />
          <HeaderNavigation></HeaderNavigation>
        </S.HeaderWrap>
      </S.Header>
  );
}

export default Header;