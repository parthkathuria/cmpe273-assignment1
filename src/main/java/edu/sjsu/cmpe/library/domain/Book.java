package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Book {
	
    private long isbn;
    @NotEmpty
    @NotNull
    @JsonProperty ("title")
    private String title;
    @NotEmpty
    @NotNull
    @JsonProperty ("publication-date")
    private String publicationDate;
    @JsonProperty ("language")
    private String language;
    @JsonProperty ("num-pages")
    private int noOfPages;
    @JsonProperty ("status")
    private String status;
    @NotEmpty
    @NotNull
    @JsonProperty ("authors")
    ArrayList<Author> authors;
    ArrayList<Review> reviews = new ArrayList<Review>();
	
    /**
     * @return the isbn
     */
    public long getIsbn() {
	return isbn;
    }

    /**
     * @param isbn
     *            the isbn to set
     */
    public void setIsbn(long isbn) {
	this.isbn = isbn;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

	/**
	 * @return the publicationDate
	 */
	public String getPublicationDate() {
		return publicationDate;
	}

	/**
	 * @param publicationDate the publicationDate to set
	 */
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language.toLowerCase();
	}

	/**
	 * @return the noOfPages
	 */
	public int getNoOfPages() {
		return noOfPages;
	}

	/**
	 * @param noOfPages the noOfPages to set
	 */
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		if(status.isEmpty()){
			this.status = "available";
		}
		else
		{
		this.status = status.toLowerCase();
		}
	}
	
	// Check Status
	public boolean checkStatus(String status){
		String bookStatus=status.toLowerCase();
    	if (bookStatus.equals("lost") || bookStatus.equals("checked-out")|| bookStatus.equals("in-queue") || bookStatus.equals("available"))
    		return true;
    	else if (bookStatus.isEmpty()) 
    		return true;
    	else
    		return false;
	}
	
	// Get the authors of the book
    public ArrayList<Author> getAuthors(){
    	return authors;
    }
    
    // Set the authors of the book
    public void setAuthors(ArrayList<Author> authors){
    	this.authors = authors;
     		
     }
    
    // Get the reviews for a book
    public ArrayList<Review> getReviews(){
    	return reviews;
    }
    
    // Set the reviews for a book
    public void setReviews (ArrayList<Review> review){
    	this.reviews = review;
    }
    
  

	
}
