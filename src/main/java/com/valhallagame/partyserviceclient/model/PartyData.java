package com.valhallagame.partyserviceclient.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartyData {
	private Integer id;
	private PartyMemberData leader;
	private List<PartyMemberData> partyMembers;
	private List<PartyInviteData> sentInvites;
	private boolean active;
}
