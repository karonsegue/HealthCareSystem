package entity;

public class HealthStandard {
	private String userId;
	private Double averageBodyTemperature;
	private Integer averageSleepTime;

	public HealthStandard() {

	}

	public String getUserId() {
	    return userId;
	}

	public void setUserId(String userId) {
	    this.userId = userId;
	}

	public Double getAverageBodyTemperature() {
	    return averageBodyTemperature;
	}

	public void setAverageBodyTemperature(Double averageBodyTemperature) {
	    this.averageBodyTemperature = averageBodyTemperature;
	}

	public Integer getAverageSleepTime() {
	    return averageSleepTime;
	}

	public void setAverageSleepTime(Integer averageSleepTime) {
	    this.averageSleepTime = averageSleepTime;
	}
}
