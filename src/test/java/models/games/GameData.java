package models.games;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Bool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameData {

	@JsonProperty("gameId")
	private Integer gameId;

	@JsonProperty("requirements")
	private Requirements requirements;

	@JsonProperty("requiredAge")
	private Boolean requiredAge;

	@JsonProperty("rating")
	private Integer rating;

	@JsonProperty("description")
	private String description;

	@JsonProperty("title")
	private String title;

	@JsonProperty("tags")
	private List<String> tags;

	@JsonProperty("isFree")
	private Boolean isFree;

	@JsonProperty("price")
	private Integer price;

	@JsonProperty("dlcs")
	private List<DlcsItem> dlcs;

	@JsonProperty("genre")
	private String genre;

	@JsonProperty("company")
	private String company;

	@JsonProperty("publish_date")
	private String publishDate;

	public Integer getGameId(){
		return gameId;
	}

	public Requirements getRequirements(){
		return requirements;
	}

	public Boolean isRequiredAge(){
		return requiredAge;
	}

	public Integer getRating(){
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

	public Boolean isIsFree(){
		return isFree;
	}

	public Integer getPrice(){
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