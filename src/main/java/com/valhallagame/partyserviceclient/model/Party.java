package com.valhallagame.partyserviceclient.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {
	private Integer id;
	private String leader;
	private List<String> partyMembers;
	private List<PartyInvite> sentInvites;
	private boolean active;
}
