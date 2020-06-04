import React, { useState, useEffect } from "react";
import styled, { css } from "styled-components";
import { Map as LeafMap, TileLayer, Marker, Popup } from "react-leaflet";
import CloseButton from "Components/SearchResult/Map/CloseButton/CloseButton";

const S = {};
S.Map = styled.div`
  position: fixed;
  left: 50%;
  margin-top: 100px;
  width: 780px;
  height: 100%;
  float: right;
  z-index: 10;

  .leaflet-control-attribution {
    display: none;
  }

  .leaflet-container {
    height: 100%;
    width: 780px;
  }

  .leaflet-div-icon {
    background: transparent;
    border: none;
  }
`;

function Map(props) {
  return (
    <S.Map>
      <LeafMap center={props.centerPosition} zoom={13}>
        <TileLayer
          attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.osm.org/{z}/{x}/{y}.png"
        />
        {props.rooms.map((data, index) => (
          <Marker key={index} position={[data.longitude, data.latitude]}>
            <Popup></Popup>
          </Marker>
        ))}
      </LeafMap>
      <CloseButton onCloseButtonClick={props.onCloseButtonClick}>X</CloseButton>
    </S.Map>
  );
}

export default Map;