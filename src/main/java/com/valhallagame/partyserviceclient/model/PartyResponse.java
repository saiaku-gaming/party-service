package com.valhallagame.partyserviceclient.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyResponse {
	private Integer id;
	private PartyMemberResponse leader;
	private List<PartyMemberResponse> partyMembers;
	private List<PartyInviteResponse> sentInvites;
	private boolean active;
}
