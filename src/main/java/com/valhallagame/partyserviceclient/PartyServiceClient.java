package com.valhallagame.partyserviceclient;

import java.io.IOException;

import com.valhallagame.common.DefaultServicePortMappings;
import com.valhallagame.common.RestCaller;
import com.valhallagame.common.RestResponse;
import com.valhallagame.partyserviceclient.model.AcceptParameter;
import com.valhallagame.partyserviceclient.model.CancelPersonInviteParameter;
import com.valhallagame.partyserviceclient.model.DeclineParameter;
import com.valhallagame.partyserviceclient.model.InvitePersonParameter;
import com.valhallagame.partyserviceclient.model.KickFromPartyParameter;
import com.valhallagame.partyserviceclient.model.LeavePartyParameter;
import com.valhallagame.partyserviceclient.model.PromoteToLeaderParameter;

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

	public RestResponse<String> promoteToLeader(String promoter, String promotee) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/promote-to-leader",
				new PromoteToLeaderParameter(promoter, promotee), String.class);
	}

	public RestResponse<String> kickFromParty(String kicker, String kickee) throws IOException {
		return restCaller.postCall(partyServiceServerUrl + "/v1/party/kick-from-party",
				new KickFromPartyParameter(kicker, kickee), String.class);
	}
}
