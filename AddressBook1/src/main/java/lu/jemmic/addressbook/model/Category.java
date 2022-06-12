package lu.jemmic.addressbook.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Contains data about all types of category.
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 2775969712526182931L;

	/**
	 * This list will contain the list of possible categories.
	 */
	public final static List<String> categories = Arrays.asList("Acquaintance", "Family", "Friends");

	private String name = "";

	/**
	 * Used to change the name of the category.
	 *
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name of the category.
	 */
	public String getName() {
		return name;
	}

}
