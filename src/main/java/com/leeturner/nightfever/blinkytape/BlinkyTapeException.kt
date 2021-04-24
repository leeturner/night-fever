package com.leeturner.nightfever.blinkytape;

public class BlinkyTapeException extends RuntimeException {

    private static final long serialVersionUID = 4L;

    public BlinkyTapeException(String message, Throwable t) {
        super(message, t);
    }
}
