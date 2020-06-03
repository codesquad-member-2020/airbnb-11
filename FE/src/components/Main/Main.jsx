import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import Header from 'Components/Main/Header/Header'
import SearchNavigation from 'Components/Main/SearchNavigation/SearchNavigation'
import { setFocusId } from "Actions/focusId/focusIdAction";
import BGImage from 'Components/Main/BGImage/BGImage'
import Cookies from 'universal-cookie';
import * as jwtDecode from 'jwt-decode';

function Main() {
  const focusId = useSelector(({focusIdReducer}) => focusIdReducer);
  const dispatch = useDispatch();

  function onMainClick() {
    dispatch(setFocusId(null));
  }

  useEffect(() => {
    const cookies = new Cookies();
    const jwtToken = cookies.get('jwt');
    
    if (jwtToken !== undefined) {
      const decoded = jwtDecode(jwtToken);
      console.log("decoded=" + JSON.stringify(decoded));
    }
    else {
      console.log("jwtToken is not set")
    }
  }, [])

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
