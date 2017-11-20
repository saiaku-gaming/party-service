package com.valhallagame.partyserviceserver.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.valhallagame.common.JS;
import com.valhallagame.partyserviceserver.message.AcceptParameter;
import com.valhallagame.partyserviceserver.message.CancelPersonInviteParameter;
import com.valhallagame.partyserviceserver.message.DeclineParameter;
import com.valhallagame.partyserviceserver.message.InvitePersonParameter;
import com.valhallagame.partyserviceserver.message.LeavePartyParameter;
import com.valhallagame.partyserviceserver.message.PromoteToLeaderParameter;
import com.valhallagame.partyserviceserver.model.Party;
import com.valhallagame.partyserviceserver.model.PartyInvite;
import com.valhallagame.partyserviceserver.service.PartyInviteService;
import com.valhallagame.partyserviceserver.service.PartyService;
import com.valhallagame.personserviceclient.PersonServiceClient;
import com.valhallagame.personserviceclient.model.Person;

@Controller
@RequestMapping(path = "/v1/party")
public class PartyController {

	@Autowired
	private PartyService partyService;

	@Autowired
	private PartyInviteService partyInviteService;

	@RequestMapping(path = "/invite-character", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> inviteCharacter() {
		// TODO define when character service exists
		// Optional<Char> invitedCharacterOpt =
		// characterService.getCharacterFromName(characterName.getName());
		// return invitedCharacterOpt.<ResponseEntity<?>>map(aChar ->
		// invitePerson(user, aChar.getPerson()))
		// .orElseGet(() -> JS.message(NOT_FOUND, "Could not find character with
		// name " + characterName.getName()));
		return JS.message(HttpStatus.NOT_IMPLEMENTED, "Character service needed to finish this request");
	}

	@RequestMapping(path = "/invite-person", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> invitePerson(@RequestBody InvitePersonParameter input) throws IOException {
		Optional<Person> optReceiver = PersonServiceClient.get().getPerson(input.getReceiverUsername()).getResponse();

		if (!optReceiver.isPresent()) {
			return JS.message(HttpStatus.NOT_FOUND,
					"Could not find person with username " + input.getReceiverUsername());
		}

		return invitePerson(input.getSenderUsername(), input.getReceiverUsername());
	}

	@RequestMapping(path = "/cancel-invite-character", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> cancelInviteCharacter() {
		return JS.message(HttpStatus.NOT_IMPLEMENTED, "Not implemented yet...");
		// log.debug(user.getDisplayUsername() + " canceled invide for " +
		// characterName);
		// Optional<Char> invitedCharacterOpt =
		// characterService.getCharacterFromName(characterName.getName());
		//
		// if (!invitedCharacterOpt.isPresent()) {
		// return JS.message(NOT_FOUND, "Could not find character with name " +
		// characterName.getName());
		// }
		//
		// Person invitedUser = invitedCharacterOpt.get().getPerson();
		// Party invitedParty = invitedUser.getParty();
		//
		// if (invitedParty != null) {
		// return JS.message(CONFLICT, "Character already in a party.");
		// }
		//
		// String reason = "Cancel party invite.";
		//
		// Party inviteeParty = user.getParty();
		// if (inviteeParty == null) {
		// // Could be a non active party. Lets remove those.
		// boolean removed = partyService.clearNonActiveParty(user,
		// invitedUser);
		// if (!removed) {
		// return JS.message(BAD_REQUEST, "No invite found");
		// } else {
		// notificationService.addNotifications(NotificationType.PARTYCHANGE,
		// reason, invitedUser);
		// notificationService.addNotifications(NotificationType.PARTYCHANGE,
		// reason, user);
		// return JS.message(OK, "Removed invite");
		// }
		// } else {
		// if (!inviteeParty.getPartyLeader().equals(user)) {
		// return JS.message(BAD_REQUEST, "Only party leader can remove
		// invite");
		// }
		// inviteeParty.getPartyPending().remove(invitedUser);
		// partyService.saveParty(inviteeParty);
		// notifyEveryone(inviteeParty, reason, invitedUser);
		// return JS.message(OK, "Removed invite to character with name " +
		// characterName.getName());
		// }
	}

	@RequestMapping(path = "/cancel-person-invite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> cancelPersonInvite(@RequestBody CancelPersonInviteParameter input) throws IOException {
		if (!PersonServiceClient.get().getPerson(input.getCanceleeUsername()).isOk()) {
			return JS.message(HttpStatus.NOT_FOUND,
					"Could not find person with username " + input.getCanceleeUsername());
		}

		Optional<Party> optCanceleeParty = partyService.getPartyFromMember(input.getCanceleeUsername());

		if (optCanceleeParty.isPresent() && optCanceleeParty.get().isActive()) {
			return JS.message(HttpStatus.CONFLICT, "Person already in a party.");
		}
		Optional<Party> optCancelerParty = partyService.getPartyFromMember(input.getCancelerUsername());
		if (!optCancelerParty.isPresent()) {
			return JS.message(HttpStatus.NOT_FOUND, input.getCancelerUsername() + " is not in a party");
		} else {
			Party cancelerParty = optCancelerParty.get();
			if (!cancelerParty.getLeader().equals(input.getCancelerUsername())) {
				return JS.message(HttpStatus.BAD_REQUEST, "Only party leader can remove invite");
			}
			List<PartyInvite> invites = partyInviteService.getInvitesFromParty(cancelerParty);
			Optional<PartyInvite> optInvite = invites.stream()
					.filter(i -> i.getReceiver().equals(input.getCanceleeUsername())).findAny();
			if (!optInvite.isPresent()) {
				JS.message(HttpStatus.NOT_FOUND, "Could not find an invite for that user");
			}
			partyInviteService.deletePartyInvite(optInvite.get());
			if (invites.size() <= 1) {
				partyService.deleteParty(cancelerParty);
			}

			// String reason = "Cancel an invite for a person to party";
			// TODO add notification
			// notifyEveryone(inviteeParty, reason, invitedPerson);
			return JS.message(HttpStatus.OK, "Removed invite to person with username " + input.getCancelerUsername());
		}
	}

	@RequestMapping(path = "/accpet-invite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> acceptInvite(@RequestBody AcceptParameter input) {
		Optional<Party> optAccepterParty = partyService.getPartyFromMember(input.getAccepterUsername());
		if (optAccepterParty.isPresent() && optAccepterParty.get().isActive()) {
			return JS.message(HttpStatus.CONFLICT, "Cannot accept invite when in party.");
		}

		Optional<Party> optParty = partyService.getPartyFromId(input.getPartyId());
		if (!optParty.isPresent()) {
			return JS.message(HttpStatus.NOT_FOUND, "Could not find party with id " + input.getPartyId());
		}

		Party party = optParty.get();
		Optional<PartyInvite> optPartyInvite = partyInviteService.getInviteFromPartyAndReceiver(party,
				input.getAccepterUsername());

		if (!optPartyInvite.isPresent()) {
			return JS.message(HttpStatus.NOT_FOUND, "User is not invited to party.");
		}

		partyInviteService.deletePartyInvite(optPartyInvite.get());
		party.getPartyMembers().add(input.getAccepterUsername());

		// If this was an inactive group, add the party leader to the group to
		// make it active.
		if (!party.isActive()) {
			party.setActive(true);
		}

		// add notification
		// String reason = "Accept invite";
		// notifyEveryone(party, reason, user);

		party = partyService.saveParty(party);

		if (optAccepterParty.isPresent()) {
			partyService.deleteParty(optAccepterParty.get());
		}
		partyInviteService.deleteInvitesFromReceiver(input.getAccepterUsername());

		return JS.message(HttpStatus.OK, "Accepted invite");
	}

	@RequestMapping(path = "/decline-invite", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> declineInvite(@RequestBody DeclineParameter input) {
		Optional<Party> optDeclinerParty = partyService.getPartyFromMember(input.getDeclinerUsername());
		if (optDeclinerParty.isPresent() && optDeclinerParty.get().isActive()) {
			return JS.message(HttpStatus.CONFLICT, "Cannot accept invite when in party.");
		}

		Optional<Party> optParty = partyService.getPartyFromId(input.getPartyId());
		if (!optParty.isPresent()) {
			return JS.message(HttpStatus.NOT_FOUND, "Could not find party with id: " + input.getPartyId());
		}

		Party party = optParty.get();
		Optional<PartyInvite> optPartyInvite = partyInviteService.getInviteFromPartyAndReceiver(party,
				input.getDeclinerUsername());

		if (!optPartyInvite.isPresent()) {
			return JS.message(HttpStatus.NOT_FOUND,
					"User is not invited to party with " + input.getPartyId() + " so cant decline.");
		}

		partyInviteService.deletePartyInvite(optPartyInvite.get());

		// TODO add notification
		// notifyEveryone(party, "Decline an invite to party.", user);
		return JS.message(HttpStatus.OK, "Removed invite");
	}

	@RequestMapping(path = "/leave-party", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> leaveCurrentParty(@RequestBody LeavePartyParameter input) {
		Optional<Party> optParty = partyService.getPartyFromMember(input.getLeaverUsername());
		if (!optParty.isPresent()) {
			return JS.message(HttpStatus.NOT_FOUND, "User is not in party.");
		}

		Party party = optParty.get();

		if (party.getPartyMembers().size() <= 2) {

			partyService.deleteParty(party);
			// TODO add notification
			// notifyEveryone(party, "leave party", user);

			return JS.message(HttpStatus.OK, "Party disbanded.");
		} else {
			party.getPartyMembers().remove(input.getLeaverUsername());
			if (party.getLeader().equals(input.getLeaverUsername())) {
				String newPartyLeader = party.getPartyMembers().get(0);
				party.setLeader(newPartyLeader);
			}
			partyService.saveParty(party);
			// TODO add notification
			// notifyEveryone(party, "leave party", user);
			return JS.message(HttpStatus.OK, "User removed from party.");
		}
	}

	@RequestMapping(path = "/promote-to-leader", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> promoteToLeader(@RequestBody PromoteToLeaderParameter input) {
		return JS.message(HttpStatus.NOT_IMPLEMENTED, "waiting for character service");
		// Optional<Char> promotedCharacterOpt =
		// characterService.getCharacterFromName(characterName.getName());
		// if (!promotedCharacterOpt.isPresent()) {
		// return JS.message(NOT_FOUND, "Could not find character with name " +
		// characterName.getName());
		// }
		// Person promotedUser = promotedCharacterOpt.get().getPerson();
		// Party party = user.getParty();
		// if (!party.getPartyMembers().contains(promotedUser)) {
		// return JS.message(NOT_FOUND, "Character not in party: " +
		// characterName.getName());
		// } else {
		//
		// // Transfer ownership of instance.
		// Optional<Dungeon> dungeonOpt =
		// dungeonService.getDungeonByOwner(user);
		// if (dungeonOpt.isPresent()) {
		// dungeonOpt.get().setPerson(promotedUser);
		// dungeonService.saveDungeon(dungeonOpt.get());
		// notificationService.addNotifications(NotificationType.DUNGEONCHANGE,
		// "promoted member to leader", user);
		// notificationService.addNotifications(NotificationType.DUNGEONCHANGE,
		// "promoted member to leader",
		// promotedUser);
		// }
		//
		// party.setPartyLeader(promotedUser);
		// partyService.saveParty(party);
		// notifyEveryone(party, "Promoted member to leader", user);
		// return JS.message(OK, "Character promoted to leader.");
		// }
	}

	@RequestMapping(path = "/kick-from-party", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> kickMember() {
		return JS.message(HttpStatus.NOT_IMPLEMENTED, "Waiting for character service");
		// log.debug(characterName + " was kicked from party " +
		// user.getParty().getId());
		//
		// Party party = user.getParty();
		// Optional<Char> kickedCharacterOpt =
		// characterService.getCharacterFromName(characterName.getName());
		//
		// if (!kickedCharacterOpt.isPresent()) {
		// return JS.message(NOT_FOUND, "Could not find character with name " +
		// characterName.getName());
		// }
		//
		// Person kickedPerson = kickedCharacterOpt.get().getPerson();
		// if (kickedPerson.equals(user)) {
		// return JS.message(CONFLICT, "Are you really trying to kick yourself?
		// Use \"leave\" instead");
		// }
		// if (!party.getPartyMembers().contains(kickedPerson)) {
		// return JS.message(NOT_FOUND, "Character not in party: " +
		// characterName.getName());
		// } else {
		// party.getPartyMembers().remove(kickedPerson);
		// partyService.saveParty(party);
		//
		// notificationService.addNotifications(NotificationType.PARTYCHANGE,
		// "Kicked member", kickedPerson);
		// notifyEveryone(party, "Kicked memeber", user);
		//
		// if (party.getPartyMembers().size() <= 1 &&
		// party.getPartyPending().size() <= 1) {
		// partyService.deleteParty(party);
		// return JS.message(OK, "Party disbanded.");
		// }
		// return JS.message(OK, "Kicked character.");
		// }
	}

	private ResponseEntity<?> invitePerson(String senderUsername, String receiverUsername) {
		Optional<Party> optReceiverParty = partyService.getPartyFromMember(receiverUsername);
		if (optReceiverParty.isPresent() && optReceiverParty.get().isActive()) {
			return JS.message(HttpStatus.CONFLICT, receiverUsername + " is already in a party");
		}

		Optional<Party> optParty = partyService.getPartyFromMember(senderUsername);
		Party party = null;

		if (optParty.isPresent()) {
			party = optParty.get();
			if (!party.getLeader().equals(senderUsername)) {
				return JS.message(HttpStatus.BAD_REQUEST, "Only party leader can invite");
			}
		} else {
			party = partyService.saveParty(new Party(senderUsername));
		}

		if (partyInviteService.getInviteFromPartyAndReceiver(party, receiverUsername).isPresent()) {
			return JS.message(HttpStatus.CONFLICT, receiverUsername + " already has an invite to this party");
		}

		partyInviteService.savePartyInvite(new PartyInvite(party, senderUsername, receiverUsername));
		return JS.message(HttpStatus.OK, "Party invite sent");
	}
}
