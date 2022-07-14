package com.foxminded.formula.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.foxminded.formula.models.Racer;

class RacersResolverTest {
	
	private FileReader reader = new FileReader();
	private RacersResolver resolver = new RacersResolver(reader);

	@Test
	void createSortedListOfRacers_ShouldReturnSortedListOfRacers_WhenGivenTimesAndAbbreviations() {
		reader.setAbbreviations("abbreviationsTest.txt");
		reader.setEndLog("endTest.log");
		reader.setStartLog("startTest.log");
		List<Racer> actual = resolver.createListOfRacers();
		LinkedList<Racer> expected = new LinkedList<Racer>();
		        expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", 64415L));
				expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L));
		assertEquals(actual, expected);
	}


}
