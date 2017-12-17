package com.valhallagame.partyserviceclient;

import java.io.IOException;

import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.common.RestCaller;
import com.valhallagame.common.RestResponse;
import com.valhallagame.partyserviceclient.model.AcceptParameter;
import com.valhallagame.partyserviceclient.model.CancelCharacterInviteParameter;
import com.valhallagame.partyserviceclient.model.CancelPersonInviteParameter;
import com.valhallagame.partyserviceclient.model.DeclineParameter;
import com.valhallagame.partyserviceclient.model.GetPartyAndInvitesParameter;
import com.valhallagame.partyserviceclient.model.GetPartyParameter;
import com.valhallagame.partyserviceclient.model.InviteCharacterParameter;
import com.valhallagame.partyserviceclient.model.InvitePersonParameter;
import com.valhallagame.partyserviceclient.model.KickCharacterFromPartyParameter;
import com.valhallagame.partyserviceclient.model.KickPersonFromPartyParameter;
import com.valhallagame.partyserviceclient.model.LeavePartyParameter;
import com.valhallagame.partyserviceclient.model.PromotePersonToLeaderParameter;
import com.valhallagame.partyserviceclient.model.Party;
import com.valhallagame.partyserviceclient.model.PartyAndInvites;
import com.valhallagame.partyserviceclient.model.PromoteCharacterToLeaderParameter;

public class PartyServiceClient {
	private static PartyServiceClient partyServiceClient;

	private String partyServiceServerUrl = "http://localhost:" + DefaultServicePortMappings.PARTY_SERVICE_PORT;
	private RestCaller restCaller;

	private PartyServiceClient() {
		restCaller = new RestCaller();
	}

	public static void init(String partyServiceServerUrl) {
		PartyServiceClient client = get();
		client.partyServiceServerUrl = partyServiceServerUrl;
	}

	public static PartyServiceClient get() {
		if (partyServiceClient == null) {
			partyServiceClient = new PartyServiceClient();
		}
		return partyServiceClient;
	}

	public RestResponse<String> invitePerson(String senderUsername, String receiverUsername) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/invite-person",
				new InvitePersonParameter(senderUsername, receiverUsername), String.class);
	}

	public RestResponse<String> inviteCharacter(String senderUsername, String characterName) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/invite-character",
				new InviteCharacterParameter(senderUsername, characterName), String.class);
	}
	
	public RestResponse<String> cancelCharacterInvite(String cancelerUsername, String canceleeCharacterName)
			throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/cancel-character-invite",
				new CancelCharacterInviteParameter(cancelerUsername, canceleeCharacterName), String.class);
	}
	
	public RestResponse<String> cancelPersonInvite(String cancelerUsername, String canceleeUsername)
			throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/cancel-person-invite",
				new CancelPersonInviteParameter(cancelerUsername, canceleeUsername), String.class);
	}

	public RestResponse<String> acceptInvite(String accpeterUsername, Integer partyId) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/accpet-invite",
				new AcceptParameter(accpeterUsername, partyId), String.class);
	}

	public RestResponse<String> declineInvite(String declinerUsername, Integer partyId) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/decline-invite",
				new DeclineParameter(declinerUsername, partyId), String.class);
	}

	public RestResponse<String> leaveParty(String leaverUsername) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/leave-party",
				new LeavePartyParameter(leaverUsername), String.class);
	}

	public RestResponse<String> promotePersonToLeader(String promoter, String promotee) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/promote-person-to-leader",
				new PromotePersonToLeaderParameter(promoter, promotee), String.class);
	}
	
	public RestResponse<String> promoteCharacterToLeader(String promoter, String promoteeCharacterName) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/promote-character-to-leader",
				new PromoteCharacterToLeaderParameter(promoter, promoteeCharacterName), String.class);
	}

	public RestResponse<String> kickCharacterFromParty(String kicker, String kickeeCharacterName) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/kick-character-from-party",
				new KickCharacterFromPartyParameter(kicker, kickeeCharacterName), String.class);
	}
	
	public RestResponse<String> kickPersonFromParty(String kicker, String kickee) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/kick-person-from-party",
				new KickPersonFromPartyParameter(kicker, kickee), String.class);
	}

	public RestResponse<Party> getParty(String username) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/get-party", new GetPartyParameter(username),
				Party.class);
	}

	public RestResponse<PartyAndInvites> getPartyAndInvites(String username) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/get-party-and-invites",
				new GetPartyAndInvitesParameter(username), PartyAndInvites.class);
	}

}
