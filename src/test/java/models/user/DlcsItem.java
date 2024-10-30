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
public class DlcsItem{

	@JsonProperty("dlcName")
	private String dlcName;

	@JsonProperty("similarDlc")
	private SimilarDlc similarDlc;

	@JsonProperty("price")
	private int price;

	@JsonProperty("rating")
	private int rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("isDlcFree")
	private boolean isDlcFree;

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