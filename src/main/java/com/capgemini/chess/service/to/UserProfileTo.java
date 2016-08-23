package com.capgemini.chess.service.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfileTo {
	
	private long id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String aboutMe;
	private String lifeMotto;
	
	/**
	 * UserProfileTO default constructor.
	 */
	public UserProfileTo(){
		
	}
	
	/**
	 * UserProfileTO parameterized constructor.
	 * 
	 * @param id - User's ID.
	 * @param login - User's login. 
	 * @param password - User's password.
	 * @param name - User's name.
	 * @param surname - User's surname.
	 * @param email - User's email.
	 * @param aboutMe  - User's aboutMe field.
	 * @param lifeMotto - User's life motto.
	 */
	public UserProfileTo(long id, String login, String password, String name, String surname, String email,
			String aboutMe, String lifeMotto) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.aboutMe = aboutMe;
		this.lifeMotto = lifeMotto;
	}
	
	//CHECKSTYLE:OFF
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAboutMe() {
		return aboutMe;
	}
	
	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	
	public String getLifeMotto() {
		return lifeMotto;
	}
	
	public void setLifeMotto(String lifeMotto) {
		this.lifeMotto = lifeMotto;
	}

	//CHECKSTYLE:ON
}
