package com.example.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingsdataservice.model.Ratings;
import com.example.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	private static final Logger logger = LoggerFactory.getLogger(RatingsDataResource.class);

	@RequestMapping("/{movieId}")
	public Ratings getRating(@PathVariable("movieId") String movieId) {
		logger.info("Getting ratings data");
		return new Ratings(movieId, 4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId) {
		logger.info("Setting ratings for userId");
		List<Ratings> ratings =  Arrays.asList(
				new Ratings("1234", 4),
				new Ratings("5678", 3)
		);
		
		UserRating userRating = new UserRating();
		userRating.setUserRating(ratings);
		logger.info("Set ratings for userId");
		return userRating;
	}
}
