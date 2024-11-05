package models.games;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DlcsItem{

	@JsonProperty("dlcName")
	private String dlcName;

	@JsonProperty("similarDlc")
	private SimilarDlc similarDlc;

	@JsonProperty("price")
	private Integer price;

	@JsonProperty("rating")
	private Integer rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("isDlcFree")
	private Boolean isDlcFree;

	public String getDlcName(){
		return dlcName;
	}

	public SimilarDlc getSimilarDlc(){
		return similarDlc;
	}

	public int getPrice(){
		return price;
	}

	public int getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public boolean isIsDlcFree(){
		return isDlcFree;
	}
}