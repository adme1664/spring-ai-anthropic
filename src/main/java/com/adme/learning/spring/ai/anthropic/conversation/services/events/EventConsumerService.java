package com.adme.learning.spring.ai.anthropic.conversation.services.events;

import com.adme.learning.spring.ai.anthropic.conversation.models.Event;
import com.adme.learning.spring.ai.anthropic.conversation.services.RouteMessageService;
import org.springframework.stereotype.Service;

@Service
public class EventConsumerService {

  private final RouteMessageService routeMessageService;
  public EventConsumerService(final RouteMessageService routeMessageService) {
    this.routeMessageService = routeMessageService;
  }

  public void consumeEvent(final Event event)  {
    routeMessageService.routeEvent(event);
  }

}
