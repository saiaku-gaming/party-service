package com.valhallagame.partyserviceclient;

import com.valhallagame.common.AbstractServiceClient;
import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.common.RestResponse;
import com.valhallagame.partyserviceclient.message.*;
import com.valhallagame.partyserviceclient.model.PartyAndInvitesData;
import com.valhallagame.partyserviceclient.model.PartyData;

import java.io.IOException;

public class PartyServiceClient extends AbstractServiceClient {
	private static PartyServiceClient partyServiceClient;

	private PartyServiceClient() {
		serviceServerUrl = "http://localhost:" + DefaultServicePortMappings.PARTY_SERVICE_PORT;
	}

	public static void init(String serviceServerUrl) {
		PartyServiceClient client = get();
		client.serviceServerUrl = serviceServerUrl;
	}

	public static PartyServiceClient get() {
		if (partyServiceClient == null) {
			partyServiceClient = new PartyServiceClient();
		}
		return partyServiceClient;
	}

	public RestResponse<String> invitePerson(String senderUsername, String receiverUsername) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/invite-person",
				new InvitePersonParameter(senderUsername, receiverUsername), String.class);
	}

	public RestResponse<String> inviteCharacter(String senderUsername, String characterName) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/invite-character",
				new InviteCharacterParameter(senderUsername, characterName), String.class);
	}

	public RestResponse<String> cancelCharacterInvite(String cancelerUsername, String canceleeCharacterName)
			throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/cancel-character-invite",
				new CancelCharacterInviteParameter(cancelerUsername, canceleeCharacterName), String.class);
	}

	public RestResponse<String> acceptInvite(String accpeterUsername, Integer partyId) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/accept-invite",
				new AcceptParameter(accpeterUsername, partyId), String.class);
	}

	public RestResponse<String> declineInvite(String declinerUsername, Integer partyId) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/decline-invite",
				new DeclineParameter(declinerUsername, partyId), String.class);
	}

	public RestResponse<String> leaveParty(String leaverUsername) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/leave-party",
				new LeavePartyParameter(leaverUsername), String.class);
	}

	public RestResponse<String> promoteCharacterToLeader(String promoter, String promoteeCharacterName)
			throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/promote-character-to-leader",
				new PromoteCharacterToLeaderParameter(promoter, promoteeCharacterName), String.class);
	}

	public RestResponse<String> kickCharacterFromParty(String kicker, String kickeeCharacterName) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/kick-character-from-party",
				new KickCharacterFromPartyParameter(kicker, kickeeCharacterName), String.class);
	}

	public RestResponse<PartyData> getParty(String username) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/get-party", new GetPartyParameter(username),
				PartyData.class);
	}

	public RestResponse<PartyData> getPartyById(Integer partyId) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/get-party-by-id",
				new GetPartyByIdParameter(partyId), PartyData.class);
	}

	public RestResponse<PartyAndInvitesData> getPartyAndInvites(String username) throws IOException {
		return restCaller.postCall(serviceServerUrl + "/v1/party/get-party-and-invites",
				new GetPartyAndInvitesParameter(username), PartyAndInvitesData.class);
	}

}
