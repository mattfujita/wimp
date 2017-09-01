package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.junit.Test;

public class MovieTests {
	
	List<Movie> movies;
	Movie movie;
	
	@Before
	public void setUp() {
		movie = new Movie();
		movies = new ArrayList<Movie>();
	}
	
	@Test
	public void test_movie_constructor() {
		Movie movie = new Movie("Moana", "Disney");
		
		assertThat(movie.getTitle()).isEqualTo("Moana");
		assertThat(movie.getDistributor()).isEqualTo("Disney");
	}
	
	@Test
	public void test_all_getters_and_setters() {
		new BeanTester().testBean(Movie.class);
	}

	@Test
	public void test_getting_movieId() {
		movie.setId(1L);
		
		Long result = movie.getId();
		
		assertThat(result).isEqualTo(1L);
	}
	
	@Test
	public void test_getting_movie_title() {
		movie.setTitle("Fantastic Beasts");
		
		String result = movie.getTitle();
		
		assertThat(result).isEqualTo("Fantastic Beasts");
		
	}
	
	@Test
	public void test_getting_movie_budget() {
		movie.setBudget(1000000L);
		
		Long result = movie.getBudget();
		
		assertThat(result).isEqualTo(1000000L);
	}
	
	@Test
	public void test_getting_movie_distributor() {
		movie.setDistributor("Disney");
		
		String result = movie.getDistributor();
		
		assertThat(result).isEqualTo("Disney");
		
	}
	
	@Test
	public void test_getting_list_of_actors_in_movie() {
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		movie.setActors(actors);
		
		List<Actor> result = movie.getActors();
		
		assertThat(result).isEqualTo(actors);
		assertThat(result).size().isEqualTo(1);
	}
	
//	@Test
//	public void test_creation_of_arrayList_when_null() {
//		List<Actor> actors = null;
//		
//		movie.addActor(new Actor());
//		
//		assertThat(actors.size()).isEqualTo(1);
//	}
	
	@Test
	public void test_getting_release_date_of_movie() throws ParseException {
		//arrange
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		String releaseDate = "08/01/2002";
		Date date = formatter.parse(releaseDate);
		
		//act
		movie.setReleaseDate(date);
		
		//assert
		assertThat(movie.getReleaseDate()).isEqualTo(date);
		
	}
	
	@Test
	public void test_creation_of_actor_arrayList_when_null() {
		//arrange
		Actor actor = new Actor();
		
		//act
		movie.addActor(actor);
		
		//assert
		assertThat(movie.getActors()).contains(actor);
	}

}
