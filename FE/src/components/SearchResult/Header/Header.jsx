import React from 'react';
import { useSelector } from "react-redux";
import styled from 'styled-components'

import Logo from 'Components/Common/Logo/Logo'
import HeaderNavigation from 'Components/SearchResult/Header/HeaderNavigation/HeaderNavigation'
import ReservationInfoButton from 'Components/SearchResult/Header/ReservationInfoButton/ReservationInfoButton'
import { logo } from 'Constants/mainpage'

import Cookies from 'universal-cookie';
import * as jwtDecode from 'jwt-decode';

const S = {}
S.Header = styled.div`
  position: fixed;
  height: 100px;
  top: 0;
  z-index: 10;
  background-color: white;
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
  const { startDateInfo, endDateInfo } = useSelector(
    ({ dateReducer }) => dateReducer
  );

  const { adultCount, childrenCount, infantsCount } = useSelector(
    ({ guestCountReducer }) => guestCountReducer
  );

  function getUserNickname() {
    const cookies = new Cookies();
    const jwtToken = cookies.get('jwt');
    let userNickname = undefined;
    
    if (jwtToken !== undefined) {
      const decoded = jwtDecode(jwtToken);
      console.log(decoded);
      userNickname = decoded.user.nickname;
    }

    return userNickname;
  }

  function onLogoutClick() {
    const cookies = new Cookies();
    cookies.remove('jwt');
  }

  return (
    <S.Header>
      <S.HeaderWrap>
        <Logo src={logo.logoImageSrc} />
        <ReservationInfoButton
          date={startDateInfo.month + "월 " + startDateInfo.day + "일" + " - " +
                endDateInfo.month + "월 " + endDateInfo.day + "일"}
          guest={
            "게스트 " + (adultCount + childrenCount) + "명" 
            + (infantsCount ? ", 유아 " + infantsCount + "명" : "")
          }
        />
        <HeaderNavigation loginUrl={process.env.REACT_APP_LOGIN_API} userNickname={getUserNickname()} onLogoutClick={onLogoutClick}></HeaderNavigation>
      </S.HeaderWrap>
    </S.Header>
  );
}

export default Header;