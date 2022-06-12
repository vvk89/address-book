package lu.jemmic.addressbook.model;

import java.io.Serializable;

/**
 * Contains all the data about a contact.
 */
public class Contact implements Serializable {

	private static final long serialVersionUID = 7276244960021961904L;

	public static int ageMax; // the maximum age of a contact

	private String name;
	private String surname;
	private String telephoneNumber;
	private String email;
	private Integer age;
	private Hair hair;
	private Category category;

	/**
	 * @return the name of the contact.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Used to change the name of the contact.
	 * As this field is mandatory, this method returns false is the given name is
	 * empty.
	 *
	 * @param name
	 * @return true is the name is not empty
	 */
	public boolean setName(String name) {
		boolean ok = !name.isEmpty();
		if (ok) {
			this.name = name;
		}
		return ok;
	}

	/**
	 * @return the surname of the contact.
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Used to change the surname of the contact.
	 * As this field is mandatory, this method returns false is the given surname is
	 * empty.
	 *
	 * @param surname
	 * @return true is the surname is not empty
	 */
	public boolean setSurname(String surname) {
		boolean ok = !surname.isEmpty();
		if (ok) {
			this.surname = surname;
		}
		return ok;
	}

	/**
	 * @return the telephone number of the contact.
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * Used to change the telephone number of the contact.
	 * As this field is mandatory, this method returns false is the given telephone
	 * number is empty.
	 * It returns also false is the telephone number is not valid.
	 *
	 * @param telephoneNumber
	 * @return true is the telephone number is not empty and is valid
	 */
	public boolean setTelephoneNumber(String telephoneNumber) {
		String patterns = "^\\+?[0-9 ]*";
		boolean ok = (!telephoneNumber.isEmpty()) && telephoneNumber.matches(patterns);

		if (ok) {
			this.telephoneNumber = telephoneNumber;
		}

		return ok;
	}

	/**
	 * @return the email address of the contact.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Used to change the email address of the contact.
	 * As this field is mandatory, this method returns false is the given email
	 * address is empty.
	 * It returns also false is the email address is not valid.
	 *
	 * @param email
	 * @return true is the email address is not empty and is valid
	 */
	public boolean setEmail(String email) {
		String patterns = "^[A-Za-z0-9.]+@[A-Za-z.]+$";
		boolean ok = (!email.isEmpty()) && email.matches(patterns);
		if (ok) {
			this.email = email;
		}
		return ok;
	}

	/**
	 * @return the age of the contact.
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Used to change the age of the contact.
	 * This method returns false is the age is not between 0 and the maximum age of
	 * a contact.
	 *
	 * @param surname
	 * @return true is the age is valid
	 */
	public boolean setAge(Integer age) {
		boolean ok = (age == null || age <= ageMax);
		if (ok) {
			this.age = age;
		}
		return ok;
	}

	/**
	 * @return the hair instance of the contact.
	 */
	public Hair getHair() {
		return hair;
	}

	/**
	 * Used to change the hair instance of the contact.
	 *
	 * @param hair
	 */
	public void setHair(Hair hair) {
		this.hair = hair;
	}

	/**
	 * @return the category instance of the contact.
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Used to change the category instance of the contact.
	 *
	 * @param hair
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the identifier of the contact.
	 */
	public String getContactIdentifier() {
		return name + " " + surname;
	}

}
