package edu.sjsu.cmpe.library.api.resources;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;
import edu.sjsu.cmpe.library.repository.BookRepositoryInterface;

/**
 * @author parthkathuria
 * 
 */
@Path("/v1/books/{isbn}/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

	private final BookRepositoryInterface bookRepository;

	public ReviewResource(BookRepositoryInterface bookRepository) {
		this.bookRepository = bookRepository;
	}

	// Create Review API
	@POST
	@Timed(name = "create-review")
	public Response createReviewByISBN(@PathParam("isbn") LongParam isbn,
			@Valid Review request) {
		if (request.checkRating() == false || request.checkComment() == false) {
			if (request.checkRating() == false
					&& request.checkComment() == false)
				return Response.status(400).entity("Fields cannot be Null")
						.build();
			if (request.checkRating() == false) {
				if (request.getRating() < 1 || request.getRating() > 5) {
					return Response.status(400).entity("Rating not in range. It should be between 1 and 5.")
							.build();
				} else {
					return Response.status(400).entity("Rating cannot be Null")
							.build();
				}
			} else if (request.checkComment() == false) {
				return Response.status(400).entity("Comment cannot be Null")
						.build();
			}
		}
		Book book = bookRepository.getBookByISBN(isbn.get());
		ArrayList<Review> tempreview = book.getReviews();
		tempreview.add(request);

		String location = "/books/" + book.getIsbn() + "/reviews/"
				+ request.getId();
		LinksDto bookResponse = new LinksDto();

		bookResponse.addLink(new LinkDto("view-review", location, "GET"));

		return Response.status(201).entity(bookResponse).build();

	}

	// View Review API
	@GET
	@Path("/{id}/")
	@Timed(name = "view-review")
	public ReviewDto getReviewByIsbn(@PathParam("isbn") LongParam isbn,
			@PathParam("id") int reviewID) {
		Review review = bookRepository.getReviewByID(isbn.get(), reviewID);
		Book book = bookRepository.getBookByISBN(isbn.get());
		ReviewDto bookResponse = new ReviewDto(review);
		bookResponse.addLink(new LinkDto("view-book", "/books/"
				+ book.getIsbn() + "/review/" + review.getId(), "GET"));

		return bookResponse;
		// return Response.status(201).entity(bookResponse).build();
	}

	// View All Reviews
	@GET
	@Timed(name = "view-all-reviews")
	public Response viewAllReviews(@PathParam("isbn") LongParam isbn) {
		Book book = bookRepository.getBookByISBN(isbn.get());
		ArrayList<Review> reviews = book.getReviews();

		ReviewsDto bookResponse = new ReviewsDto(reviews);

		return Response.status(201).entity(bookResponse).build();
	}

}
