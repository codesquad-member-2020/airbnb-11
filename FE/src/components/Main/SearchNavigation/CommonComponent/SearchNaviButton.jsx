import React from 'react';
import styled, { css } from 'styled-components'

const S = {};
S.SearchNaviButton = styled.div`
  float: left;
  position: relative;
  width: 220px;
  height: 40px;
  padding: 15px;
  border-radius: 12px;
  box-shadow: ${(props) => (props.focus ? "0 0 0 1.5px black inset" : "")};
  user-select: none;

  &:hover {
    cursor: pointer;
    box-shadow: ${(props) => (!props.focus ? "0 0 0 1.5px lightgray inset" : "")};
    background: ${(props) => props.focus ? "#f7f7f7" : "#f9f9f9"};
  }

  &:active {
    cursor: pointer;
    box-shadow: 0 0 0 1.5px black inset;
  }

  ${(props) =>
    props.focus &&
    css`
      border-bottom-left-radius: ${(props) => (props.focus ? "0px" : "12px")};
      border-bottom-right-radius: ${(props) => (props.focus ? "0px" : "12px")};
  `};
`;

S.Title = styled.div`
  position: relative;
  font-size: 11px;
  font-weight: bold;
  padding-bottom: 5px;
`;

S.Contents = styled.div`
  position: relative;
  font-size: 15px;
`;

function SearchNaviButton(props) {
  const onSearchNaviButtonClick = e => {
    e.stopPropagation();
    props.onClick(props.id);
  }

  return (
    <S.SearchNaviButton focus={props.focus} onClick={onSearchNaviButtonClick}>
      <S.Title>
        {props.title}
      </S.Title>
      <S.Contents>
        {props.contents}
      </S.Contents>
    </S.SearchNaviButton>
  );
}

export default SearchNaviButton;