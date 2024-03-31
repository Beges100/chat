package org.example.example.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

@Document(collection = "chats")
@Getter
@Setter
public class ChatEntity {

    @Id
    private String id;

    @Field("users")
    private Set<UserEntity> users;

    @Field("messages")
    private List<Messages> messages;

}
