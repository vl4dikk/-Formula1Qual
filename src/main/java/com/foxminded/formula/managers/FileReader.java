package com.foxminded.formula.managers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.foxminded.formula.models.Racer;

public class FileReader {

	private String abbreviations;
	private String start;
	private String end;
	private final static DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
	private ClassLoader classLoader = getClass().getClassLoader();

	public void setAbbreviations(String file) {
		this.abbreviations = file;
	}

	public void setStartLog(String file) {
		this.start = file;
	}

	public void setEndLog(String file) {
		this.end = file;
	}

	public List<Racer> getRacersFromFile() {
		return new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(abbreviations))).lines()
				.map(line -> line.split("_"))
				.map(racer -> new Racer(racer[0], racer[1], racer[2], 0))
				.collect(Collectors.toList());
	}

	public Map<String, Long> getStartTime() {
		return getTimeFromFile(start);
	}

	public Map<String, Long> getEndTime() {
		return getTimeFromFile(end);
	}

	private Map<String, Long> getTimeFromFile(String file) {
		List<String> dataFromFile;
		Map<String, Long> result = new HashMap<String, Long>();
		dataFromFile = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(file))).lines()
				.toList();
		result = dataFromFile.stream().collect(Collectors.toMap(key -> key.substring(0, 3), value -> {
			String date = value.substring(3);
			LocalDateTime localDateTime = LocalDateTime.parse(date, DATE_PATTERN);
			return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
		}));
		return result;
	}

}
