package com.valhallagame.partyserviceclient.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.validation.CheckLowercase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KickPersonFromPartyParameter {
	@NotNull
	@CheckLowercase
	private String kickerUsername;
	@NotNull
	@CheckLowercase
	private String kickeeUsername;
}
