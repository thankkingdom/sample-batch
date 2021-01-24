package com.example.samplebatch.util;

import java.util.Stack;

import org.slf4j.Logger;

public class StopWatch {

    private Stack<Long> stack;

    public StopWatch() {

        stack = new Stack<>();
    }

    private void push() {

        stack.add(System.currentTimeMillis());
    }

    private long pop() {

        return stack.pop();
    }

    public final void start(Logger logger, String processName) {

        push();
        logger.info(String.format("%s start.", processName));
    }

    public final void end(Logger logger, String processName) {

        logger.info(String.format("%s end - %d ms.", processName, System.currentTimeMillis() - pop()));
    }
}
