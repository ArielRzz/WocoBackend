package com.nocountry.woco.exception;

public class UserAlreadyExistException extends RuntimeException {

  public UserAlreadyExistException() {
    super("Email is already in use.");
  }

}
