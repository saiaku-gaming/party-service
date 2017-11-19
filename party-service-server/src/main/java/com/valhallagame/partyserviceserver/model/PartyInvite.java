package com.valhallagame.partyserviceserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "party_invite")
public class PartyInvite {
	@Id
	@SequenceGenerator(name = "party_invite_party_invite_id_seq", sequenceName = "party_invite_party_invite_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party_invite_party_invite_id_seq")
	@Column(name = "party_invite_id", updatable = false)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "party_id")
	private Party party;

	@Column(name = "sender")
	private String sender;

	@Column(name = "receiver")
	private String receiver;

	public PartyInvite(Party party, String sender, String receiver) {
		this.party = party;
		this.sender = sender;
		this.receiver = receiver;
	}
}
