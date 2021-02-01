package com.brep.personal.utils;

import org.springframework.stereotype.Component;

/**
 * 설명 :
 *
 * @author 이민호(Mark) / minholee93@sk.com
 * 2021/01/31
 * 2:42 오후
 */
@Component
public class UserContext {
    public static final String CORRELATION_ID = "brep-correlation-id";
    public static final String AUTH_TOKEN = "brep-auth-token";
    public static final String USER_ID = "brep-user-id";
    public static final String ORG_ID = "brep-org-id";

    private String correlationId = new String();
    private String authToken = new String();
    private String userId = new String();
    private String orgId = new String();

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
