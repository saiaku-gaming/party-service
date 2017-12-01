package com.valhallagame.partyserviceclient.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartyAndInvites {
	Party party;
	List<String> invites;
}
