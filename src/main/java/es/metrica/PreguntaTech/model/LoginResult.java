package es.metrica.PreguntaTech.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginResult {
	 @JsonProperty("token")
	private String token;
	 @JsonProperty("error")
	private String error;


public String getToken() {
	return token;
}
public void setToken(String token) {
	this.token = token;
}
public String getError() {
	return error;
}
public void setError(String error) {
	this.error = error;
}


}
