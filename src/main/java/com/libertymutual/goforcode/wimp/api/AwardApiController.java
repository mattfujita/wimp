package com.libertymutual.goforcode.wimp.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libertymutual.goforcode.wimp.services.ActorRepository;
import com.libertymutual.goforcode.wimp.services.AwardRepository;

@RestController
@RequestMapping("/api/awards")
public class AwardApiController {
	
	AwardRepository awardRepo;
	ActorRepository actorRepo;
	
	public AwardApiController(AwardRepository awardRepo, ActorRepository actorRepo) {
		this.awardRepo = awardRepo;
		this.actorRepo = actorRepo;
	}
	
	//@PostMapping("/api/actors/{actorId}/awards")
	

}
