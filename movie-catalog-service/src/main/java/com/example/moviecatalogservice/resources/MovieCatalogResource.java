package com.example.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	private static final Logger logger = LoggerFactory.getLogger(MovieCatalogResource.class);

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {
		// get all rated movie ids
		logger.info("Getting user ratings data");
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
		logger.info("Got user ratings data");
		
		return ratings.getUserRating().stream().map(rating -> {
			// for each movie id, call movie info service and get details
			logger.info("Getting movies data for each user");
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
			logger.info("Got movies data for each user");
			
			// put them all together
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		}
		).collect(Collectors.toList());

	}
}
