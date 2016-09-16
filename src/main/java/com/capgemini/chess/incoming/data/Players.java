package com.capgemini.chess.incoming.data;

public class Players {

	String whitePlayer;
	String blackPlayer;
	
	public Players(){
		
	}

	public Players(String whitePlayer, String blackPlayer) {
		super();
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
	}

	public String getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(String whitePlayer) {
		this.whitePlayer = whitePlayer;
	}

	public String getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(String blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

}
