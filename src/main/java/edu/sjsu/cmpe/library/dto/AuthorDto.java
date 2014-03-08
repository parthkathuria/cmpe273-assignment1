/**
 * 
 */
package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.Author;
/**
 * @author parthkathuria
 * 
 */
@JsonPropertyOrder(alphabetic = true)
public class AuthorDto extends LinksDto {
	private Author Author;

	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return Author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		Author = author;
	}
	
	public AuthorDto (Author author){
		this.setAuthor(author);
	}

}
