package com.joemad.model.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "creation_date")
	private Date creationDate;

	@Column(name = "email")
	private String email;

	@Column(name = "failed_login_attempts")
	private int failedLoginAttempts;

	@Column(name = "ip_address")
	private String ipAddress;

	@Column(name = "last_read_message_id")
	private Long lastReadMessageId;

	@Column(name = "locked")
	private int locked;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "status")
	private int status;

	@Column(name = "username")
	private String username;

	@Column(name = "verified")
	private int verified;
	
	@Column(name = "generated_code")
	private String generatedCode;

	public User() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public void setFailedLoginAttempts(int failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Long getLastReadMessageId() {
		return lastReadMessageId;
	}

	public void setLastReadMessageId(Long lastReadMessageId) {
		this.lastReadMessageId = lastReadMessageId;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public String getGeneratedCode() {
		return generatedCode;
	}

	public void setGeneratedCode(String generatedCode) {
		this.generatedCode = generatedCode;
	}

	@Override
	public boolean equals(Object obj) {
			if(obj!=null && obj instanceof User) {
				User userObj = (User) obj;
				if(this.username.equals(userObj.getUsername())){
					return true;
				}
			}
			return false;
	}

	@Override
	public int hashCode() {
		return this.userId.intValue();
	}
}