package com.adme.learning.spring.ai.anthropic.conversation.services.events;

import com.adme.learning.spring.ai.anthropic.conversation.models.Event;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public final class EventBuilderService {

  private static final String EVENT_NAME = "open.ai.conversation.message";
  private static final String PRODUCER = "anthropic.ai.message.producer";

  public Event buildEvent(Map<String, String> responses) {
    String response = responses.get("response");
    return new Event.Builder()
        .idEvent()
        .nameEvent(EVENT_NAME)
        .content(response)
        .producer(PRODUCER)
        .build();
  }

}
