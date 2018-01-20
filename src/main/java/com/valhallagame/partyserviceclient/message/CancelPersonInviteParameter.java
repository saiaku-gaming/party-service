package com.valhallagame.partyserviceclient.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.validation.CheckLowercase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPersonInviteParameter {
	@NotNull
	@CheckLowercase
	private String cancelerUsername;
	@NotNull
	@CheckLowercase
	private String canceleeUsername;
}
