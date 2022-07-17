package com.foxminded.formula.models;

public class Racer {

	private String abbreviation;
	private String name;
	private String team;
	private long lapTime;

	public Racer(String abbreviation, String name, String team, long lapTime) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.team = team;
		this.lapTime = lapTime;
	}

	public Racer(String abbreviation, String name, String team) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.team = team;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public long getLapTime() {
		return lapTime;
	}

	public void setLapTime(long lapTime) {
		this.lapTime = lapTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
		result = prime * result + (int) (lapTime ^ (lapTime >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Racer other = (Racer) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
			return false;
		if (lapTime != other.lapTime)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

}
