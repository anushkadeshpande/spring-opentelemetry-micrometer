package com.example.moviecatalogservice.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moviecatalogservice.models.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {


	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId) {
		return Collections.singletonList(
				
				new CatalogItem("abc", "movie abc", 4)
		);
	}
}
