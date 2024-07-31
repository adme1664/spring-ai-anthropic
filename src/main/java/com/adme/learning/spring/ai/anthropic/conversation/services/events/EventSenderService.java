package com.adme.learning.spring.ai.anthropic.conversation.services.events;

import com.adme.learning.spring.ai.anthropic.conversation.models.Event;
import java.util.Optional;
import java.util.concurrent.LinkedBlockingDeque;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventSenderService {

  private final LinkedBlockingDeque<Event> eventQueue;

  public EventSenderService() {
    this.eventQueue = new LinkedBlockingDeque<>();
  }

  public void send(Event event) {
    eventQueue.add(event);
  }

  public Message<Event> supplyEvent() {
    try {
      Thread.sleep(60000);
      return Optional.ofNullable(eventQueue.poll())
          .map(event -> {
            return MessageBuilder.withPayload(event)
                .setHeader("type", event.nameEvent())
                .build();
          }).orElse(null);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return null;
  }
}
