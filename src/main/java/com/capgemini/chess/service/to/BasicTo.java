package com.capgemini.chess.service.to;

import java.util.Date;

public class BasicTo {

	private Long id;
	private int version;
	private Date createdAt;
	private Date modifiedAt;

	public BasicTo() {
		this.setCreatedAt(new Date());
		this.setModifiedAt(new Date());
	}

	public BasicTo(Long id, int version, Date createdAt, Date modifiedAt) {
		super();
		this.id = id;
		this.version = version;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modifiedAt == null) ? 0 : modifiedAt.hashCode());
		result = prime * result + version;
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
		BasicTo other = (BasicTo) obj;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modifiedAt == null) {
			if (other.modifiedAt != null)
				return false;
		} else if (!modifiedAt.equals(other.modifiedAt))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BasicTo [id=" + id + ", version=" + version + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt
				+ "]";
	}

}
