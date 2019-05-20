package gr.di.uoa.m151.repo;

import gr.di.uoa.m151.entity.AppUser;
import gr.di.uoa.m151.entity.ChatMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatMessageRepo extends CrudRepository<ChatMessage,Long> {

    @Query(value = "select * from chat_message cm\n" +
            "where (cm.sender_id = ?1 and cm.receiver_id = ?2)\n" +
            "   or (cm.sender_id = ?2 and cm.receiver_id = ?1)\n" +
            "order by cm.creation_date desc limit 100", nativeQuery = true)
    List<ChatMessage> findMessagesOnConversation(Long senderId, Long receiverId);

}
