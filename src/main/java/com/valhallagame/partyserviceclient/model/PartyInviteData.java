package com.valhallagame.partyserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyInviteData {
	private Integer id;
	private Integer partyId;
	private PartyMemberData sender;
	private PartyMemberData receiver;
}
