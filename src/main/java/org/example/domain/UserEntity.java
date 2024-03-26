package org.example.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Предлагаю в постре все равно какие то данные хранить как и в монге. Только хз на сколько это будет сложно.
 */
@Data //=)))))))))))
@Entity
public class UserEntity {
    @Id
    private UUID id;

}
