package edu.sjsu.cmpe.library.repository;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;

/**
 * Book repository interface.
 * 
 * What is repository pattern?
 * 
 * @see http://martinfowler.com/eaaCatalog/repository.html
 */
public interface BookRepositoryInterface {
    /**
     * Save a new book in the repository
     * 
     * @param newBook
     *            a book instance to be create in the repository
     * @return a newly created book instance with auto-generated ISBN
     */
    Book saveBook(Book newBook);

    /**
     * Retrieve an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * @return a book instance
     */
    Book getBookByISBN(Long isbn);

    // TODO: add other operations here!
    
    /**
     * Delete an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * 
     */
    void deleteBook(Long isbn);
    
    /**
     * Update status of an existing book by ISBN
     * 
     * @param isbn
     *            a valid ISBN
     * @param status
     *            a valid status
     */
    void updateBook(Long isbn, String status);
    
    /**
     * Retrieve review an existing book by Review ID
     * 
     * @param isbn
     *            a valid ISBN
     * @param reviewID
     *            a valid Review ID
     * @return a Review instance
     */
    Review getReviewByID(Long isbn, int reviewID);
}
