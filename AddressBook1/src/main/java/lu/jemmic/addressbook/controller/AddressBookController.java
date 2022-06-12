package lu.jemmic.addressbook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import lu.jemmic.addressbook.model.AddressBook;
import lu.jemmic.addressbook.model.Category;
import lu.jemmic.addressbook.model.CategoryAcquaintance;
import lu.jemmic.addressbook.model.CategoryFamily;
import lu.jemmic.addressbook.model.CategoryFriends;
import lu.jemmic.addressbook.model.Contact;
import lu.jemmic.addressbook.model.Hair;
import lu.jemmic.addressbook.utils.FileUtils;
import lu.jemmic.addressbook.view.CommandLine;

/**
 * Contains the main process and other useful methods.
 */
public class AddressBookController {
	private CommandLine commandLine = new CommandLine(); // useful for the user interface.
	private AddressBook addressBook = new AddressBook(); // the address book
	private String addressBookFilename; // the folder and the filename of the file used to save the address book.

	/**
	 * Enum containing the possible choices of the main menu.
	 */
	public static enum MainMenuSelection {
		DISPLAY_LIST_CONTACTS,
		ADD_CONTACT,
		DISPLAY_CONTACT,
		REMOVE_CONTACT,
		EDIT_CONTACT,
		SAVE_QUIT,
		CANCEL_QUIT
	};

	/**
	 * Enum containing the possible fields of a contact.
	 */
	public static enum ContactData {
		NAME,
		SURNAME,
		TELEPHONE_NUMBER,
		EMAIL,
		AGE,
		HAIR_COLOR,
		CATEGORY_TYPE,
		CATEGORY_NAME,
		FAMILY_RELATIONSHIPS,
		FRIENDS_FRIENDSHIPYEARS,
		OTHER
	};

	/**
	 * Displays the list of contacts.
	 *
	 * @return the sorted list of contacts.
	 */
	private List<String> displayListContacts() {
		List<String> listContacts = null;

		if (addressBook.getAllContacts().size() > 0) {
			List<String> sortedList = addressBook.getAllContacts().keySet().stream()
					.sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
					.collect(Collectors.toList());

			listContacts = commandLine.displayMenu("The list of contacts:", sortedList);
		} else {
			commandLine.displayMessage("The list of contacts is empty.");
		}

		return listContacts;
	}

	/**
	 * Displays the list of contacts and return the contact chosen by the user.
	 *
	 * @return
	 */
	private Contact getContactChoice() {
		Contact contact = null;

		List<String> listContacts = displayListContacts();

		if (listContacts != null) {
			String contactIdentifier = commandLine.getMenuChoice(listContacts, true, false);
			contact = addressBook.getContact(contactIdentifier);
		}

		return contact;
	}

	/**
	 * Updates a Contact instance with the data given by the user.
	 * These data are in the given map.
	 * Check if the mandatory data are given and if the data are valid.
	 * Some of these checks are not useful because they are already done in the
	 * CommandLine class.
	 *
	 * @param contact
	 * @param data
	 * @return true if the data given by the user are valid and if the mandatory
	 *         data are given.
	 */
	private boolean setContact(Contact contact, Map<ContactData, String> data) {
		Boolean dataValid = true;
		Category category = null;
		String categoryName;

		if (!contact.setName(data.get(ContactData.NAME))) {
			commandLine.displayMessage("ERROR : the name is mandatory");
			dataValid = false;
		}
		if (!contact.setSurname(data.get(ContactData.SURNAME))) {
			commandLine.displayMessage("ERROR : the surname is mandatory");
			dataValid = false;
		}
		if (!contact.setTelephoneNumber(data.get(ContactData.TELEPHONE_NUMBER))) {
			commandLine.displayMessage("ERROR : the telephone number is mandatory");
			dataValid = false;
		}
		if (!contact.setEmail(data.get(ContactData.EMAIL))) {
			commandLine.displayMessage("ERROR : a valid email is mandatory");
			dataValid = false;
		}
		String age = data.get(ContactData.AGE);
		if (!"".equals(age)) {
			if (age.matches("[0-9]*")) {
				if (!contact.setAge(Integer.parseInt(age))) {
					commandLine.displayMessage("ERROR : the age is too old");
					dataValid = false;
				}
			} else {
				commandLine.displayMessage("ERROR : the age is not valid");
				dataValid = false;
			}
		} else {
			contact.setAge(null);
		}

		String hairColor = data.get(ContactData.HAIR_COLOR);
		if (!hairColor.isEmpty()) {
			contact.setHair(new Hair(hairColor));
		} else {
			contact.setHair(null);
		}

		categoryName = data.get(ContactData.CATEGORY_TYPE);
		if (categoryName.isEmpty()) {
			contact.setCategory(null);
		} else {
			// checks if it is an “acquaintance” category
			if (CategoryAcquaintance.class.getName().indexOf(categoryName) > 0) {
				category = new CategoryAcquaintance();
				contact.setCategory(category);
			}
			// checks if it is an “family” category
			if (CategoryFamily.class.getName().indexOf(categoryName) > 0) {
				String relationship = data.get(ContactData.FAMILY_RELATIONSHIPS);
				category = new CategoryFamily();
				contact.setCategory(category);
				((CategoryFamily) category).setRelationship(relationship);
			}
			// checks if it is an “friends” category
			if (CategoryFriends.class.getName().indexOf(categoryName) > 0) {
				String friendshipYears = data.get(ContactData.FRIENDS_FRIENDSHIPYEARS);
				category = new CategoryFriends();
				contact.setCategory(category);
				if (!"".equals(friendshipYears)) {
					if (friendshipYears.matches("^[0-9]*")) {
						if (!((CategoryFriends) category).setFriendshipYears(Integer.parseInt(friendshipYears))) {
							commandLine.displayMessage("ERROR : the friendshipYears is too old");
							dataValid = false;
						}
					} else {
						commandLine.displayMessage("ERROR : the friendshipYears is not valid");
						dataValid = false;
					}
				}
			}
			category.setName(data.get(ContactData.CATEGORY_NAME));
		}

		return dataValid;
	}

