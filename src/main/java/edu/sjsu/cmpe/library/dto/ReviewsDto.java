/**
 * 
 */
package edu.sjsu.cmpe.library.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Review;

/**
 * @author parthkathuria
 *
 */
@JsonPropertyOrder({"reviews","links"})
public class ReviewsDto extends LinksDto {

	ArrayList<Review> reviews = new ArrayList<Review>();
	
	public ReviewsDto (ArrayList<Review> reviews){
		this.reviews = reviews;
	}
	
	public void addReviews (ArrayList<Review> reviews){
		reviews.addAll(reviews);
	}
	
	public ArrayList<Review> getReviews(){
		return reviews;
	}
	
	public void setReviews (ArrayList<Review> reviews){
		this.reviews = reviews;
	}
}
