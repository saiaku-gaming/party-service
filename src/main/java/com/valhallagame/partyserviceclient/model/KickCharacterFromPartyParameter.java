package com.valhallagame.partyserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KickCharacterFromPartyParameter {
	private String kickerUsername;
	private String kickeeCharacterName;
}
