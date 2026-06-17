package entity;

import java.time.LocalDate;

public class HealthData {
	
	private Integer recordId;
	private String userId;
	private Double bodyTemperature;
	private Integer sleepTime;
	private String status;
	private LocalDate recordDate;
	private Integer editCount;
	
	public HealthData() {

	}

	public Integer getRecordId() {
	    return recordId;
	}

	public void setRecordId(Integer recordId) {
	    this.recordId = recordId;
	}

	public String getUserId() {
	    return userId;
	}

	public void setUserId(String userId) {
	    this.userId = userId;
	}

	public Double getBodyTemperature() {
	    return bodyTemperature;
	}

	public void setBodyTemperature(Double bodyTemperature) {
	    this.bodyTemperature = bodyTemperature;
	}

	public Integer getSleepTime() {
	    return sleepTime;
	}

	public void setSleepTime(Integer sleepTime) {
	    this.sleepTime = sleepTime;
	}

	public String getStatus() {
	    return status;
	}

	public void setStatus(String status) {
	    this.status = status;
	}

	public LocalDate getRecordDate() {
	    return recordDate;
	}

	public void setRecordDate(LocalDate recordDate) {
	    this.recordDate = recordDate;
	}

	public Integer getEditCount() {
	    return editCount;
	}

	public void setEditCount(Integer editCount) {
	    this.editCount = editCount;
	}
}
