import React from 'react';
import ReactDOM from 'react-dom';
import { Provider } from "react-redux";
import { createStore } from "redux";
import rootReducer from 'Reducers/rootReducer'
import 'Library/style/reset.css'
import App from './App';

const store = createStore(
  rootReducer,
  window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
);

ReactDOM.render(
  <React.StrictMode>
    <Provider store={store}>
    <App />
    </Provider>
  </React.StrictMode>,
  document.getElementById('root')
);
