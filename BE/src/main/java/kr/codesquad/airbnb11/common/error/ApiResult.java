package kr.codesquad.airbnb11.common.error;

import java.util.List;
import kr.codesquad.airbnb11.common.error.ApiError.FieldError;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.validation.BindingResult;

public class ApiResult<T> {

  private final boolean success;

  private final T response;

  private final ApiError error;

  private ApiResult(boolean success, T response, ApiError error) {
    this.success = success;
    this.response = response;
    this.error = error;
  }

  private ApiResult(boolean success, ApiError error) {
    this.success = success;
    this.response = null;
    this.error = error;
  }

  public static <T> ApiResult<T> OK(T response) {
    return new ApiResult<>(true, response, null);
  }


  public static ApiResult ERROR(ErrorCode code) {
    return new ApiResult<>(false, ApiError.of(code));
  }

  public static ApiResult ERROR(ErrorCode code, BindingResult bindingResult) {
    return new ApiResult<>(false, ApiError.of(code, bindingResult));
  }

  public static ApiResult ERROR(ErrorCode code, List<FieldError> errors) {
    return new ApiResult<>(false, ApiError.of(code, errors));
  }

  public boolean isSuccess() {
    return success;
  }

  public T getResponse() {
    return response;
  }

  public ApiError getError() {
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
