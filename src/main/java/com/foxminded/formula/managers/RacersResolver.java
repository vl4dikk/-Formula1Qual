package com.foxminded.formula.managers;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.foxminded.formula.models.Racer;

public class RacersResolver {

	private FileReader reader;

	public RacersResolver(FileReader reader) {
		this.reader = reader;
	}

	public List<Racer> createListOfRacers() {
		Map<String, Long> lapTime = calculateLapTime();
		return reader.getRacersFromFile().stream()
				.map(racer -> {
					racer.setLapTime(lapTime.get(racer.getAbbreviation()));
					return racer;
				})
				.sorted(Comparator.comparingLong(Racer::getLapTime))
				.collect(Collectors.toList());
	}

	private Map<String, Long> calculateLapTime() {
		return Stream.of(reader.getEndTime(), reader.getStartTime())
				.map(Map::entrySet)
				.flatMap(Collection::stream)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Math::subtractExact));
	}

}
