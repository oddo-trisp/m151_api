package gr.di.uoa.m151.service;

import gr.di.uoa.m151.entity.ChatMessage;
import gr.di.uoa.m151.repo.ChatMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageServiceImpl {

    final private ChatMessageRepo chatMessageRepo;

    @Autowired
    public ChatMessageServiceImpl(ChatMessageRepo chatMessageRepo) {
        this.chatMessageRepo = chatMessageRepo;
    }


    public List<ChatMessage> findMessagesOnConversation(Long senderId, Long receiverId){
        return chatMessageRepo.findMessagesOnConversation(senderId,receiverId);
    }

    public Iterable<ChatMessage> saveConversation(List<ChatMessage> conversation){
        return chatMessageRepo.saveAll(conversation);
    }

}
