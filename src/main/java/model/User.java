package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends entity.MetaInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="\"createdTime\"")
	private String createdTime;

	@Column(name="\"emailAddress\"")
	private String emailAddress;

	@Column(name="\"name\"")
	private String name;

	@Column(name="\"password\"")
	private String password;

	@Column(name="\"updatedTime\"")
	private String updatedTime;

	public User() {
	}

	public String getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

}