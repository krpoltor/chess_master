package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.generated.entities.GameEntity;
import com.capgemini.chess.service.to.GameTo;

public class GameMapper {

	public static GameTo map(GameEntity gameEntity) {
		if (gameEntity != null) {
			GameTo gameTO = new GameTo();
			// Basic To
			gameTO.setId(gameEntity.getId());
			gameTO.setVersion(gameEntity.getVersion());
			gameTO.setCreatedAt(gameEntity.getCreatedAt());
			gameTO.setModifiedAt(gameEntity.getModifiedAt());
			return gameTO;
		}
		return null;
	}

	public static GameEntity map(GameTo gameTO) {
		if (gameTO != null) {
			GameEntity gameEntity = new GameEntity();
			// Basic To
			gameEntity.setId(gameTO.getId());
			gameEntity.setVersion(gameTO.getVersion());
			gameEntity.setCreatedAt(gameTO.getCreatedAt());
			gameEntity.setModifiedAt(gameTO.getModifiedAt());
			return gameEntity;
		}
		return null;
	}

	public static GameEntity update(GameEntity gameEntity, GameTo gameTO) {
		if (gameTO != null && gameEntity != null) {
			
		}
		return gameEntity;
	}

	public static List<GameTo> map2TOs(List<GameEntity> challengeEntities) {
		return challengeEntities.stream().map(GameMapper::map).collect(Collectors.toList());
	}

	public static List<GameEntity> map2Entities(List<GameTo> challengeTOs) {
		return challengeTOs.stream().map(GameMapper::map).collect(Collectors.toList());
	}

}
