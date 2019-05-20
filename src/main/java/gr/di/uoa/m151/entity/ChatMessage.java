package gr.di.uoa.m151.entity;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "chat_message", schema = "public")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private AppUserShort sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private AppUserShort receiver;

    @Basic
    @Column(name = "creation_date")
    private Timestamp creationDate;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUserShort getSender() {
        return sender;
    }

    public void setSender(AppUserShort sender) {
        this.sender = sender;
    }

    public AppUserShort getReceiver() {
        return receiver;
    }

    public void setReceiver(AppUserShort receiver) {
        this.receiver = receiver;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
