package com.foxminded.formula.managers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.foxminded.formula.models.Racer;

@ExtendWith(MockitoExtension.class)
class RacersResolverTest {
	
	@Mock
	private FileReader reader;
	
	@InjectMocks
	private RacersResolver resolver = new RacersResolver(reader);

	@Test
	void createSortedListOfRacers_ShouldReturnSortedListOfRacers_WhenGivenTimesAndAbbreviations() {
		
		reader.setAbbreviations("abbreviationsTest.txt");
		reader.setEndLog("endTest.log");
		reader.setStartLog("startTest.log");
		LinkedList<Racer> racers = new LinkedList<Racer>();
		racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI"));
		racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));	
		HashMap<String, Long> startTime = new HashMap<String, Long>();
		HashMap<String, Long> endTime = new HashMap<String, Long>();
		startTime.put("SVF", 1527152578917L);
		startTime.put("DRR", 1527153252054L);
		endTime.put("SVF", 1527152643332L);
		endTime.put("DRR", 1527153324067L);
		LinkedList<Racer> expected = new LinkedList<Racer>();
		        expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", 64415L));
				expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", 72013L));				
		Mockito.when(reader.getRacersFromFile()).thenReturn(racers);
		Mockito.when(reader.getStartTime()).thenReturn(startTime);
		Mockito.when(reader.getEndTime()).thenReturn(endTime);
		List<Racer> actual = resolver.createListOfRacers();
		assertEquals(expected, actual);
	}


}
