package com.adme.learning.spring.ai.anthropic.conversation.services.events;

import com.adme.learning.spring.ai.anthropic.conversation.models.Event;
import com.adme.learning.spring.ai.anthropic.conversation.models.SystemEvent;
import com.adme.learning.spring.ai.anthropic.conversation.services.RouteMessageService;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProcessAnthropicMessageService extends AbstractEventService {

  private final EventBuilderService eventBuilderService;
  private final AnthropicChatModel chatModel;
  private final EventSenderService eventSenderService;

  @Autowired
  protected ProcessAnthropicMessageService(
      RouteMessageService routeMessageService, EventBuilderService eventBuilderService,
      AnthropicChatModel chatModel, EventSenderService eventSenderService) {
    super(routeMessageService);
    this.eventBuilderService = eventBuilderService;
    this.chatModel = chatModel;
    this.eventSenderService = eventSenderService;
  }

  @Override
  protected List<SystemEvent> getSystemEvents() {
    return List.of(new SystemEvent("anthropic.ai.conversation.message"));
  }

  @Override
  public void processEvent(Event event) {
    log.info("OPEN AI talks: {}", event.content());
    Prompt prompt = new Prompt(event.content());
    Map<String, String> response = Map.of("response",
        this.chatModel.call(prompt).getResult().getOutput().getContent());
    log.info("Anthropic answers: {}", response.get("response"));
    Event receivedMessage = eventBuilderService.buildEvent(response);
    this.eventSenderService.send(receivedMessage);

  }
}
