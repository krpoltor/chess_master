package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.UserEntity;
import com.capgemini.chess.service.to.UserProfileTo;

public class UserProfileMapper {
	
	public static UserProfileTo map(UserEntity userEntity) {
		if (userEntity != null) {
			UserProfileTo userTO = new UserProfileTo();
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

	public static UserEntity map(UserProfileTo userTO) {
		if (userTO != null) {
			UserEntity userEntity = new UserEntity();
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
	
	public static UserEntity update(UserEntity userEntity, UserProfileTo userTO) {
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
	
	public static List<UserProfileTo> map2TOs(List<UserEntity> userEntities) {
		return userEntities.stream().map(UserProfileMapper::map).collect(Collectors.toList());
	}

	public static List<UserEntity> map2Entities(List<UserProfileTo> userTOs) {
		return userTOs.stream().map(UserProfileMapper::map).collect(Collectors.toList());
	}
}
