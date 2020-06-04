import React from 'react';
import styled from 'styled-components'

const S = {};
S.IconTextButton = styled.div`
  float: right;
  position: relative;
  background-color: transparent;
  width: 160px;
  height: 40px;
  border-radius: 22px;
  line-height: 18px;
  font-size: 14px;
  color: #222222;
  font-weight: bold;
  top: 50%;
  transform: translateY(-50%);

  color: gray;
  outline: none;

  &:hover {
    cursor: pointer;
    color: black;
    background-color: rgba(239, 239, 239);
  }

  &:active {
    background-color: lightgray;
  }
`;

S.Image = styled.div`
  position: relative;
  float: left;
  width: 40px;
  height: 40px;
  background-image: url("https://cdn0.iconfinder.com/data/icons/map-location-solid-style/91/Map_-_Location_Solid_Style_17-512.png");
  background-size: 100% 100%;
`;

S.Title = styled.div`
  position: relative;
  float: left;
  padding: 12px;
`;

function IconTextButton(props) {
  return (
    <S.IconTextButton onClick={props.onMapOnClick}>
      <S.Image />
      <S.Title>지도 표시하기</S.Title>
    </S.IconTextButton>
  );
}

export default IconTextButton;