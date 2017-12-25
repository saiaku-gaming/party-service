package com.valhallagame.partyserviceclient.message;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromoteCharacterToLeaderParameter {
	@NotNull
	private String promoterUsername;
	@NotNull
	private String promoteeCharacterName;
}
