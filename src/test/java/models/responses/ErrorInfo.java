package models.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorInfo{

	@JsonProperty("path")
	private String path;

	@JsonProperty("error")
	private String error;

	@JsonProperty("timestamp")
	private String timestamp;

	@JsonProperty("status")
	private int status;

	public String getPath(){
		return path;
	}

	public String getError(){
		return error;
	}

	public String getTimestamp(){
		return timestamp;
	}

	public int getStatus(){
		return status;
	}
}