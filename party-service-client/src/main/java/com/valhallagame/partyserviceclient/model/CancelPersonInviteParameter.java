package com.valhallagame.partyserviceclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelPersonInviteParameter {
	private String cancelerUsername;
	private String canceleeUsername;
}