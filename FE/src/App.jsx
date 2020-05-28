import React from 'react';
import { useSelector, useDispatch } from "react-redux";
import './App.css'

import AppRouter from './routers/AppRouter';
import allActions from "./statement/actions/allActions";

function App() {
  return (
    <>
      <AppRouter />
    </>
  );
}

export default App;
