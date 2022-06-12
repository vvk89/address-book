package lu.jemmic.addressbook.model;

import java.util.Arrays;
import java.util.List;

/**
 * Contains data about the “family” category.
 */
public class CategoryFamily extends Category {

	private static final long serialVersionUID = 7801530421677277845L;

	/**
	 * This list will contain the list of possible family’s relationships.
	 */
	public final static List<String> relationships = Arrays.asList("parent", "granparent", "son/daughter",
			"aunt/uncle");

	private String relationship = "";

	/**
	 * Used to change the family’s relationship.
	 *
	 * @param relationship
	 */
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	/**
	 * @return the family’s relationship.
	 */
	public String getRelationship() {
		return relationship;
	}

}
