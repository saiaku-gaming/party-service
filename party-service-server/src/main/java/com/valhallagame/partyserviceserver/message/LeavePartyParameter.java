package com.valhallagame.partyserviceserver.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeavePartyParameter {
	private String leaverUsername;
}
