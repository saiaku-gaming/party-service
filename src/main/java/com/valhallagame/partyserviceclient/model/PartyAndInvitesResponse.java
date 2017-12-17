package com.valhallagame.partyserviceclient.model;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyAndInvitesResponse {
	Optional<PartyResponse> party;
	List<PartyInviteResponse> receivedInvites;
}
