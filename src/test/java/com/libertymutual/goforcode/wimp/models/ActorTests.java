package com.libertymutual.goforcode.wimp.models;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.springframework.dao.EmptyResultDataAccessException;

public class ActorTests {
	
	Actor actor;
	
	@Before
	public void setUp() {
		actor = new Actor();
	}
	
	@Test
	public void test_all_getters_and_setters() {
		new BeanTester().testBean(Actor.class);
	}
	
	@Test
	public void test_actor_constructor() {
		Actor actor = new Actor("Chris", "Hemsworth");
		
		assertThat(actor.getFirstName()).isEqualTo("Chris");
		assertThat(actor.getLastName()).isEqualTo("Hemsworth");
		
	}

	@Test
	public void test_that_you_can_get_actor_id() {
		actor.setId(1L);
		
		Long result = actor.getId();
		
		assertThat(result).isEqualTo(1L);
	}
	
	@Test
	public void test_that_you_can_get_actor_firstName() {
		actor.setFirstName("Chris");
		
		String result = actor.getFirstName();
		
		assertThat(result).isEqualTo("Chris");
	}
	
	@Test
	public void test_that_you_can_get_actor_lasttName() {
		actor.setLastName("Pratt");
		
		String result = actor.getLastName();
		
		assertThat(result).isEqualTo("Pratt");
	}

	@Test
	public void test_getting_active_since_year() {
		actor.setActiveSinceYear(2000L);
		
		Long result = actor.getActiveSinceYear();
		
		assertThat(result).isEqualTo(2000L);
	}
	
	@Test
	public void test_getting_movies_of_actors() {
		List<Movie> movies = new ArrayList<Movie>();
		actor.setMovies(movies);
		
		List<Movie> result = actor.getMovies();
		
		assertThat(result).isSameAs(movies);
		
	}
	
	@Test
	public void test_getting_awards_from_actors() {
		List<Award> awards = new ArrayList<Award>();
		actor.setAwards(awards);
		
		List<Award> result = actor.getAwards();
		
		assertThat(result).isSameAs(awards);
	}
	
	@Test
	public void test_creation_of_award_arrayList_when_null() {
		//arrange
		Award award = new Award();
		
		//act
		actor.addAward(award);
		
		//assert
		assertThat(actor.getAwards()).contains(award);
	}
	
	@Test
	public void test_birthDate_of_actor() throws ParseException {
		//arrange
		String expectedPattern = "MM/dd/yyyy";
		SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
		String birthdate = "08/01/2002";
		Date date = formatter.parse(birthdate);
		
		//act
		actor.setBirthDate(date);
		
		//assert
		assertThat(actor.getBirthDate()).isEqualTo(date);
	}
	
}
