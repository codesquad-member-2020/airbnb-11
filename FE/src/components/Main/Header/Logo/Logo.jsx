import React from 'react';
import styled from 'styled-components'

const StyledLogo = styled.img`
  position: relative;
  width: 118px;
  height: 64px;
  background-size: 100% 100%;
`;

function Logo(props) {
  return (
      <>
        <StyledLogo src={props.src}></StyledLogo>
      </>
  );
}

export default Logo;