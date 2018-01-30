package com.caseyscarborough.raiblocks;

import com.caseyscarborough.raiblocks.BaseResponse;

public class AccountBlockCount extends BaseResponse {

    private String blockCount;

    public String getBlockCount() {
        return blockCount;
    }

    public void setBlockCount(String blockCount) {
        this.blockCount = blockCount;
    }
}
