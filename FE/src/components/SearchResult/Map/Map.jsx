import React, { useState, useEffect, Component } from "react";
import styled, { css } from "styled-components";
import { Map as LeafMap, TileLayer, Marker, Popup } from "react-leaflet";
import CloseButton from "Components/SearchResult/Map/CloseButton/CloseButton";
import AccomodationInfo from "Components/SearchResult/Map/AccomodationInfo/AccomodationInfo";

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
  const leafMapRef = React.createRef();

  const onDragend = e => {
    props.onCenterChanged({latitude: leafMapRef.current.viewport.center[0], longitude: leafMapRef.current.viewport.center[1]});
  }

  return (
    <S.Map>
      <LeafMap ref={leafMapRef} center={props.centerPosition} zoom={14} onDragend={onDragend}>
        <TileLayer
          attribution='&amp;copy <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
          url="https://{s}.tile.osm.org/{z}/{x}/{y}.png"
        />
        {props.nearRooms && props.nearRooms.map((data, index) => (
          <Marker key={index} position={[data.latitude, data.longitude]}>
            <Popup>
              <AccomodationInfo
                src={data.mainImage}
                title={data.title}
                chargePerDay={"₩" + data.dailyPrice.toLocaleString() + "원"}
                isHost={data.superHost}
                country={data.country}
                rating={data.rating}
                longitude={data.longitude}
                latitude={data.latitude}
              ></AccomodationInfo>
            </Popup>
          </Marker>
        ))}
        {props.rooms && props.rooms.map((data, index) => (
          <Marker key={index} position={[data.latitude, data.longitude]}>
            <Popup>
              <AccomodationInfo
                src={data.mainImage}
                title={data.title}
                chargePerDay={"₩" + data.dailyPrice.toLocaleString() + "원"}
                isHost={data.superHost}
                country={data.country}
                rating={data.rating}
                longitude={data.longitude}
                latitude={data.latitude}
              ></AccomodationInfo>
            </Popup>
          </Marker>
        ))}
      </LeafMap>
      <CloseButton onCloseButtonClick={props.onCloseButtonClick}>X</CloseButton>
    </S.Map>
  );
}

export default Map;