package com.caseyscarborough.raiblocks;

public class RaiBlocksException extends RuntimeException {
    public RaiBlocksException(String message) {
        super(message);
    }

    public RaiBlocksException(String message, Throwable cause) {
        super(message, cause);
    }
}
