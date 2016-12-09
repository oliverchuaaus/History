package com.tougher.prattle.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Prattler {

	@Id
	@Column(name = "prattler_id")
	@SequenceGenerator(name = "PRATTLER_SEQ_GEN", sequenceName = "PRATTLER_SEQ", allocationSize = 50)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRATTLER_SEQ_GEN")
	private Long id;

	@Column(name = "username")
	@NotNull
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters long.")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must be alphanumeric with no spaces")
	private String username;

	@Column(name = "password")
	@NotNull
	@Size(min = 6, max = 20, message = "The password must be at least 6 characters long.")
	private String password;

	@Column(name = "full_name")
	@NotNull
	@Size(min = 3, max = 50, message = "Your full name must be between 3 and 50 characters long.")
	private String fullName;

	@Column(name = "email")
	@NotNull
	@Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Invalid email address.")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
