package lu.jemmic.addressbook.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains the list of contacts of the address book.
 */
public class AddressBook implements Serializable {

	private static final long serialVersionUID = -562469115229776855L;

	/**
	 * Map containing the list of contacts of the address book.
	 */
	private Map<String, Contact> contacts = new HashMap<String, Contact>();

	/**
	 * @return the map containing the list of contacts.
	 */
	public Map<String, Contact> getAllContacts() {
		return contacts;
	}

	/**
	 * Returns the contact having the given identifier.
	 *
	 * @param contactIdentifier
	 * @return the contact having the given identifier.
	 */
	public Contact getContact(String contactIdentifier) {
		return contacts.get(contactIdentifier);
	}

	/**
	 * Adds the given contact in the address book.
	 * Checks if a contact having the same identifier is already in the address
	 * book.
	 * The contact is not added if it is the case.
	 *
	 * @param contact
	 * @return false is a contact having the same identifier is already in the
	 *         address book.
	 */
	public boolean addContact(Contact contact) {
		boolean returnValue = false;

		String contactIdentifier = contact.getContactIdentifier();
		if (getContact(contactIdentifier) == null) {
			contacts.put(contactIdentifier, contact);
			returnValue = true;
		}
		return returnValue;
	}

	/**
	 * Removes a contact from the address book.
	 * Checks if a contact having the identifier of the given contact is in the
	 * address book.
	 * The contact cannot be removed if it is not the case.
	 *
	 * @param contact
	 * @return false if this contact was not in the address book.
	 */
	public boolean deleteContact(Contact contact) {
		boolean returnValue = false;
		String contactIdentifier = contact.getContactIdentifier();
		if (getContact(contactIdentifier) != null) {
			contacts.remove(contactIdentifier);
			returnValue = true;
		}
		return returnValue;
	}

}
