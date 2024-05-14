package edu.neu.csye6220.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
	private int userId;
    
    @Size(min = 3, max = 50, message = "Please enter first name of between size 3-50")
    @NotBlank
	private String fname;
    
    @Size(min = 3, max = 50, message = "Please enter last name of between size 3-50")
    @NotBlank
	private String lname;
    
    @Column(name="email", unique=true)
    @Email(message = "Please enter a valid e-mail address")
    @NotBlank
    @Size(min = 7, max = 50)
	private String email;
    
    @Size(min = 10, max = 10, message = "Please enter 10 digit phone number")
    @NotBlank
	private String contactNo;

   @Size(min = 7, max = 50, message = "Please enter password of between size 7-20")
    @NotBlank
	private String password;
	
    @Size(max = 100, message = "Please enter a shorter address")
    @NotBlank
	private String address;
	
    @NotBlank
	private String role;
	
	@Transient
	private String about;
	
	public User() {
	}

	public User( String fname, String lname, String email, String contactNo, String password, String address,
			String role) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contactNo = contactNo;
		this.password = password;
		this.address = address;
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", contactNo="
				+ contactNo + ", password=" + password + ", address=" + address + ", role=" + role + "]";
	}

	

}
