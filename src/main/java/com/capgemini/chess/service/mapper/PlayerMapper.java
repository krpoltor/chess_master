package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.generated.entities.PlayerEntity;
import com.capgemini.chess.service.to.PlayerTo;

public class PlayerMapper {
	
	public static PlayerTo map(PlayerEntity playerEntity) {
		if (playerEntity != null) {
			PlayerTo playerTO = new PlayerTo();
			playerTO.setAboutMe(playerEntity.getAboutMe());
			playerTO.setEmail(playerEntity.getEmail());
			playerTO.setId(playerEntity.getId());
			playerTO.setLifeMotto(playerEntity.getLifeMotto());
			playerTO.setLogin(playerEntity.getLogin());
			playerTO.setName(playerEntity.getName());
			playerTO.setPassword(playerEntity.getPassword());
			playerTO.setSurname(playerEntity.getSurname());
			return playerTO;
		}
		return null;
	}

	public static PlayerEntity map(PlayerTo playerTO) {
		if (playerTO != null) {
			PlayerEntity playerEntity = new PlayerEntity();
			playerEntity.setAboutMe(playerTO.getAboutMe());
			playerEntity.setEmail(playerTO.getEmail());
			playerEntity.setId(playerTO.getId());
			playerEntity.setLifeMotto(playerTO.getLifeMotto());
			playerEntity.setLogin(playerTO.getLogin());
			playerEntity.setName(playerTO.getName());
			playerEntity.setPassword(playerTO.getPassword());
			playerEntity.setSurname(playerTO.getSurname());
			return playerEntity;
		}
		return null;
	}
	
	public static PlayerEntity update(PlayerEntity playerEntity, PlayerTo playerTO) {
		if (playerTO != null && playerEntity != null) {
			playerEntity.setAboutMe(playerTO.getAboutMe());
			playerEntity.setEmail(playerTO.getEmail());
			playerEntity.setId(playerTO.getId());
			playerEntity.setLifeMotto(playerTO.getLifeMotto());
			playerEntity.setName(playerTO.getName());
			playerEntity.setPassword(playerTO.getPassword());
			playerEntity.setSurname(playerTO.getSurname());
		}
		return playerEntity;
	}
	
	public static List<PlayerTo> map2TOs(List<PlayerEntity> playerEntities) {
		return playerEntities.stream().map(PlayerMapper::map).collect(Collectors.toList());
	}

	public static List<PlayerEntity> map2Entities(List<PlayerTo> playerTOs) {
		return playerTOs.stream().map(PlayerMapper::map).collect(Collectors.toList());
	}
}
