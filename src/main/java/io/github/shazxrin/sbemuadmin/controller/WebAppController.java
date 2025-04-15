package io.github.shazxrin.sbemuadmin.controller;

import io.github.shazxrin.sbemuadmin.exception.NotFoundException;
import io.github.shazxrin.sbemuadmin.model.EntityType;
import io.github.shazxrin.sbemuadmin.model.Message;
import io.github.shazxrin.sbemuadmin.service.EntityService;
import io.github.shazxrin.sbemuadmin.service.MessageService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@RequestMapping("/")
@Controller
public class WebAppController {
    private final EntityService entityService;
    private final MessageService messageService;
    
    @RequestMapping
    public String index() {
        return "index";
    }

    @GetMapping("/entities")
    public String listEntities(Model model) {
        model.addAttribute("entities", entityService.getAllEntities());
        return "entities";
    }

    @GetMapping("/messages/{entityType}/{entityGroupId}/{entityId}")
    public String listMessages(
        Model model,
        @PathVariable EntityType entityType,
        @PathVariable String entityGroupId,
        @PathVariable long entityId,
        @RequestParam long startSequenceNumber,
        @RequestParam long endSequenceNumber
    ) {
        List<Message> messages = switch (entityType) {
            case QUEUE -> messageService.getMessagesInQueue(entityGroupId, entityId, startSequenceNumber, endSequenceNumber);
            case TOPIC -> messageService.getMessagesInTopic(entityGroupId, entityId, startSequenceNumber, endSequenceNumber);
            case SUBSCRIPTION -> messageService.getMessagesInSubscription(entityGroupId, entityId, startSequenceNumber, endSequenceNumber);
            default -> throw new NotFoundException("Unknown entity type!");
        };

        model.addAttribute("messages", messages);
        return "messages";
    }
}
