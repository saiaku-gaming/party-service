package com.valhallagame.partyserviceserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallagame.partyserviceserver.model.Party;
import com.valhallagame.partyserviceserver.repository.PartyRepository;

@Service
public class PartyService {

	@Autowired
	private PartyRepository partyRepository;

	public Party saveParty(Party party) {
		return partyRepository.save(party);
	}

	public void deleteParty(Party party) {
		partyRepository.delete(party);
	}

	public Optional<Party> getPartyFromLeader(String leaderUsername) {
		return partyRepository.findByLeader(leaderUsername);
	}

	public Optional<Party> getPartyFromMember(String memberUsername) {
		return partyRepository.findByMember(memberUsername);
	}

	public Optional<Party> getPartyFromId(Integer id) {
		return partyRepository.findById(id);
	}
}
