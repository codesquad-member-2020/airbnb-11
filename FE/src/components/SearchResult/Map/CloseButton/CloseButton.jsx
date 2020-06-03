import React, { useState, useEffect } from "react";
import styled, { css } from "styled-components";
import { Map as LeafMap, TileLayer, Marker, Popup } from "react-leaflet";

const S = {};
S.CloseButton = styled.button`
  position: absolute;
  top: 20px;
  right: 20px;
  width: 40px;
  height: 40px;
  background-color: white;
  border: 1px solid #dadfe0;
  border-radius: 12px;
  line-height: 18px;
  font-size: 20px;
  font-weight: bold;
  z-index: 1000;
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

function CloseButton(props) {

  return (
    <S.CloseButton onClick={props.onCloseButtonClick}>X</S.CloseButton>
  );
}

export default CloseButton;