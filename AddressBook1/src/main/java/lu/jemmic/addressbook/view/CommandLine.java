package lu.jemmic.addressbook.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lu.jemmic.addressbook.controller.AddressBookController;
import lu.jemmic.addressbook.controller.AddressBookController.ContactData;
import lu.jemmic.addressbook.model.Category;
import lu.jemmic.addressbook.model.CategoryFamily;
import lu.jemmic.addressbook.model.CategoryFriends;
import lu.jemmic.addressbook.model.Contact;
import lu.jemmic.addressbook.model.Hair;

/**
 * Contains all methods used for the user interface.
 */
public class CommandLine {
	private String lineSep = System.getProperty("line.separator"); // line separator
	private Scanner keyboard; // used to get data from the keyboard
	private final String otherLabel = "other"; // choice used in a menu

	/**
	 * Allows to get an integer value given from the keyboard.
	 * This value must be between min and max otherwise the method displays a
	 * message.
	 * In this case, the user have to give another value.
	 *
	 * @param min
	 * @param max
	 * @return the integer value given from the keyboard.
	 */
	private int getInt(int min, int max) {
		if (keyboard == null) {
			keyboard = new Scanner(System.in);
		}
		boolean found = false;
		int choice = 0;

		do {
			try {
				choice = keyboard.nextInt();
				if (choice <= max) {
					found = true;
				} else {
					System.out.print("You have to choose an number between " + min + " and " + max + ".");
				}
			} catch (InputMismatchException e) {
				keyboard.next();
				System.out.print("You have to choose an number between " + min + " and " + max + ".");
			}
		} while (!found);

		return choice;
	}

	/**
	 * Allows to get a string value given from the keyboard.
	 *
	 * @return the string value given from the keyboard.
	 */
	private String getString() {
		if (keyboard == null) {
			keyboard = new Scanner(System.in);
		}
		String input = keyboard.next();

		return input;
	}

	/**
	 * Displays the main menu and returns the choice of the user.
	 *
	 * @return the choice of the user.
	 */
	public AddressBookController.MainMenuSelection displayMainMenu() {
		System.out.print(lineSep + "MAIN MENU" + lineSep
				+ " 1) Display the list of contacts" + lineSep
				+ " 2) Add a contact" + lineSep
				+ " 3) Display a contact" + lineSep
				+ " 4) Remove a contact" + lineSep
				+ " 5) Edit a contact" + lineSep
				+ " 6) Save and quit the program" + lineSep
				+ " 7) Quit the program without saving" + lineSep
				+ "Please make a selection.");

		int min = 1;
		int max = AddressBookController.MainMenuSelection.values().length;
		// Get the choice of the user
		AddressBookController.MainMenuSelection choice = AddressBookController.MainMenuSelection
				.values()[getInt(min, max) - 1];

		System.out.println();

		return choice;
	}

	/**
	 * Displays a menu whose possible choices are in the list given in parameter.
	 * The name of the menu is also given in parameter.
	 *
	 * @param menu
	 * @param list
	 * @return the list given in parameter where the number of each choice is added.
	 */
	public List<String> displayMenu(String menu, List<String> list) {
		return displayMenu(menu, list, false);
	}

	/**
	 * Displays a menu whose possible choices are in the list given in parameter.
	 * The name of the menu is also given in parameter.
	 * The other parameter is true if a choice "other" must be added at the end of
	 * the menu.
	 *
	 * @param menu
	 * @param list
	 * @param other
	 * @return the list given in parameter where the number of each choice and
	 *         eventually the choice "other" are added.
	 */
	private List<String> displayMenu(String menu, List<String> list, boolean other) {
		System.out.println(menu);

		List<String> listId = IntStream.range(0, list.size())
				.mapToObj(i -> (i + 1) + ") " + list.get(i))
				.collect(Collectors.toList());

		listId.stream().forEach(System.out::println);

		// Add the "other" choice if the parameter other is true
		if (other) {
			String otherChoice = (listId.size() + 1) + ") " + otherLabel;
			System.out.println(otherChoice);
			listId.add(otherChoice);
		}

		return listId;
	}

