package com.capgemini.chess.service.to;

import org.apache.commons.lang.builder.ToStringBuilder;

public class GameTo extends BasicTo {

	public GameTo() {
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
