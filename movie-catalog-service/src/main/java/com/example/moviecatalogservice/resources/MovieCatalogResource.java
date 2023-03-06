package com.example.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movie;
import com.example.moviecatalogservice.models.Ratings;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {
		// get all rated movie ids
		List<Ratings> ratings = Arrays.asList(
				new Ratings("1234", 4),
				new Ratings("5678", 3)
		);
		
		// for each movie id, call movie info service and get details
		return ratings.stream().map(rating -> {
//			new CatalogItem("abc", "movie abc", 4)
			Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), "Desc", rating.getRating());
		}
		).collect(Collectors.toList());
		
		
		// put them all together
		
		
//		return Collections.singletonList(
//				
//				new CatalogItem("abc", "movie abc", 4)
//		);
	}
}
