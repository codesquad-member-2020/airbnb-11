import React from 'react';
import styled from 'styled-components'

const S = {};
S.SearchButton = styled.div`
  position: relative;
  width: 104px;
  height: 44px;
  padding: 12px;
  border-radius: 12px;
`;

S.Button = styled.button`
  float: left;
  position: relative;
  width: 104px;
  height: 44px;
  border-radius: 12px;
  color: white;
  font-size: 15px;
  font-weight: bold;
  background-image: linear-gradient(to right, #E61E4D 0%, #E31C5F 50%, #D70466 100%);

  &:hover {
    cursor: pointer;
  }
`;

function SearchButton(props) {
  return (
    <S.SearchButton>
      <S.Button>{props.contents}</S.Button>
    </S.SearchButton>
  );
}

export default SearchButton;