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
public class SimilarDlc{

	@JsonProperty("isFree")
	private boolean isFree;

	@JsonProperty("dlcNameFromAnotherGame")
	private String dlcNameFromAnotherGame;

	public boolean isIsFree(){
		return isFree;
	}

	public String getDlcNameFromAnotherGame(){
		return dlcNameFromAnotherGame;
	}
}