package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Info{

	@JsonProperty("info")
	private Info info;

	@JsonProperty("message")
	private String message;

	@JsonProperty("status")
	private String status;

	public Info getInfo(){
		return info;
	}

	public String getMessage(){
		return message;
	}

	public String getStatus(){
		return status;
	}
}