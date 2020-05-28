import React from 'react';
import styled from 'styled-components'

const StyledLogo = styled.div`
  position: relative;
  float: left;
  width: 118px;
  height: 64px;
  background-size: 100% 100%;
  background-image: url(${props => props.src});
  top: 50%;
  transform: translateY(-50%);
`;

function Logo(props) {
  return (
      <>
        <StyledLogo src={props.src}></StyledLogo>
      </>
  );
}

export default Logo;