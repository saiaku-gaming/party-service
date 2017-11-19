package com.valhallagame.partyserviceserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "party")
public class Party {
	@Id
	@SequenceGenerator(name = "party_party_id_seq", sequenceName = "party_party_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party_party_id_seq")
	@Column(name = "party_id", updatable = false)
	private Integer id;

	@Column(name = "leader")
	private String leader;

	@ElementCollection
	@JoinTable(name = "party_member", joinColumns = @JoinColumn(name = "party_id"))
	@Column(name = "username")
	private List<String> partyMembers;

	@Column(name = "active")
	private boolean active;

	public Party(String leader) {
		this.leader = leader;
		this.partyMembers = new ArrayList<>();
		this.partyMembers.add(leader);
		this.active = false;
	}
}
