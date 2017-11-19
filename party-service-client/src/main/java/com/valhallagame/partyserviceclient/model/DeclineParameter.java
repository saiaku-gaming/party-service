package com.valhallagame.partyserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclineParameter {
	private String declinerUsername;
	private Integer partyId;
}
