package com.adme.learning.spring.ai.anthropic.conversation.services.events;

import com.adme.learning.spring.ai.anthropic.conversation.models.SystemEvent;
import com.adme.learning.spring.ai.anthropic.conversation.services.IEventService;
import com.adme.learning.spring.ai.anthropic.conversation.services.RouteMessageService;
import jakarta.annotation.PostConstruct;
import java.util.List;

public abstract class AbstractEventService implements IEventService {

  private final RouteMessageService routeMessageService;

  protected AbstractEventService(RouteMessageService routeMessageService) {
    this.routeMessageService = routeMessageService;
  }


  protected abstract List<SystemEvent> getSystemEvents();

  @PostConstruct
  private void init(){
    getSystemEvents().forEach(systemEvent -> routeMessageService.registerEvent(systemEvent,this));
  }

}
