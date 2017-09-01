package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class ActorWithMoviesTest {
	
	private ActorWithMovies actorWithMovies;

	@Test
	public void test_that_actor_with_movies() {
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		
		ActorWithMovies test = new ActorWithMovies();
		test.setMovies(movies);
		
		assertThat(test.noReallyMovies()).isSameAs(movies);
		
	}

}
