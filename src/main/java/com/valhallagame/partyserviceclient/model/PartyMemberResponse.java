package com.valhallagame.partyserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyMemberResponse {
	String username;
	String characterName;
}
