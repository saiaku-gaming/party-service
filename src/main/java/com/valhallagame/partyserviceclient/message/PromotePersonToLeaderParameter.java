package com.valhallagame.partyserviceclient.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.validation.CheckLowercase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotePersonToLeaderParameter {
	@NotNull
	@CheckLowercase
	private String promoterUsername;
	@NotNull
	@CheckLowercase
	private String promoteeUsername;
}
