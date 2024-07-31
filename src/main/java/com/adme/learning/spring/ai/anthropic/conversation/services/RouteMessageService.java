package com.adme.learning.spring.ai.anthropic.conversation.services;

import com.adme.learning.spring.ai.anthropic.conversation.models.Event;
import com.adme.learning.spring.ai.anthropic.conversation.models.SystemEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class RouteMessageService {

  private final Map<String, IEventService> mapEvent;


  public RouteMessageService() {
    this.mapEvent = new HashMap<>();
  }

  public void routeEvent(Event event) {
    IEventService eventService = this.mapEvent.get(event.nameEvent());
    if (Objects.isNull(eventService)) {
      throw new IllegalArgumentException(
          String.format("this event %s is not supported", event.nameEvent()));
    }
    eventService.processEvent(event);
  }

  public void registerEvent(SystemEvent event, IEventService eventService) {
    this.mapEvent.put(event.nameEvent(), eventService);
  }
}
