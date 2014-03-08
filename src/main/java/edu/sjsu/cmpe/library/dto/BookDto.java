package edu.sjsu.cmpe.library.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import edu.sjsu.cmpe.library.domain.BookInfo;

@JsonPropertyOrder(alphabetic = true)
public class BookDto extends LinksDto {
    private BookInfo book;

    /**
     * @param book
     */
    public BookDto(BookInfo book) {
	super();
	this.book = book;
    }

    /**
     * @return the book
     */
    public BookInfo getBook() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(BookInfo book) {
	this.book = book;
    }
}
