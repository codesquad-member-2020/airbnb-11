import React from 'react';
import styled from 'styled-components'
import { useHistory } from "react-router-dom";

const StyledLogo = styled.div`
  position: relative;
  float: left;
  width: 118px;
  height: 64px;
  background-size: 100% 100%;
  background-image: url(${props => props.src});
  top: 50%;
  transform: translateY(-50%);

  &:hover {
    cursor: pointer;
  }
`;

function Logo(props) {
  const history = useHistory();

  function onLogoClick() {
    history.push("/");
  }

  return (
      <>
        <StyledLogo src={props.src} onClick={onLogoClick}></StyledLogo>
      </>
  );
}

export default Logo;