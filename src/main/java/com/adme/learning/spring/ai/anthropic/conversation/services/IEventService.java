package com.adme.learning.spring.ai.anthropic.conversation.services;

import com.adme.learning.spring.ai.anthropic.conversation.models.Event;

public interface IEventService {
  void processEvent(Event event);
}
