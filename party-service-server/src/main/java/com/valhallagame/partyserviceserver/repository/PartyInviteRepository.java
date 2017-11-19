package com.valhallagame.partyserviceserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.valhallagame.partyserviceserver.model.Party;
import com.valhallagame.partyserviceserver.model.PartyInvite;

public interface PartyInviteRepository extends JpaRepository<PartyInvite, Integer> {
	// @Query(value = "SELECT * FROM party_invite WHERE party_id = :party_id",
	// nativeQuery = true)
	public List<PartyInvite> findInvitesByParty(Party party);

	@Query(value = "SELECT * FROM party_invite WHERE party_id = :partyId AND receiver = :receiver", nativeQuery = true)
	public Optional<PartyInvite> findInviteByPartyAndReceiver(@Param("partyId") Integer partyId,
			@Param("receiver") String receiverUsername);

	@Transactional
	@Modifying
	@Query
	public void deleteInvitesByReceiver(String receiver);
}
