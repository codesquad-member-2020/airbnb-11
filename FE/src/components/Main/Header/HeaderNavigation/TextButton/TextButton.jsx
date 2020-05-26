import React from 'react';
import styled from 'styled-components'

const S = {};
S.TextButton = styled.a`
  display: inline-block;
  position: relative;
  background-color: transparent;
  border-radius: 22px;
  line-height: 18px;
  font-size: 14px;
  color: #222222;
  font-weight: bold;
  top: 50%;
  transform: translateY(-50%);

  &:hover {
    color: black;
    background-color: #f4f4f4;
  }
`;

S.Title = styled.div`
  position: relative;
  padding: 12px;
`;

function TextButton(props) {
  return (
    <S.TextButton href={props.href}>
      <S.Title>{props.title}</S.Title>
    </S.TextButton>
  );
}

export default TextButton;