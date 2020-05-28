import React from 'react';
import styled from 'styled-components'
import TextButton from 'Components/Common/TextButton/TextButton'
import RadiusTextButton from 'Components/Common/RadiusTextButton/RadiusTextButton'

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
          <TextButton title="숙소 호스트 되기" href="#"></TextButton>
          <TextButton title="체험 호스팅하기" href="#"></TextButton>
          <TextButton title="도움말" href="#"></TextButton>
          <TextButton title="로그인" href="#"></TextButton>
          <RadiusTextButton title="회원 가입" href="#"></RadiusTextButton>
        </S.HeaderNavigation>
      </>
  );
}

export default HeaderNavigation;