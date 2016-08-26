package com.capgemini.chess.service.to;

import java.util.HashSet;
import java.util.Set;

public class PlayerTo extends BasicTo{
	
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	private String aboutMe;
	private String lifeMotto;
	
	private StaticticsTo statistics;
	
	private Set<ChallengeTo> receiverChallenges = new HashSet<ChallengeTo>(0);
	private Set<ChallengeTo> senderChallenges = new HashSet<ChallengeTo>(0);
	
	/**
	 * UserProfileTO default constructor.
	 */
	public PlayerTo(){
		
	}

	@Override
	public String toString() {
		return "PlayerTo [login=" + login + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", aboutMe=" + aboutMe + ", lifeMotto=" + lifeMotto + ", statisticks="
				+ statistics + ", receiverChallenges=" + receiverChallenges + ", senderChallenges=" + senderChallenges
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((aboutMe == null) ? 0 : aboutMe.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((lifeMotto == null) ? 0 : lifeMotto.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((receiverChallenges == null) ? 0 : receiverChallenges.hashCode());
		result = prime * result + ((senderChallenges == null) ? 0 : senderChallenges.hashCode());
		result = prime * result + ((statistics == null) ? 0 : statistics.hashCode());
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
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
		if (receiverChallenges == null) {
			if (other.receiverChallenges != null)
				return false;
		} else if (!receiverChallenges.equals(other.receiverChallenges))
			return false;
		if (senderChallenges == null) {
			if (other.senderChallenges != null)
				return false;
		} else if (!senderChallenges.equals(other.senderChallenges))
			return false;
		if (statistics == null) {
			if (other.statistics != null)
				return false;
		} else if (!statistics.equals(other.statistics))
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

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

	public StaticticsTo getStatistics() {
		return statistics;
	}

	public void setStatistics(StaticticsTo statistics) {
		this.statistics = statistics;
	}

	public Set<ChallengeTo> getReceiverChallenges() {
		return receiverChallenges;
	}

	public void setReceiverChallenges(Set<ChallengeTo> receiverChallenges) {
		this.receiverChallenges = receiverChallenges;
	}

	public Set<ChallengeTo> getSenderChallenges() {
		return senderChallenges;
	}

	public void setSenderChallenges(Set<ChallengeTo> senderChallenges) {
		this.senderChallenges = senderChallenges;
	}

	public PlayerTo(String login, String password, String name, String surname, String email, String aboutMe,
			String lifeMotto, StaticticsTo statisticks, Set<ChallengeTo> receiverChallenges,
			Set<ChallengeTo> senderChallenges) {
		super();
		this.login = login;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.aboutMe = aboutMe;
		this.lifeMotto = lifeMotto;
		this.statistics = statisticks;
		this.receiverChallenges = receiverChallenges;
		this.senderChallenges = senderChallenges;
	}
	
	
	
}