	/**
	 * Updates a map with the data contained in a Contact instance.
	 *
	 * @param contact
	 * @param data
	 */
	private void getContact(Contact contact, Map<ContactData, String> data) {
		data.put(ContactData.NAME, contact.getName());
		data.put(ContactData.SURNAME, contact.getSurname());
		data.put(ContactData.TELEPHONE_NUMBER, contact.getTelephoneNumber());
		data.put(ContactData.EMAIL, contact.getEmail());
		Integer age = contact.getAge();
		data.put(ContactData.AGE, (contact.getAge() == null ? "" : age.toString()));
		Category category = contact.getCategory();
		if (category != null) {
			String categoryType = category.getClass().getSimpleName();

			data.put(ContactData.CATEGORY_TYPE, categoryType);
			data.put(ContactData.CATEGORY_NAME, category.getName());
			// checks if it is an “family” category
			if (categoryType.equals(CategoryFamily.class.getSimpleName())) {
				data.put(ContactData.FAMILY_RELATIONSHIPS, ((CategoryFamily) category).getRelationship());
			}
			// checks if it is an “friends” category
			if (categoryType.equals(CategoryFriends.class.getSimpleName())) {
				Integer friendshipYears = ((CategoryFriends) category).getFriendshipYears();
				data.put(ContactData.FRIENDS_FRIENDSHIPYEARS,
						(friendshipYears == null ? "" : friendshipYears.toString()));
			}
		} else {
			data.put(ContactData.CATEGORY_TYPE, "");
		}
	}

	/**
	 * Adds all existing colors in the list contained in the Hair class.
	 */
	private void createHairColorsList() {
		Hair hair;
		for (Contact contact : addressBook.getAllContacts().values()) {
			hair = contact.getHair();
			if (hair != null) {
				Hair.addColorToList(hair.getColorName());
			}
		}
	}

	/**
	 * The main process.
	 * Displays the main menu and executes the actions wanted by the user.
	 */
	public void mainProcess() {
		FileUtils fileUtils = new FileUtils();
		MainMenuSelection choice;
		boolean stop = false;
		Contact contact;
		Map<ContactData, String> data;

		try {
			// read the data from the file config.properties
			Properties prop = fileUtils.loadFileProperties();
			Contact.ageMax = Integer.parseInt(prop.getProperty("ageMax"));
			CategoryFriends.friendshipYearsMax = Integer.parseInt(prop.getProperty("friendshipYearsMax"));
			addressBookFilename = prop.getProperty("addressBookFilename");

			// read the address book from the file whose the folder and the name is
			// addressBookFilename
			// an empty address book is created if this file is not found.
			addressBook = fileUtils.loadAddressBook(addressBookFilename);

			// adds all existing colors in the list contained in the Hair class.
			createHairColorsList();

			do {
				// displays the main menu and returns the choice of the user.
				choice = commandLine.displayMainMenu();

				// executes the actions wanted by the user.
				switch (choice) {
					case DISPLAY_LIST_CONTACTS:
						displayListContacts();
						break;
					case ADD_CONTACT:
						contact = new Contact();
						data = commandLine.createContact();
						if (setContact(contact, data)) {
							if (!addressBook.addContact(contact)) {
								commandLine.displayMessage(
										"ERROR : a contact with the same name and surname is already in the address book");
							}
						}
						break;
					case DISPLAY_CONTACT:
						contact = getContactChoice();
						if (contact != null) {
							commandLine.displayContactDetail(contact);
						}
						break;
					case REMOVE_CONTACT:
						contact = getContactChoice();
						if (contact != null) {
							addressBook.deleteContact(contact);
						}
						break;
					case EDIT_CONTACT:
						contact = getContactChoice();
						if (contact != null) {
							addressBook.deleteContact(contact);
							data = new HashMap<ContactData, String>();
							getContact(contact, data);
							commandLine.editContact(contact, data);
							setContact(contact, data);
							if (!addressBook.addContact(contact)) {
								commandLine.displayMessage(
										"ERROR : a contact with the same name and surname is already in the address book");
							}
						}
						break;
					case SAVE_QUIT:
						// the user has to give a folder and a filename if an empty address book was
						// created
						if (addressBookFilename.isEmpty()) {
							addressBookFilename = commandLine.getFilename();

							// the folder and the filename are saved in the file config.properties
							prop.setProperty("addressBookFilename", addressBookFilename);
							fileUtils.saveFileProperties(prop);
						}
						fileUtils.saveAddressBook(addressBook, addressBookFilename);
					case CANCEL_QUIT:
						stop = true;
				}
			} while (!stop);
		} catch (java.io.IOException e) {
			commandLine.displayMessage("ERROR : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
