package com.valhallagame.partyserviceclient.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.validation.CheckLowercase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitePersonParameter {
	@NotNull
	@CheckLowercase
	private String username;
	@NotNull
	@CheckLowercase
	private String targetUsername;
}
