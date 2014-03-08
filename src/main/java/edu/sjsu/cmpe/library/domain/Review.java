/**
 * 
 */
package edu.sjsu.cmpe.library.domain;


/**
 * @author parthkathuria
 * 
 */
public class Review {

	private long id;
	private int rating;
	private String comment;
	private static int reviewID;

	public Review() {
		this.id = ++reviewID;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the rating
	 */
	public int getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the rating to set
	 */
	public void setRating(int rating) {
		this.rating = rating;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	// Check rating
	public boolean checkRating() {
		if (rating < 1 || rating > 5)
			return false;
		else
			return true;
	}

	// Check comment
	public boolean checkComment() {
		if (comment == "" || comment == null)
			return false;
		else
			return true;
	}

}
