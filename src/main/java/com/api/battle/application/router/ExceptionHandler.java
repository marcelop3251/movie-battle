package com.api.battle.application.router;

import com.api.battle.domain.exception.BaseException;
import com.api.battle.domain.exception.GameStartException;
import com.api.battle.domain.exception.TypeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ExceptionHandler {


  public Mono<ServerResponse> handler(Throwable ex) {
    HttpErrorDTO httpErrorDTO = buildHttpDto("Internal Server Error", 500);
    if (ex instanceof BaseException) {
      BaseException exception = (BaseException) ex;
      httpErrorDTO =  handleException(exception);
    }
    return ServerResponse.status(httpErrorDTO.status()).bodyValue(httpErrorDTO);
  }

  private HttpErrorDTO buildHttpDto(String message, int statusCode) {
    return new HttpErrorDTO(message, statusCode);
  }

  private HttpErrorDTO handleException(BaseException ex) {
    if (TypeException.GAME_ALREADY_STARTED.equals(ex.getTypeException())) {
      return buildHttpDto(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
    } else {
      return buildHttpDto("Internal Server Error", 500);
    }
  }
}
