package com.foxminded.formula.managers;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.foxminded.formula.models.Racer;

public class RacersResolver {


	public List<Racer> createListOfRacers(FileReader reader){
		Map<String, Long> lapTime = calculateLapTime(reader);
		return reader.getRacersFromFile().stream()
				.map(racer -> {
					racer.setLapTime(lapTime.get(racer.getAbbreviation()));
					return racer;
				})
				.sorted(Comparator.comparingLong(Racer::getLapTime))
				.collect(Collectors.toList());
	}

	private Map<String, Long> calculateLapTime(FileReader reader){
		return Stream.of(reader.getEndTime(), reader.getStartTime())
				.map(Map::entrySet)
				.flatMap(Collection::stream)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Math::subtractExact));
	}

}
