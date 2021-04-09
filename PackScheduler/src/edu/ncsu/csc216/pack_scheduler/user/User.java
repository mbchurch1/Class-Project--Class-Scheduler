package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Creates the User object, provides object getters and setters,
 * overrides standard equals and hashcode methods
 * @author John Firlet
 * @author Matt Church
 * @author Will Goodwin
 *
 */
public abstract class User {

	/** users first name */
	private String firstName;
	/** users last name */
	private String lastName;
	/** users id number */
	private String id;
	/** users email */
	private String email;
	/** users password */
	private String password;

	

	/**
	 * User constructor
	 * @param firstName  First name of user
	 * @param lastName  Last name of user
	 * @param id  ID of user
	 * @param email  email address of user
	 * @param hashPW  User password in SHA-256 encryption
	 */
	public User(String firstName, String lastName, String id, String email, String hashPW) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(hashPW);
	}


	/**
	 * Gets the users first name
	 * 
	 * @return firstName  Returns the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the users last name
	 * 
	 * @return lastName  Returns the last name of the user
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the users Id number
	 * 
	 * @return id  Returns the users ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the users email
	 * 
	 * @return email  Returns the users email address
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Gets the users password
	 * 
	 * @return password  Returns the users password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the users first name. If the name is empty or null, throws
	 * IllegalArgumentException
	 * 
	 * @param firstName the firstName to set
	 * @throws IllegalArgumentException if the name is invalid
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.length() == 0) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * Sets the users last name. If the name is empty or null, throws an
	 * IllegalArgumentException
	 * 
	 * @param lastName the lastName to set
	 * @throws IllegalArgumentException if the lastName is invalid
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.length() == 0) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * Sets the users Id. If the id is null or empty, throws
	 * IllegalArgumentException
	 * 
	 * @param id the id to set
	 * @throws IllegalArgumentException if the id is invalid
	 */
	protected void setId(String id) {
		if (id == null || id.length() == 0) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * Sets the users email. If the email is null, empty, does not contain and
	 * "@" or ".", or the last "." is before "@", throws an IllegalArgumentException
	 * 
	 * @param email the email to set
	 * @throws IllegalArgumentException if the email is invalid
	 */
	public void setEmail(String email) {
		if (email == null || email.length() == 0) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!email.contains(".")) {
			throw new IllegalArgumentException("Invalid email");
		}
		int atChar = email.indexOf('@');
		int periodChar = email.lastIndexOf('.');
		if (atChar > periodChar) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * Sets the users password. If the password is null or empty, throws
	 * IllegalArgumentException.
	 * 
	 * @param password the password to set
	 * @throws IllegalArgumentException if the password is invalid
	 */
	public void setPassword(String password) {
		if (password == null || password.length() == 0) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.password = password;
	}

	/**
	 * Generates a hashCode for User using all fields.
	 * 
	 * @return hashCode for User
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/**
	 * Compares a given object to this object for equality in all fields.
	 * 
	 * @param obj of the object to compare
	 * @return true of the object are the same in all fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

}