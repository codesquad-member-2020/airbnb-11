import React from 'react';
import styled from 'styled-components'

import Logo from './Logo/Logo'
import HeaderNavigation from './HeaderNavigation/HeaderNavigation'
import mainpageConstants from '../../../constants/mainpage'

const S = {}
S.Header = styled.div`
  position: relative;
  width: 1400px;
  height: 80px;
  color: black;
  font-size: 24px;
  margin: 0 auto;
  padding-top: 10px;
  padding-bottom: 10px;
`;

function Header() {

  return (
      <S.Header>
        <Logo src={mainpageConstants.logoImageSrc} />
        <HeaderNavigation></HeaderNavigation>
      </S.Header>
  );
}

export default Header;