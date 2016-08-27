package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.generated.entities.ChallengeEntity;
import com.capgemini.chess.service.to.ChallengeTo;

public class ChallengeMapper {

	public static ChallengeTo map(ChallengeEntity challengeEntity) {
		if (challengeEntity != null) {
			ChallengeTo challengeTO = new ChallengeTo();
			// Basic To
			challengeTO.setId(challengeEntity.getId());
			challengeTO.setVersion(challengeEntity.getVersion());
			challengeTO.setCreatedAt(challengeEntity.getCreatedAt());
			challengeTO.setModifiedAt(challengeEntity.getModifiedAt());
			// Challenge To
			challengeTO.setGame(GameMapper.map(challengeEntity.getGame()));
			challengeTO.setWhitePlayer(PlayerMapper.map(challengeEntity.getSender()));
			challengeTO.setBlackPlayer(PlayerMapper.map(challengeEntity.getReceiver()));
			challengeTO.setStartDate(challengeEntity.getStartDate());
			challengeTO.setEndDate(challengeEntity.getEndDate());
			challengeTO.setStatus(challengeEntity.getStatus());
			return challengeTO;
		}
		return null;
	}

	public static ChallengeEntity map(ChallengeTo challengeTO) {
		if (challengeTO != null) {
			ChallengeEntity challengeEntity = new ChallengeEntity();
			// Basic Entity
			challengeEntity.setId(challengeTO.getId());
			challengeEntity.setVersion(challengeTO.getVersion());
			challengeEntity.setCreatedAt(challengeTO.getCreatedAt());
			challengeEntity.setModifiedAt(challengeTO.getModifiedAt());
			// Challenge Entity
			challengeEntity.setGame(GameMapper.map(challengeTO.getGame()));
			challengeEntity.setSender(PlayerMapper.map(challengeTO.getWhitePlayer()));
			challengeEntity.setReceiver(PlayerMapper.map(challengeTO.getBlackPlayer()));
			challengeEntity.setStartDate(challengeTO.getStartDate());
			challengeEntity.setEndDate(challengeTO.getEndDate());
			challengeEntity.setStatus(challengeTO.getStatus());
			return challengeEntity;
		}
		return null;
	}

	public static ChallengeEntity update(ChallengeEntity challengeEntity, ChallengeTo challengeTO) {
		if (challengeTO != null && challengeEntity != null) {
			// Challenge Entity
			if (challengeTO.getGame() != null) {
				challengeEntity.setGame(GameMapper.map(challengeTO.getGame()));
			}
			if (challengeTO.getWhitePlayer() != null) {
				challengeEntity.setSender(PlayerMapper.map(challengeTO.getWhitePlayer()));
			}
			if (challengeTO.getBlackPlayer() != null) {
				challengeEntity.setReceiver(PlayerMapper.map(challengeTO.getBlackPlayer()));
			}
			if (challengeTO.getStartDate() != null) {
				challengeEntity.setStartDate(challengeTO.getStartDate());
			}
			if (challengeTO.getEndDate() != null) {
				challengeEntity.setEndDate(challengeTO.getEndDate());
			}
			if (challengeTO.getStatus() != null) {
				challengeEntity.setStatus(challengeTO.getStatus());
			}
		}
		return challengeEntity;
	}

	public static List<ChallengeTo> map2TOs(List<ChallengeEntity> challengeEntities) {
		return challengeEntities.stream().map(ChallengeMapper::map).collect(Collectors.toList());
	}

	public static List<ChallengeEntity> map2Entities(List<ChallengeTo> challengeTOs) {
		return challengeTOs.stream().map(ChallengeMapper::map).collect(Collectors.toList());
	}
}
