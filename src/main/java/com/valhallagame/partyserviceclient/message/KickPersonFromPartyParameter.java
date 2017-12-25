package com.valhallagame.partyserviceclient.message;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KickPersonFromPartyParameter {
	@NotNull
	private String kickerUsername;
	@NotNull
	private String kickeeUsername;
}
