import React from 'react';
import styled from 'styled-components'

const S = {};
S.TextButton = styled.a`
  display: inline-block;
  position: relative;
  background-color: transparent;
  border: 1px solid #dadfe0;
  border-radius: 22px;
  line-height: 18px;
  font-size: 14px;
  color: #222222;
  font-weight: bold;
  top: 50%;
  transform: translateY(-50%);

  &:hover {
    color: black;
    box-shadow: 1px 1px 1px 1px gray;
  }
`;

S.Title = styled.div`
  position: relative;
  padding: 12px;
`;

function RadiusTextButton(props) {
  return (
    <S.TextButton href={props.href}>
      <S.Title>{props.title}</S.Title>
    </S.TextButton>
  );
}

export default RadiusTextButton;