package lu.jemmic.addressbook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains data about the hairs of a contact.
 */
public class Hair implements Serializable {

	private static final long serialVersionUID = 6643246377357446262L;

	/**
	 * This list will contain the list of the existing hair colors.
	 */
	public static List<String> colors = new ArrayList<String>();

	private String color;

	/**
	 * The construtor has the hair color as parameter.
	 *
	 * @param color
	 */
	public Hair(String color) {
		super();
		Hair.addColorToList(color);
		this.color = color;
	}

	/**
	 * @return the color hair.
	 */
	public String getColorName() {
		return color;
	}

	/**
	 * Used to add a new color in the list of the existing hair colors.
	 *
	 * @param color
	 */
	public static void addColorToList(String color) {
		if (colors.stream().filter(p -> p.equals(color)).count() == 0) {
			colors.add(color);
		}
	}

}
