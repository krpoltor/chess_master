package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.generated.entities.PlayerEntity;
import com.capgemini.chess.service.to.PlayerTo;

public class PlayerMapper {
	
	public static PlayerTo map(PlayerEntity userEntity) {
		if (userEntity != null) {
			PlayerTo userTO = new PlayerTo();
			userTO.setAboutMe(userEntity.getAboutMe());
			userTO.setEmail(userEntity.getEmail());
			userTO.setId(userEntity.getId());
			userTO.setLifeMotto(userEntity.getLifeMotto());
			userTO.setLogin(userEntity.getLogin());
			userTO.setName(userEntity.getName());
			userTO.setPassword(userEntity.getPassword());
			userTO.setSurname(userEntity.getSurname());
			return userTO;
		}
		return null;
	}

	public static PlayerEntity map(PlayerTo userTO) {
		if (userTO != null) {
			PlayerEntity userEntity = new PlayerEntity();
			userEntity.setAboutMe(userTO.getAboutMe());
			userEntity.setEmail(userTO.getEmail());
			userEntity.setId(userTO.getId());
			userEntity.setLifeMotto(userTO.getLifeMotto());
			userEntity.setLogin(userTO.getLogin());
			userEntity.setName(userTO.getName());
			userEntity.setPassword(userTO.getPassword());
			userEntity.setSurname(userTO.getSurname());
			return userEntity;
		}
		return null;
	}
	
	public static PlayerEntity update(PlayerEntity userEntity, PlayerTo userTO) {
		if (userTO != null && userEntity != null) {
			userEntity.setAboutMe(userTO.getAboutMe());
			userEntity.setEmail(userTO.getEmail());
			userEntity.setId(userTO.getId());
			userEntity.setLifeMotto(userTO.getLifeMotto());
			userEntity.setName(userTO.getName());
			userEntity.setPassword(userTO.getPassword());
			userEntity.setSurname(userTO.getSurname());
		}
		return userEntity;
	}
	
	public static List<PlayerTo> map2TOs(List<PlayerEntity> userEntities) {
		return userEntities.stream().map(PlayerMapper::map).collect(Collectors.toList());
	}

	public static List<PlayerEntity> map2Entities(List<PlayerTo> userTOs) {
		return userTOs.stream().map(PlayerMapper::map).collect(Collectors.toList());
	}
}
