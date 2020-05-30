import React from 'react';
import styled from "styled-components";

const S = {};

S.ResultSummary = styled.div`
  display: relative;
  height: 26px;
  padding-top: 25px;
  padding-bottom: 5px;
  font-size: 14px;
  margin:0 auto;
`;

function ResultSummary(props) {
  return (
      <S.ResultSummary>
        {props.summary}
      </S.ResultSummary>
  );
}

export default ResultSummary;
