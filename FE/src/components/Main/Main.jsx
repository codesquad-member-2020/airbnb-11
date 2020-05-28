import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import Header from 'Component/Main/Header/Header'
import SearchNavigation from 'Components/Main/SearchNavigation/SearchNavigation'
import { setFocusId } from "Actions/focusId/focusIdAction";

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
        <img src="http://dev-angelo.dlinkddns.com/bg_1.png"></img>
        <img src="http://dev-angelo.dlinkddns.com/bg_2.png"></img>
      </div>
  );
}

export default Main;
