package com.backend.scribble.api;

import com.backend.scribble.event.EventType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class API {
    public final Map<EventType, String> subscribes = Arrays.stream(EventType.values()).collect(Collectors.toMap(
            (eventType -> eventType), (EventType::getSubscribeName)
    ));

    public final Map<EventType, String> producers = List.of(
            EventType.SETTINGS,
            EventType.START_GAME
        ).stream().collect(Collectors.toMap((eventType -> eventType), (EventType::getProducerName)
    ));
}
