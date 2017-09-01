package com.libertymutual.goforcode.wimp.api;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.verification.VerificationMode;
import org.springframework.dao.EmptyResultDataAccessException;

import com.libertymutual.goforcode.wimp.models.Actor;
import com.libertymutual.goforcode.wimp.models.Award;
import com.libertymutual.goforcode.wimp.services.ActorRepository;
import com.libertymutual.goforcode.wimp.services.AwardRepository;

import java.util.ArrayList;
import java.util.List;

public class ActorApiControllerTests {
	
	private ActorRepository actorRepo;
	private AwardRepository awardRepo;
	private ActorApiController controller;
	
	@Before
	public void setUp() {
		actorRepo = mock(ActorRepository.class);
		awardRepo = mock(AwardRepository.class);
		controller = new ActorApiController(actorRepo, awardRepo);
		
	}

	@Test
	public void test_that_getAll_returns_all_actors() {
		//arrange
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(new Actor());
		actors.add(new Actor());
		when(actorRepo.findAll()).thenReturn(actors);
		
		//act
		List<Actor> result = controller.getAll();
		
		//assert
		assertThat(result.size()).isEqualTo(2);
		assertThat(result.get(0)).isSameAs(actors.get(0));
		verify(actorRepo).findAll();
	}
	
	@Test
	public void test_that_getOne_returns_one_actor_from_repo() throws StuffNotFoundException {
		//arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(1L)).thenReturn(actor);
		
		//act
		Actor result = controller.getOne(1L);
		
		//assert
		assertThat(result).isSameAs(actor);
		verify(actorRepo).findOne(1L);
	}
	
	@Test
	public void test_that_getOne_throws_StuffNotFoundException_when_no_actor_found() {
		try {
			controller.getOne(2L);
			fail("The controller did not throw an StuffNotFoundException");
		} catch(StuffNotFoundException snfe) {
			
		}
	}

	@Test
	public void test_that_creation_of_actor_saves_to_repo() {
		//arrange
		Actor actor = new Actor();
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//act
		Actor result = controller.create(actor);
		
		//assert
		assertThat(result).isSameAs(actor);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_that_updating_an_actor_by_id_updates_actor_in_repo() {
		
		//assert
		Actor actor = new Actor();
		actor.setId(5L);
		when(actorRepo.save(actor)).thenReturn(actor);
		
		//act
		Actor result = controller.update(actor, 5L);
		
		//assert
		assertThat(result).isSameAs(actor);
		verify(actorRepo).save(actor);
	}
	
	@Test
	public void test_deletion_of_actor_from_repo() {
		
		//arrange
		Actor actor = new Actor();
		when(actorRepo.findOne(6L)).thenReturn(actor);
		
		//act
		Actor result = controller.delete(6L);
		
		//assert
		assertThat(result).isSameAs(actor);
		verify(actorRepo).delete(6L);
		
	}
	
	@Test
	public void test_delete_EmptyResultDataAccessException_when_no_actor_found( ) {
		
		when(actorRepo.findOne(7L)).thenThrow(new EmptyResultDataAccessException(0));
		
		Actor result = controller.delete(7L);
		
		assertThat(result).isNull();
		verify(actorRepo).findOne(7L);
	}
	
	@Test
	public void test_that_an_award_gets_associated_to_an_actor() {
		Actor actor = new Actor();
		Award award = new Award();
		when(actorRepo.findOne(8L)).thenReturn(actor);
		
		//act
		Actor result = controller.associateAwardToActor(8L, award);
		
		//assert
		verify(actorRepo, times(2)).findOne(8L);
		verify(awardRepo).save(award);
		assertThat(result.getId()).isSameAs(actor.getId());
		
	}

}
