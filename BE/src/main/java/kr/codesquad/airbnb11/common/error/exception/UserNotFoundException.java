package kr.codesquad.airbnb11.common.error.exception;

import kr.codesquad.airbnb11.common.error.ErrorCode;

public class UserNotFoundException extends EntityNotFoundException {

  public UserNotFoundException() {
    super(ErrorCode.USER_NOT_FOUND);
  }

  public UserNotFoundException(String message) {
    super(message, ErrorCode.USER_NOT_FOUND);
  }
}
