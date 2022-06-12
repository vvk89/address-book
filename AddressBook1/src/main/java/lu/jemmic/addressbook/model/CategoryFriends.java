package lu.jemmic.addressbook.model;

/**
 * Contains data about the “friends” category.
 */
public class CategoryFriends extends Category {

	private static final long serialVersionUID = -4801260941605646495L;

	public static int friendshipYearsMax; // the maximum number of friendship’s years

	private Integer friendshipYears;

	/**
	 * Used to change the number of friendship’s years of the “friends” category.
	 * This method returns false is this number is not between 0 and the maximum
	 * number of friendship’s years.
	 *
	 * @param friendshipYears
	 * @return true is the number of friendship’s years is valid
	 */
	public boolean setFriendshipYears(Integer friendshipYears) {
		boolean ok = (friendshipYears >= 0 && friendshipYears <= friendshipYearsMax);
		if (ok) {
			this.friendshipYears = friendshipYears;
		}
		return ok;
	}

	/**
	 * @return the number of friendship’s years of the “friends” category.
	 */
	public Integer getFriendshipYears() {
		return friendshipYears;
	}

}
