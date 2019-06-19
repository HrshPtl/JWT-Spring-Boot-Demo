package com.byteparity.employee.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.byteparity.employee.config.CustomerDateAndTimeDeserialize;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "tb_employee")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createAt, updateAt"}, allowGetters = true)
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	
	@Size(min=2, max=24, message="Your first name should be between 2 - 24 characters.")
	@NotBlank
	private String firstName;
	
	@Size(min=2, max=24, message="Your last name should be between 2 - 24 characters.")
	@NotBlank
	private String lastName;
	
	@JsonDeserialize(using=CustomerDateAndTimeDeserialize.class)
	private Date dob;
	
	@Pattern(regexp=".+@.+\\..+", message="Wrong email!")
	@NotBlank
	private String email;
	
	private Long contactNumber;
	
	
	@Column(nullable=false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
