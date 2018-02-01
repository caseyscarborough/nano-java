package com.caseyscarborough.nano;

import java.util.HashMap;
import java.util.Map;

class Request {

    private static final String ACTION_KEY = "action";
    private final String action;
    private final Map<String, Object> map;

    public Request(String action, Map<String, Object> map) {
        this.action = action;
        this.map = map;
        this.map.put(ACTION_KEY, action);
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public String getAction() {
        return action;
    }

    public static Builder action(String action) {
        return new Builder(action);
    }

    public static class Builder {

        private final String action;
        private Map<String, Object> map;

        public Builder(String action) {
            this.action = action;
            this.map = new HashMap<>();
        }

        public Builder param(String key, Object value) {
            map.put(key, value);
            return this;
        }

        public Request build() {
            return new Request(action, map);
        }
    }
}
