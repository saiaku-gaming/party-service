package com.valhallagame.partyserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyInviteResponse {
	private Integer id;
	private Integer partyId;
	private PartyMemberResponse sender;
	private PartyMemberResponse receiver;
}
