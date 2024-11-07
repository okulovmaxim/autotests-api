package models.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import models.games.GameData;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserData{

	@JsonProperty("pass")
	private String pass;

	@JsonProperty("games")
	private List<GameData> games;

	@JsonProperty("login")
	private String login;

	public String getPass(){
		return pass;
	}

	public List<GameData> getGames(){
		return games;
	}

	public String getLogin(){
		return login;
	}
}