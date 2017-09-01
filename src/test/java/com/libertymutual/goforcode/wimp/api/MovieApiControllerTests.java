package com.libertymutual.goforcode.wimp.api;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.EmptyResultDataAccessException;

import org.junit.Test;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Movie;
import com.libertymutual.goforcode.wimp.services.ActorRepository;
import com.libertymutual.goforcode.wimp.services.AwardRepository;
import com.libertymutual.goforcode.wimp.services.MovieRepository;

public class MovieApiControllerTests {
	
	private MovieRepository movieRepo;
	private ActorRepository actorRepo;
	private MovieApiController controller;
	
	@Before
	public void setUp() {
		movieRepo = mock(MovieRepository.class);
		actorRepo = mock(ActorRepository.class);
		controller = new MovieApiController(movieRepo, actorRepo);
	}

	@Test
	public void test_that_getAll_returns_list_of_movies_from_repo() {
		//arrange
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(new Movie());
		movies.add(new Movie());
		when(movieRepo.findAll()).thenReturn(movies);
		
		//act
		List<Movie> result = controller.getAll();
		
		//assert
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0)).isSameAs(movies.get(0));
		verify(movieRepo).findAll();
	}
	
	@Test
	public void test_that_getOne_returns_one_movie_when_searching_by_movieId() throws StuffNotFoundException {
		Movie movie = new Movie();
		when(movieRepo.findOne(1L)).thenReturn(movie);
		
		//act
		Movie result = controller.getOne(1L);
		
		//assert
		assertThat(result).isSameAs(movie);
		verify(movieRepo).findOne(1L);

	}
	
	@Test
	public void test_that_StuffNotFoundException_is_thrown_when_no_movieId_found() {
		try {
			controller.getOne(2L);
			fail("The controller did not throw an StuffNotFoundException");
		} catch(StuffNotFoundException snfe) {
			
		}
	}
	
	@Test
	public void test_movie_deletion_with_movieId () {
		Movie movie = new Movie();
		when(movieRepo.findOne(3L)).thenReturn(movie);
		
		Movie result = controller.delete(3L);
		
		assertThat(result).isSameAs(movie);
		verify(movieRepo).delete(3L);
		
	}
	
	@Test
	public void test_movie_delete_EmptyResultDataAccessException_when_id_not_found() {
		
		when(movieRepo.findOne(5L)).thenThrow(new EmptyResultDataAccessException(0));
		
		Movie result = controller.delete(5L);
		
		assertThat(result).isNull();
		verify(movieRepo).findOne(5L);
		
	}
	
	@Test
	public void test_movie_creation_to_movieRepo() {
		Movie movie = new Movie();
		when(movieRepo.save(movie)).thenReturn(movie);
		
		Movie result = controller.create(movie);
		
		assertThat(result).isSameAs(movie);
		verify(movieRepo).save(movie);
	}
	
	@Test
	public void test_movie_update_in_movieRepo_when_searching_by_id() {
		Movie movie = new Movie();
		movie.setId(9L);
		when(movieRepo.save(movie)).thenReturn(movie);
		
		Movie result = controller.update(movie, 9L);
		
		assertThat(result).isSameAs(movie);
		verify(movieRepo).save(movie);
	}
	
	@Test
	public void test_that_you_can_associate_an_actor_to_movie() {
		Movie movie = new Movie();
		Actor actor = new Actor();
		when(movieRepo.findOne(10L)).thenReturn(movie);
		
		Movie result = controller.associateAnActor(actor, 10L);
		
		assertThat(result).isSameAs(movie);
		verify(movieRepo).save(movie);
	}
	
}
