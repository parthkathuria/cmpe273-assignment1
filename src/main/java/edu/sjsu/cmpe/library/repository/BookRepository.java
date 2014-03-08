package edu.sjsu.cmpe.library.repository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;

public class BookRepository implements BookRepositoryInterface {
    /** In-memory map to store books. (Key, Value) -> (ISBN, Book) */
    private final ConcurrentHashMap<Long, Book> bookInMemoryMap;

    /** Never access this key directly; instead use generateISBNKey() */
    private long isbnKey;
    private long authorId;
    
    public BookRepository(ConcurrentHashMap<Long, Book> bookMap) {
	checkNotNull(bookMap, "bookMap must not be null for BookRepository");
	bookInMemoryMap = bookMap;
	isbnKey = 0;
	authorId = 0;
    }

    /**
     * This should be called if and only if you are adding new books to the
     * repository.
     * 
     * @return a new incremental ISBN number
     */
    private final Long generateISBNKey() {
	// increment existing isbnKey and return the new value
	return Long.valueOf(++isbnKey);
    }
    

    /**
     * This will auto-generate unique ISBN for new books.
     */
    @Override
    public Book saveBook(Book newBook) {
	checkNotNull(newBook, "newBook instance must not be null");
	// Generate new ISBN
	Long isbn = generateISBNKey();
	newBook.setIsbn(isbn);
	// TODO: create and associate other fields such as author
	
	// Generate Author ID
	ArrayList<Author> authors = newBook.getAuthors();
	for(int i = 0; i < authors.size() ; i++){
		authors.get(i).setId(++authorId);
	}
	// Finally, save the new book into the map
	bookInMemoryMap.putIfAbsent(isbn, newBook);

	return newBook;
    }

    /**
     * @see edu.sjsu.cmpe.library.repository.BookRepositoryInterface#getBookByISBN(java.lang.Long)
     */
    @Override
    public Book getBookByISBN(Long isbn) {
	checkArgument(isbn > 0,
		"ISBN was %s but expected greater than zero value", isbn);
	return bookInMemoryMap.get(isbn);
	
    }
    
    /*This will delete book from the memory*/
    @Override 
    public void deleteBook(Long isbn){
    	checkArgument(isbn > 0,
    			"ISBN was %s but expected greater than zero value", isbn);
    	bookInMemoryMap.remove(isbn);
    }
    
    // This will update status of the Book
    @Override
    public void updateBook(Long isbn, String status){
    	checkArgument(isbn > 0,
    			"ISBN was %s but expected greater than zero value", isbn);
    	checkNotNull(status, "Status must not be null");
    	Book book = bookInMemoryMap.get(isbn);
    	checkNotNull(book, "Book instance must not be null");
    	book.setStatus(status);
    	bookInMemoryMap.put(isbn, book);
    }
    
    // This will return Review by ID
    @Override
    public Review getReviewByID(Long isbn, int reviewID) {
    	checkArgument(isbn > 0,
    			"ISBN was %s but expected greater than zero value", isbn);
    	checkNotNull(reviewID, "ID instance must not be null");
    	Book book = bookInMemoryMap.get(isbn);
    	checkNotNull(book, "Book instance must not be null");
    	ArrayList<Review> reviews = book.getReviews();
    	
    	for(int i=0; i<reviews.size();i++){
    		if(reviews.get(i).getId()==reviewID){
    			return reviews.get(i);
    			}
    	}
    	return null;
    	
    }

}
