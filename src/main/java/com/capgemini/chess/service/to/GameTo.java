package com.capgemini.chess.service.to;

public class GameTo extends BasicTo {

	public GameTo() {
	}

	@Override
	public String toString() {
		return "GameTo [getId()=" + getId() + ", getVersion()=" + getVersion() + ", getCreatedAt()=" + getCreatedAt()
				+ ", getModifiedAt()=" + getModifiedAt() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}

}
