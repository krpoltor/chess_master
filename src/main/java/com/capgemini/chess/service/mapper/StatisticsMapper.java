package com.capgemini.chess.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.chess.generated.entities.StatisticsEntity;
import com.capgemini.chess.service.to.StatisticsTo;

public class StatisticsMapper {

	public static StatisticsTo map(StatisticsEntity statisticsEntity) {
		if (statisticsEntity != null) {
			StatisticsTo statisticsTO = new StatisticsTo();
			// Basic To
			statisticsTO.setId(statisticsEntity.getId());
			statisticsTO.setVersion(statisticsEntity.getVersion());
			statisticsTO.setCreatedAt(statisticsEntity.getCreatedAt());
			statisticsTO.setModifiedAt(statisticsEntity.getModifiedAt());
			// Statistics To
			statisticsTO.setLostGames(statisticsEntity.getLostGames());
			statisticsTO.setNumberOfMatches(statisticsEntity.getNumberOfMatches());
			statisticsTO.setPlayerLevel(statisticsEntity.getPlayerLevel());
			statisticsTO.setPlayerPoints(statisticsEntity.getPlayerPoints());
			statisticsTO.setTiedGames(statisticsEntity.getTiedGames());
			statisticsTO.setWinLoseRatio(statisticsEntity.getWinLoseRatio());
			statisticsTO.setWonGames(statisticsEntity.getWonGames());
			return statisticsTO;
		}
		return null;
	}

	public static StatisticsEntity map(StatisticsTo statisticsTO) {
		if (statisticsTO != null) {
			StatisticsEntity statisticsEntity = new StatisticsEntity();
			// Basic Entity
			statisticsEntity.setId(statisticsTO.getId());
			statisticsEntity.setVersion(statisticsTO.getVersion());
			statisticsEntity.setCreatedAt(statisticsTO.getCreatedAt());
			statisticsEntity.setModifiedAt(statisticsTO.getModifiedAt());
			// Statistics Entity
			statisticsEntity.setLostGames(statisticsTO.getLostGames());
			statisticsEntity.setNumberOfMatches(statisticsTO.getNumberOfMatches());
			statisticsEntity.setPlayerLevel(statisticsTO.getPlayerLevel());
			statisticsEntity.setPlayerPoints(statisticsTO.getPlayerPoints());
			statisticsEntity.setTiedGames(statisticsTO.getTiedGames());
			statisticsEntity.setWinLoseRatio(statisticsTO.getWinLoseRatio());
			statisticsEntity.setWonGames(statisticsTO.getWonGames());
			return statisticsEntity;
		}
		return null;
	}

	public static StatisticsEntity update(StatisticsEntity statisticsEntity, StatisticsTo statisticsTO) {
		if (statisticsTO != null && statisticsEntity != null) {
			// Statistics Entity
			if (!statisticsTO.getLostGames().equals(null)) {
				statisticsEntity.setLostGames(statisticsTO.getLostGames());
			}
			if (!statisticsTO.getNumberOfMatches().equals(null)) {
				statisticsEntity.setNumberOfMatches(statisticsTO.getNumberOfMatches());
			}
			if (!statisticsTO.getPlayerLevel().equals(null)) {
				statisticsEntity.setPlayerLevel(statisticsTO.getPlayerLevel());
			}
			if (!statisticsTO.getPlayerPoints().equals(null)) {
				statisticsEntity.setPlayerPoints(statisticsTO.getPlayerPoints());
			}
			if (!statisticsTO.getTiedGames().equals(null)) {
				statisticsEntity.setTiedGames(statisticsTO.getTiedGames());
			}
			if (!statisticsTO.getWinLoseRatio().equals(null)) {
				statisticsEntity.setWinLoseRatio(statisticsTO.getWinLoseRatio());
			}
			if (!statisticsTO.getWonGames().equals(null)) {
				statisticsEntity.setWonGames(statisticsEntity.getWonGames());
			}
		}
		return statisticsEntity;
	}

	public static List<StatisticsTo> map2TOs(List<StatisticsEntity> statisticsEntities) {
		return statisticsEntities.stream().map(StatisticsMapper::map).collect(Collectors.toList());
	}

	public static List<StatisticsEntity> map2Entities(List<StatisticsTo> statisticsTOs) {
		return statisticsTOs.stream().map(StatisticsMapper::map).collect(Collectors.toList());
	}

}
