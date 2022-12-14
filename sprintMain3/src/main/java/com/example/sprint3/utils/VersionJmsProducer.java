package com.example.sprint3.utils;

import java.util.Map;

public interface VersionJmsProducer {
    void sendVersion(Map<String,Object> message);
}
