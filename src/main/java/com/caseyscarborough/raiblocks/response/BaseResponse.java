package com.caseyscarborough.raiblocks.response;

public abstract class BaseResponse {

    private String error;

    public boolean isSuccess() {
        return error == null;
    }

    public String getError() {
        return error;
    }
}
