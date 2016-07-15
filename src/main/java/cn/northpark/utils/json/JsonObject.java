package cn.northpark.utils.json;

import java.util.HashMap;
import java.util.Map;

public class JsonObject implements OAuthInterfaces{
	private Map<String,String> oauthMap = new HashMap<String,String>();
	public Map<String, String> getOauthMap() {
		return oauthMap;
	}
	public void setOauthMap(Map<String, String> oauthMap) {
		this.oauthMap = oauthMap;
	}
	@Override
	public void authorizationRequest(String client_id, String client_secret,
			String redirect_uri,String state,String response_type,String scope) {
		oauthMap.put("client_id",client_id);
		oauthMap.put("client_secret",client_secret);
		oauthMap.put("redirect_uri",redirect_uri);
		oauthMap.put("state", state);
		oauthMap.put("response_type",response_type);
	    oauthMap.put("scope",scope);
	}
	@Override
	public void authorizationGrant() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Map<String, Map<String, String>> authorizationGrant(
			String client_id, String client_secret, String redirect_uri) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAccessTokenByCode() {
		// TODO Auto-generated method stub
		return null;
	}
}
