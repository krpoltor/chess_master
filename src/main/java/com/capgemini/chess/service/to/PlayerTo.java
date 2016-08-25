package com.capgemini.chess.service.to;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerTo {
	
	private Long id;
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
	public PlayerTo(){
		
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
	public PlayerTo(Long id, String login, String password, String name, String surname, String email,
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
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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

	@Override
	public String toString() {
		return "PlayerTo [id=" + id + ", login=" + login + ", name=" + name + ", surname=" + surname + ", email="
				+ email + ", aboutMe=" + aboutMe + ", lifeMotto=" + lifeMotto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lifeMotto == null) ? 0 : lifeMotto.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlayerTo other = (PlayerTo) obj;
		if (aboutMe == null) {
			if (other.aboutMe != null)
				return false;
		} else if (!aboutMe.equals(other.aboutMe))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lifeMotto == null) {
			if (other.lifeMotto != null)
				return false;
		} else if (!lifeMotto.equals(other.lifeMotto))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	//CHECKSTYLE:ON
}
