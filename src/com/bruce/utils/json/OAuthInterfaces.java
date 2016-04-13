package com.bruce.utils.json;
import java.util.Map;
public interface OAuthInterfaces {
	public void authorizationRequest(String client_id,String client_secret,String redirect_uri,String state,String response_type,String scope);
	public void authorizationGrant();
	public Map<String,Map<String,String>> authorizationGrant(String client_id,String client_secret,String redirect_uri);
	public String getAccessTokenByCode();
}
