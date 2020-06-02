import React from 'react';
import styled from "styled-components";

const S = {};

S.Caution = styled.div`
  height: 40px;
  display: relative;
  padding-top: 20px;
  padding-bottom: 20px;
`;

S.Image = styled.div`
  float: left;
  width: 40px;
  height: 40px;
  background-image: url(${props => props.imageSrc});
  background-size: 100% 100%;
`;

S.Title = styled.div`
  float: left;
  line-height: 40px;
  font-size: 15px;
  font-weight: bold;
`;

S.Description = styled.div`
  float: left;
  font-size: 15px;
  line-height: 20px;
`;

function Caution(props) {
  return (
      <S.Caution>
        <S.Image imageSrc={props.imageSrc} />
        <S.Title>{props.title}</S.Title>
        <S.Description>{props.description}</S.Description>
      </S.Caution>
  );
}

export default Caution;