	/**
	 * Returns the user's choice for the displayed menu.
	 * The parameter contains the list of choices.
	 *
	 * @param listId
	 * @return a string containing the user's choice.
	 */
	public String getMenuChoice(List<String> listId) {
		return getMenuChoice(listId, false, false);
	}

	/**
	 * Returns the user's choice for the displayed menu.
	 * The parameter contains the list of choices.
	 * The user may choose the value 0 if the parameter mandatory is true.
	 * It is useful when the choice is not mandatory.
	 * The other parameter is true if a choice "other" has been added at the end of
	 * the menu.
	 *
	 * @param listId
	 * @param mandatory
	 * @param other
	 * @return a string containing the user's choice.
	 */
	public String getMenuChoice(List<String> listId, boolean mandatory, boolean other) {
		int choice;
		String identifier = "";

		if (mandatory) {
			System.out.print("Please make a selection");
			choice = getInt(1, listId.size());
		} else {
			System.out.print("Please make a selection or choose 0 if this field is empty");
			choice = getInt(0, listId.size());
		}

		if (choice != 0) {
			identifier = listId.stream()
					.filter(s -> s.startsWith(Integer.valueOf(choice).toString()))
					.map(s -> s.substring(s.indexOf(") ") + 2))
					.reduce("", String::concat);
			if (other && identifier.isEmpty()) {
				identifier = otherLabel;
			}
		}

		return identifier;
	}

	/**
	 * Allows to get the folder and the filename to save the address book.
	 *
	 * @return the folder and the filename.
	 */
	public String getFilename() {
		System.out.println("Please give the folder and the filename.");

		return getString();
	}

	/**
	 * Displays all the data about the contact given as parameter.
	 *
	 * @param contact
	 */
	public void displayContactDetail(Contact contact) {
		displayContactDetail(contact, false);
	}

	/**
	 * Displays all the data about the contact given as parameter.
	 * If the parameter menu is true, each data is displayed with a number.
	 *
	 * @param contact
	 * @param menu
	 * @return a list containing each data displayed.
	 */
	private List<ContactData> displayContactDetail(Contact contact, boolean menu) {
		Hair hair = contact.getHair();
		Category category = contact.getCategory();
		List<ContactData> list = new ArrayList<ContactData>();
		int i = 1;

		System.out.println("\t" + (menu ? i++ + ") " : "") + "Name : " + contact.getName());
		list.add(ContactData.NAME);
		System.out.println("\t" + (menu ? i++ + ") " : "") + "Surname : " + contact.getSurname());
		list.add(ContactData.SURNAME);
		System.out.println("\t" + (menu ? i++ + ") " : "") + "Telephone number : " + contact.getTelephoneNumber());
		list.add(ContactData.TELEPHONE_NUMBER);
		System.out.println("\t" + (menu ? i++ + ") " : "") + "Email : " + contact.getEmail());
		list.add(ContactData.EMAIL);
		Integer age = contact.getAge();
		System.out.println("\t" + (menu ? i++ + ") " : "") + "Age : " + (age != null ? age : ""));
		list.add(ContactData.AGE);
		if (hair != null) {
			System.out.println("\t" + (menu ? i++ + ") " : "") + "Hair color : " + hair.getColorName());
			list.add(ContactData.HAIR_COLOR);
		} else {
			System.out.println("\t" + (menu ? i++ + ") " : "") + "Hair color : ");
			list.add(ContactData.HAIR_COLOR);
		}
		if (category != null) {
			String categoryType = category.getClass().getSimpleName();
			System.out.println("\t" + (menu ? i++ + ") " : "") + "Type of category : " + categoryType.substring(8));
			list.add(ContactData.CATEGORY_TYPE);
			System.out.println("\t" + (menu ? i++ + ") " : "") + "Name of category : " + category.getName());
			list.add(ContactData.CATEGORY_NAME);
			if (category instanceof CategoryFamily) {
				System.out.println("\t" + (menu ? i++ + ") " : "") + "Relationship : "
						+ ((CategoryFamily) category).getRelationship());
				list.add(ContactData.FAMILY_RELATIONSHIPS);
			} else {
				if (category instanceof CategoryFriends) {
					Integer friendshipYears = ((CategoryFriends) category).getFriendshipYears();
					System.out.println("\t" + (menu ? i++ + ") " : "") + "Friendship years : "
							+ (friendshipYears != null ? friendshipYears : ""));
					list.add(ContactData.FRIENDS_FRIENDSHIPYEARS);
				}
			}
		} else {
			System.out.println("\t" + (menu ? i++ + ") " : "") + "Type of category : ");
			list.add(ContactData.CATEGORY_TYPE);
		}

		if (menu) {
			// add a choice to quit the menu
			System.out.println("\t" + i++ + ") Quit");
			list.add(ContactData.OTHER);
		}

		return list;
	}

