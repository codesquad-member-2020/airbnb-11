import React from 'react';
import styled from 'styled-components'

const S = {};
S.TextButton = styled.div`
  display: inline-block;
  position: relative;
  background-color: transparent;
  line-height: 18px;
  font-size: 14px;
  color: #222222;
  font-weight: bold;
  top: 50%;
  transform: translateY(-50%);
`;

S.Title = styled.div`
  position: relative;
  padding: 12px;
`;

function Nickname(props) {
  return (
    <S.TextButton href={props.href}>
      <S.Title>{props.title}</S.Title>
    </S.TextButton>
  );
}

export default Nickname;