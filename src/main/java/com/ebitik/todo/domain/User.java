package com.ebitik.todo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

/**
 * User entity
 * 
 * @author erdal.bitik
 * */

@Entity
@Table(name = "T_TODO_USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;
	
	/**
	 * email field is used as username
	 * */
	@NotNull(message="message.user.emailRequired")
	@Email(message="message.user.emailNotValid")
	@Column(name = "EMAIL", nullable = false, length = 255)
	private String email;

	/**
	 * name field can keep some information(name,surname) about user
	 * */
	@NotNull(message="message.user.nameRequired")
	@Column(name = "NAME", nullable = false, length = 255)
	private String name;
	
	/**
	 * surname field
	 * */
	@NotNull(message="message.user.surnameRequired")
	@Column(name = "SURNAME", nullable = false, length = 255)
	private String surname;

	/**
	 * plain password is stored as MD5 Hash
	 * */
	@Column(name = "PASSWORD_HASH", nullable = false, length = 255)
	private String passwordHash;
	
	public User() {}

	public User(long id) { 
		this.id = id;
	}

	public User(String email, String name) {
		this.email = email;
		this.name = name;
	}
	
	public User(Long id, String email, String name, String surname, String passwordHash) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.surname = surname;
		this.passwordHash = passwordHash; 
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", name=" + name + ", surname=" + surname + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		this.name = value;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

}