	/**
	 * Displays a label and get the user value for the field corresponding to this
	 * label.
	 * Displays a message to know if the user wants to give a value for this field
	 * when it is not mandatory.
	 * The type of this value is a string.
	 *
	 * @param message
	 * @param mandatory
	 * @return the value of the field or an empty string if the user doesn't give a
	 *         value for this field.
	 */
	private String getStringField(String message, boolean mandatory) {
		boolean empty = false;
		String value = "";

		if (!mandatory) {
			System.out.print("Is the field " + message + " empty (y/n):");
			empty = getString().toUpperCase().equals("Y");
		}

		if (!empty) {
			System.out.print(message + ":");
			value = getString();
		}

		return value;
	}

	/**
	 * Displays a label and get the user value for the field corresponding to this
	 * label.
	 * Displays a message to know if the user wants to give a value for this field
	 * when it is not mandatory.
	 * The type of this value is a string containing an integer.
	 * This value must be between min and max.
	 *
	 * @param message
	 * @param min
	 * @param max
	 * @param mandatory
	 * @return the value of the field or an empty string if the user doesn't give a
	 *         value for this field.
	 */
	private String getIntField(String message, int min, int max, boolean mandatory) {
		boolean empty = false;
		String value = "";

		if (!mandatory) {
			System.out.print("Is the field " + message + " empty (y/n):");
			empty = getString().toUpperCase().equals("Y");
		}

		if (!empty) {
			System.out.print(message + ":");
			Integer i = Integer.valueOf(getInt(min, max));
			value = (i == null ? "" : i.toString());
		}

		return value;
	}

	/**
	 * Allows to get the choice of the user about hair color.
	 * The choice is put in given map.
	 *
	 * @param data
	 */
	private void getHairColor(Map<ContactData, String> data) {
		String hairColor = "";

		if (Hair.colors.size() > 0) {
			hairColor = getMenuChoice(displayMenu("Existing hair colors:", Hair.colors, true));
		}

		if (Hair.colors.size() == 0 || hairColor.endsWith(otherLabel)) {
			hairColor = getStringField("Hair color", Hair.colors.size() > 0);
		}

		data.put(AddressBookController.ContactData.HAIR_COLOR, hairColor);
	}

