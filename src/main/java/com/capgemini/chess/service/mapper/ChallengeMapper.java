package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.dataaccess.entities.ChallengeEntity;
import com.capgemini.chess.service.to.ChallengeTo;

public class ChallengeMapper {
	
	public static ChallengeTo map(ChallengeEntity challengeEntity) {
		if (challengeEntity != null) {
			ChallengeTo challengeTO = new ChallengeTo();
			challengeTO.setId(challengeEntity.getId());
			challengeTO.setWhitePlayerId(challengeEntity.getWhitePlayerId());
			challengeTO.setBlackPlayerId(challengeEntity.getBlackPlayerId());
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
			challengeEntity.setId(challengeTO.getId());
			challengeEntity.setWhitePlayerId(challengeTO.getWhitePlayerId());
			challengeEntity.setBlackPlayerId(challengeTO.getBlackPlayerId());
			challengeEntity.setStartDate(challengeTO.getStartDate());
			challengeEntity.setEndDate(challengeTO.getEndDate());
			challengeEntity.setStatus(challengeTO.getStatus());
			return challengeEntity;
		}
		return null;
	}
	
	public static ChallengeEntity update(ChallengeEntity challengeEntity, ChallengeTo challengeTO) {
		if (challengeTO != null && challengeEntity != null) {
			challengeEntity.setId(challengeTO.getId());
			challengeEntity.setWhitePlayerId(challengeTO.getWhitePlayerId());
			challengeEntity.setBlackPlayerId(challengeTO.getBlackPlayerId());
			challengeEntity.setStartDate(challengeTO.getStartDate());
			challengeEntity.setEndDate(challengeTO.getEndDate());
			challengeEntity.setStatus(challengeTO.getStatus());
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
