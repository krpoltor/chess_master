package com.capgemini.chess.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Challenge could not be found in database!")
public class ChallengeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ChallengeNotFoundException() {
		super("Challenge not found!");
	}

	public ChallengeNotFoundException(String message) {
		super("Challenge not found!" + message);
	}

}
