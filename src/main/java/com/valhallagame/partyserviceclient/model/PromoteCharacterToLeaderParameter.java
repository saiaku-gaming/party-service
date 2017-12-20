package com.valhallagame.partyserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoteCharacterToLeaderParameter {
	private String promoterUsername;
	private String promoteeCharacterName;
}
