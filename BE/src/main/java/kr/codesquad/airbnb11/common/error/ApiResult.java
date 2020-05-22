package kr.codesquad.airbnb11.common.error;

import java.util.List;
import kr.codesquad.airbnb11.common.error.ErrorResponse.FieldError;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.BindingResult;

public class ApiResult<T> {

  private final boolean success;

  private final T response;

  private final ErrorResponse error;

  public ApiResult(boolean success, T response, ErrorResponse error) {
    this.success = success;
    this.response = response;
    this.error = error;
  }

  public static <T> ApiResult<T> OK(T response) {
    return new ApiResult<>(true, response, null);
  }

  public static ApiResult ERROR(ErrorCode code) {
    return new ApiResult<>(false, null, ErrorResponse.of(code));
  }

  public static ApiResult ERROR(ErrorCode code, BindingResult bindingResult) {
    return new ApiResult<>(false, null, ErrorResponse.of(code, bindingResult));
  }

  public static ApiResult ERROR(ErrorCode code, List<FieldError> errors) {
    return new ApiResult<>(false, null, ErrorResponse.of(code, errors));
  }

  public boolean isSuccess() {
    return success;
  }

  public T getResponse() {
    return response;
  }

  public ErrorResponse getError() {
    return error;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("success", success)
        .append("response", response)
        .append("error", error)
        .toString();
  }
}
