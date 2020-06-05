import React from 'react';
import styled from 'styled-components'
import TextButton from 'Components/Common/TextButton/TextButton'
import RadiusTextButton from 'Components/Common/RadiusTextButton/RadiusTextButton'
import Nickname from 'Components/Common/Nickname/Nickname'

const S = {};
S.HeaderNavigation = styled.div`
  display: block;
  position: relative;
  float: right;
  height: 80px;

  > *:not(:last-child) {
      margin-right: 5px;
  }
`;

function HeaderNavigation(props) {
  return (
      <>
        <S.HeaderNavigation>
          {props.userNickname && <Nickname title={props.userNickname + " 님 반갑습니다."}></Nickname>}
          <TextButton title="도움말" href="#"></TextButton>
          {!props.userNickname && <RadiusTextButton title="로그인" href={props.loginUrl}></RadiusTextButton>}
          {props.userNickname && <RadiusTextButton title="로그아웃" href="#" onLogoutClick={props.onLogoutClick} ></RadiusTextButton>}
        </S.HeaderNavigation>
      </>
  );
}

export default HeaderNavigation;