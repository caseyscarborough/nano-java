package com.caseyscarborough.raiblocks;

abstract class BaseResponse {

    private String error;

    boolean isSuccess() {
        return error == null;
    }

    String getError() {
        return error;
    }
}