	/**
	 * Displays a form to create a new contact.
	 *
	 * @return a map containing the data given by the user.
	 */
	public Map<ContactData, String> createContact() {
		Map<ContactData, String> data = new HashMap<ContactData, String>();

		System.out.println("Please fill this form");
		data.put(AddressBookController.ContactData.NAME, getStringField("Name", true));
		data.put(AddressBookController.ContactData.SURNAME, getStringField("Surname", true));
		data.put(AddressBookController.ContactData.TELEPHONE_NUMBER, getStringField("Telephone number", true));
		data.put(AddressBookController.ContactData.EMAIL, getStringField("Email", true));
		data.put(AddressBookController.ContactData.AGE, getIntField("Age", 0, Contact.ageMax, false));
		getHairColor(data);
		String categoryType = getMenuChoice(displayMenu("Type of category:", Category.categories), false, false);
		data.put(AddressBookController.ContactData.CATEGORY_TYPE, categoryType);
		if (!categoryType.isEmpty()) {
			data.put(AddressBookController.ContactData.CATEGORY_NAME, getStringField("Name of category", false));
			if (CategoryFamily.class.getSimpleName().indexOf(categoryType) >= 0) {
				data.put(AddressBookController.ContactData.FAMILY_RELATIONSHIPS,
						getMenuChoice(displayMenu("Relationship:", CategoryFamily.relationships), false, false));
			}
			if (CategoryFriends.class.getSimpleName().indexOf(categoryType) >= 0) {
				data.put(AddressBookController.ContactData.FRIENDS_FRIENDSHIPYEARS,
						getIntField("Friendship years", 0, CategoryFriends.friendshipYearsMax, false));
			}
		}

		return data;
	}

	/**
	 * Displays a form to edit a contact.
	 * The contact parameter contains the current values of the contact.
	 * The data parameter is used to contain the data given by the user.
	 *
	 * @param contact
	 * @param data
	 */
	public void editContact(Contact contact, Map<ContactData, String> data) {
		boolean stop = false;
		ContactData choice;
		Category category = contact.getCategory();
		String categoryType = (category == null ? "" : category.getClass().getSimpleName());
		List<ContactData> list = displayContactDetail(contact, true);

		do {
			System.out.print("Please select the field to edit:");
			choice = list.get(getInt(1, list.size()) - 1);
			switch (choice) {
				case NAME:
					data.put(AddressBookController.ContactData.NAME, getStringField("Name", true));
					break;
				case SURNAME:
					data.put(AddressBookController.ContactData.SURNAME, getStringField("Surname", true));
					break;
				case TELEPHONE_NUMBER:
					data.put(AddressBookController.ContactData.TELEPHONE_NUMBER,
							getStringField("Telephone number", true));
					break;
				case EMAIL:
					data.put(AddressBookController.ContactData.EMAIL, getStringField("Email", true));
					break;
				case AGE:
					data.put(AddressBookController.ContactData.AGE, getIntField("Age", 0, Contact.ageMax, false));
					break;
				case HAIR_COLOR:
					getHairColor(data);
					break;
				case CATEGORY_TYPE:
					categoryType = getMenuChoice(displayMenu("Type of category:", Category.categories));
					data.put(AddressBookController.ContactData.CATEGORY_TYPE, categoryType);
					if (categoryType.isEmpty()) {
						break;
					}
					data.put(AddressBookController.ContactData.CATEGORY_NAME,
							getStringField("Name of category", false));
				case FAMILY_RELATIONSHIPS:
					if (CategoryFamily.class.getSimpleName().indexOf(categoryType) >= 0) {
						data.put(AddressBookController.ContactData.FAMILY_RELATIONSHIPS, getMenuChoice(
								displayMenu("Relationship:", CategoryFamily.relationships), false, false));
					}
				case FRIENDS_FRIENDSHIPYEARS:
					if (CategoryFriends.class.getSimpleName().indexOf(categoryType) >= 0) {
						data.put(AddressBookController.ContactData.FRIENDS_FRIENDSHIPYEARS,
								getIntField("Friendship years", 0, CategoryFriends.friendshipYearsMax, false));
					}
					break;
				case CATEGORY_NAME:
					data.put(AddressBookController.ContactData.CATEGORY_NAME,
							getStringField("Name of category", false));
					break;
				case OTHER:
					stop = true;
			}
		} while (!stop);
	}

	/**
	 * Displays the message given as parameter.
	 *
	 * @param message
	 */
	public void displayMessage(String message) {
		System.out.println(message);
	}

}
