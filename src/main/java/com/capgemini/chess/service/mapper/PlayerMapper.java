package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.generated.entities.PlayerEntity;
import com.capgemini.chess.service.to.PlayerTo;

public class PlayerMapper {

	public static PlayerTo map(PlayerEntity playerEntity) {
		if (playerEntity != null) {
			PlayerTo playerTO = new PlayerTo();
			// Basic To
			playerTO.setId(playerEntity.getId());
			playerTO.setVersion(playerEntity.getVersion());
			playerTO.setCreatedAt(playerEntity.getCreatedAt());
			playerTO.setModifiedAt(playerEntity.getModifiedAt());
			// Player To
			playerTO.setLogin(playerEntity.getLogin());
			playerTO.setPassword(playerEntity.getPassword());
			playerTO.setName(playerEntity.getName());
			playerTO.setSurname(playerEntity.getSurname());
			playerTO.setEmail(playerEntity.getEmail());
			playerTO.setAboutMe(playerEntity.getAboutMe());
			playerTO.setLifeMotto(playerEntity.getLifeMotto());
			playerTO.setStatistics(StatisticsMapper.map(playerEntity.getStatistics()));

			return playerTO;
		}
		return null;
	}

	public static PlayerEntity map(PlayerTo playerTO) {
		if (playerTO != null) {
			PlayerEntity playerEntity = new PlayerEntity();
			// Basic Entity
			playerEntity.setId(playerTO.getId());
			playerEntity.setVersion(playerTO.getVersion());
			playerEntity.setCreatedAt(playerTO.getCreatedAt());
			playerEntity.setModifiedAt(playerTO.getModifiedAt());
			// Player Entity
			playerEntity.setLogin(playerTO.getLogin());
			playerEntity.setPassword(playerTO.getPassword());
			playerEntity.setName(playerTO.getName());
			playerEntity.setSurname(playerTO.getSurname());
			playerEntity.setEmail(playerTO.getEmail());
			playerEntity.setAboutMe(playerTO.getAboutMe());
			playerEntity.setLifeMotto(playerTO.getLifeMotto());
			playerEntity.setStatistics(StatisticsMapper.map(playerTO.getStatistics()));

			return playerEntity;
		}
		return null;
	}

	public static PlayerEntity update(PlayerEntity playerEntity, PlayerTo playerTO) {
		if (playerTO != null && playerEntity != null) {
			// Player Entity
			if (!playerTO.getLogin().equals(null)) {
				playerEntity.setLogin(playerTO.getLogin());
			}
			if (!playerTO.getPassword().equals(null)) {
				playerEntity.setPassword(playerTO.getPassword());
			}
			if (!playerTO.getName().equals(null)) {
				playerEntity.setName(playerTO.getName());
			}
			if (!playerTO.getSurname().equals(null)) {
				playerEntity.setSurname(playerTO.getSurname());
			}
			if (!playerTO.getEmail().equals(null)) {
				playerEntity.setEmail(playerTO.getEmail());
			}
			if (!playerTO.getAboutMe().equals(null)) {
				playerEntity.setAboutMe(playerTO.getAboutMe());
			}
			if (!playerTO.getLifeMotto().equals(null)) {
				playerEntity.setLifeMotto(playerTO.getLifeMotto());
			}
			if (!playerTO.getStatistics().equals(null)) {
				playerEntity.setStatistics(StatisticsMapper.map(playerTO.getStatistics()));
			}
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
