package com.valhallagame.partyserviceclient;

import java.io.IOException;

import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.common.RestCaller;
import com.valhallagame.common.RestResponse;
import com.valhallagame.partyserviceclient.message.AcceptParameter;
import com.valhallagame.partyserviceclient.message.CancelCharacterInviteParameter;
import com.valhallagame.partyserviceclient.message.CancelPersonInviteParameter;
import com.valhallagame.partyserviceclient.message.DeclineParameter;
import com.valhallagame.partyserviceclient.message.GetPartyAndInvitesParameter;
import com.valhallagame.partyserviceclient.message.GetPartyParameter;
import com.valhallagame.partyserviceclient.message.InviteCharacterParameter;
import com.valhallagame.partyserviceclient.message.InvitePersonParameter;
import com.valhallagame.partyserviceclient.message.KickCharacterFromPartyParameter;
import com.valhallagame.partyserviceclient.message.KickPersonFromPartyParameter;
import com.valhallagame.partyserviceclient.message.LeavePartyParameter;
import com.valhallagame.partyserviceclient.message.PromoteCharacterToLeaderParameter;
import com.valhallagame.partyserviceclient.message.PromotePersonToLeaderParameter;
import com.valhallagame.partyserviceclient.model.PartyAndInvitesData;
import com.valhallagame.partyserviceclient.model.PartyData;

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
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/accept-invite",
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

	public RestResponse<PartyData> getParty(String username) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/get-party", new GetPartyParameter(username),
				PartyData.class);
	}

	public RestResponse<PartyAndInvitesData> getPartyAndInvites(String username) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/get-party-and-invites",
				new GetPartyAndInvitesParameter(username), PartyAndInvitesData.class);
	}

}
