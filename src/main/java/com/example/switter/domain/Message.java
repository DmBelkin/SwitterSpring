package com.example.switter.domain;
import jakarta.persistence.*;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "please, fill the tag")
    @Length(max = 20, message = "Tag too long")
    @Column(name = "tag")
    private String tag;

    @NotBlank(message = "please, fill the message")
    @Length(max = 2048, message = "Message too long")
    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;


    public Message() {
    }

    public Message(String text, String tag, User user) {
        this.tag = tag;
        this.text = text;
        this.author = user;
    }

    public String getAuthorName() {
        return author != null? author.getUsername() : "<none>";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
