package com.valhallagame.partyserviceserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.valhallagame.partyserviceserver.model.Party;

public interface PartyRepository extends JpaRepository<Party, Integer> {
	public Optional<Party> findByLeader(String leaderUsername);

	@Query(value = "SELECT * FROM party p JOIN party_member pm ON(p.party_id = pm.party_id) WHERE pm.username = :username", nativeQuery = true)
	public Optional<Party> findByMember(@Param("username") String memberUsername);

	public Optional<Party> findById(Integer partyId);
}
