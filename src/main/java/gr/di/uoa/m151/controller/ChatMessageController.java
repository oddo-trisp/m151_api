package gr.di.uoa.m151.controller;

import gr.di.uoa.m151.entity.ChatMessage;
import gr.di.uoa.m151.service.ChatMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatMessageController {

    final private ChatMessageServiceImpl chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageServiceImpl chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @RequestMapping(value = "/findMessagesOnConversation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ChatMessage> findMessagesOnConversation(@RequestParam String senderId, @RequestParam String receiverId) {
        return chatMessageService.findMessagesOnConversation(Long.valueOf(senderId), Long.valueOf(receiverId));
    }

    @RequestMapping(value = "/saveConversation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String findMessagesOnConversation(@RequestBody List<ChatMessage> conversation) {
        return chatMessageService.saveConversation(conversation) != null ? "SUCCESS" : "FAILURE";
    }
}
