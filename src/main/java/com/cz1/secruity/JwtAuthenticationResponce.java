package com.cz1.secruity;

/**
 * Created by wkchen on 2017/3/22.
 */
public class JwtAuthenticationResponce {


    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponce(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
