package com.valhallagame.partyserviceclient.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.ExposedNameInYmer;
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
	private String username;
	@NotNull
	@CheckLowercase
	@ExposedNameInYmer("username")
	private String canceleeUsername;
}
