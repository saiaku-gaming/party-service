package com.valhallagame.partyserviceclient.message;

import com.valhallagame.common.validation.CheckLowercase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KickCharacterFromPartyParameter {
	@NotNull
	@CheckLowercase
	private String username;
	@NotBlank
	private String displayCharacterName;
}
