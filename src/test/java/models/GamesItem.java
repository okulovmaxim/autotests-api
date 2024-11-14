package models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamesItem{

	@JsonProperty("gameId")
	private int gameId;

	@JsonProperty("requirements")
	private Requirements requirements;

	@JsonProperty("requiredAge")
	private boolean requiredAge;

	@JsonProperty("rating")
	private int rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("title")
	private String title;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("isFree")
	private boolean isFree;

	@JsonProperty("price")
	private int price;

	@JsonProperty("dlcs")
	private List<DlcsItem> dlcs;

	@JsonProperty("genre")
	private String genre;

	@JsonProperty("company")
	private String company;

	@JsonProperty("publish_date")
	private String publishDate;

	public int getGameId(){
		return gameId;
	}

	public Requirements getRequirements(){
		return requirements;
	}

	public boolean isRequiredAge(){
		return requiredAge;
	}

	public int getRating(){
		return rating;
	}

	public String getDescription(){
		return description;
	}

	public String getTitle(){
		return title;
	}

	public List<String> getTags(){
		return tags;
	}

	public boolean isIsFree(){
		return isFree;
	}

	public int getPrice(){
		return price;
	}

	public List<DlcsItem> getDlcs(){
		return dlcs;
	}

	public String getGenre(){
		return genre;
	}

	public String getCompany(){
		return company;
	}

	public String getPublishDate(){
		return publishDate;
	}
}