package com.valhallagame.partyserviceserver.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valhallagame.partyserviceserver.model.Party;
import com.valhallagame.partyserviceserver.model.PartyInvite;
import com.valhallagame.partyserviceserver.repository.PartyInviteRepository;

@Service
public class PartyInviteService {

	@Autowired
	private PartyInviteRepository partyInviteRepository;

	public PartyInvite savePartyInvite(PartyInvite partyInvite) {
		return partyInviteRepository.save(partyInvite);
	}

	public void deletePartyInvite(PartyInvite partyInvite) {
		partyInviteRepository.delete(partyInvite);
	}

	public List<PartyInvite> getInvitesFromParty(Party party) {
		return partyInviteRepository.findInvitesByParty(party);
	}

	public Optional<PartyInvite> getInviteFromPartyAndReceiver(Party party, String receiverUsername) {
		return partyInviteRepository.findInviteByPartyAndReceiver(party.getId(), receiverUsername);
	}

	public void deleteInvitesFromReceiver(String receiver) {
		partyInviteRepository.deleteInvitesByReceiver(receiver);
	}
}
