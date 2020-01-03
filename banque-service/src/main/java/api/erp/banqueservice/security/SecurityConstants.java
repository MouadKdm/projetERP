package api.erp.banqueservice.security;

public class SecurityConstants {

    public static final String SIGN_UP_URLS = "/agentAvAuth/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 3000000_0000000l; //300 seconds
}
