package models.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Requirements{

	@JsonProperty("videoCard")
	private String videoCard;

	@JsonProperty("hardDrive")
	private int hardDrive;

	@JsonProperty("osName")
	private String osName;

	@JsonProperty("ramGb")
	private int ramGb;

	public String getVideoCard(){
		return videoCard;
	}

	public int getHardDrive(){
		return hardDrive;
	}

	public String getOsName(){
		return osName;
	}

	public int getRamGb(){
		return ramGb;
	}
}