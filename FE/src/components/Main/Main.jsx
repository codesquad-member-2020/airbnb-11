import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import Header from 'Components/Main/Header/Header'
import SearchNavigation from 'Components/Main/SearchNavigation/SearchNavigation'
import { setFocusId } from "Actions/focusId/focusIdAction";
import BGImage from 'Components/Main/BGImage/BGImage'

function Main() {
  const focusId = useSelector(({focusIdReducer}) => focusIdReducer);
  const dispatch = useDispatch();

  function onMainClick() {
    dispatch(setFocusId(null));
  }

  return (
      <div onClick={onMainClick}>
        <Header />
        <SearchNavigation />
        <BGImage imageSrc="http://dev-angelo.dlinkddns.com/bg_1.png" imageWidth={1580} imageHeight={623} />
        <BGImage imageSrc="http://dev-angelo.dlinkddns.com/bg_2.png" imageWidth={1580} imageHeight={900} />
      </div>
  );
}

export default Main;
