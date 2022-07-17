package com.foxminded.formula.formatters;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.foxminded.formula.models.Racer;

public class TableFormatter {

	public String formatTable(List<Racer> racers) {
		AtomicInteger index = new AtomicInteger(1);

		return racers.stream()
				.map(racer -> convertRacer(racer, index))
				.collect(Collectors.joining("\n"));
	}

	private String convertRacer(Racer racer, AtomicInteger index) {
		int place = index.getAndIncrement();
		String racerAsStr = String.format("%1$-21s | %2$-27s | %3$tM:%3$tS.%3$tL", place + ". " + racer.getName(),
				racer.getTeam(), racer.getLapTime());
		if (place == 15) {
			String border = "\n---------------------------------------------------------------";
			return new StringBuilder(racerAsStr).append(border).toString();
		}
		return racerAsStr;
	}
}
