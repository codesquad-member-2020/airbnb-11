import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import styled from "styled-components";
import SearchNaviButton from "Components/Common/SearchNaviButton/SearchNaviButton";
import {
  setStartDate,
  setEndDate,
} from "Actions/date/dateAction";

import { DateRangePicker } from "react-dates";
import moment from "moment";
import "moment/locale/ko";
import "react-dates/initialize";
import "react-dates/lib/css/_datepicker.css";

moment.locale("ko");

const DatePickerWrap = styled.div`
  position: absolute;
  top: 20px;
  left: -180px;
  width: 0px;
  height: 0px;
  display: flex;
  opacity: 1;

  .DateRangePickerInput {
    display: flex;
    background-color: transparent;
    position: relative;
    #airbnb-end-date {
      display: none;
    }
    .DateInput {
      position: absolute;
      top: 0;
      left: 0;
      width: 0;
      height: 0;
    }
    .DateInput_input {
      cursor: pointer;
      opacity: 0;
    }
    .DateInput_fang {
      display: none;
    }
    .DateRangePickerInput_arrow {
      display: none;
    }
  }
  .DayPickerNavigation_button__default {
    border: none;
    outline: none;
  }
  .DayPicker_weekHeader {
    color: #95a5a6;
  }
  .CalendarMonth_table {
    margin-top: 10px;
    tr {
      border: 1px solid #fff;
    }
    td {
      outline: none;
    }
  }
  .CalendarDay__default {
    border: none;
  }
  .CalendarDay {
    vertical-align: middle;
    position: relative;
    z-index: 0;
    &.CalendarDay__selected {
      background-color: #fff;
      &::before {
        content: "";
        width: 50%;
        height: 100%;
        background-color: #ecf0f1;
        position: absolute;
        top: 0;
        z-index: -2;
      }
      &::after {
        content: "";
        width: 100%;
        height: 100%;
        background-color: #000;
        border-radius: 50%;
        position: absolute;
        top: 0;
        left: 0;
        z-index: -1;
      }
    }
    &.CalendarDay__selected_start {
      &::before {
        right: 0;
      }
    }
    &.CalendarDay__selected_end {
      &::before {
        left: 0;
      }
    }
    &.CalendarDay__hovered_span,
    &.CalendarDay__selected_span {
      background-color: #ecf0f1;
      color: #484848;
      outline: none;
    }
  }
`;

const S = {};
S.ChargeButton = styled.div``;

function DateButton(props) {
  const { startDate, endDate, startDateInfo, endDateInfo } = useSelector(
    ({ dateReducer }) => dateReducer
  );
  const dispatch = useDispatch();

  const [myFocusedInput, setMyFocusedInput] = useState(null);

  const handleDatesChange = ({ startDate, endDate }) => {
    dispatch(setStartDate(startDate));
    dispatch(setEndDate(endDate));

    if (endDate) setMyFocusedInput("startDate");
  };

  const onDateButtonClick = () => {
    setMyFocusedInput("startDate");
    props.onClick();
  };

  return (
    <>
      <SearchNaviButton
        title={props.title}
        contents={
          startDate && endDate
            ? (startDateInfo.month + "월" + startDateInfo.day + "일" + " - " +
               endDateInfo.month + "월" + endDateInfo.day + "일")
            : props.contents
        }
        onClick={onDateButtonClick}
        focus={props.focus}
      />
      <DatePickerWrap focus={props.focus}>
        <DateRangePicker
          startDatePlaceholderText=""
          endDatePlaceholderText=""
          startDateId="start_id"
          endDateId="end_id"
          startDate={startDate}
          endDate={endDate}
          onDatesChange={handleDatesChange}
          focusedInput={myFocusedInput}
          onFocusChange={(focusedInput) => {
            setMyFocusedInput(focusedInput);
          }}
          calendarInfoPosition="bottom"
          hideKeyboardShortcutsPanel readOnly noBorder small keepOpenOnDateSelect
        />
      </DatePickerWrap>
    </>
  );
}

export default DateButton;
