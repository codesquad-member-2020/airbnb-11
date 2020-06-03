import React from 'react';
import styled from 'styled-components'

const S = {};
S.BGImage = styled.div`
  width: ${props => props.imageWidth}px;
  height: ${props => props.imageHeight}px;
  background-image: url(${props => props.imageSrc});
  background-size: 100% 100%;
  margin: 0 auto;
`;

function BGImage(props) {
  return (
      <>
        <S.BGImage imageSrc={props.imageSrc} imageWidth={props.imageWidth} imageHeight={props.imageHeight}>
        </S.BGImage>
      </>
  );
}

export default BGImage;