package warehouseapp.warehouse.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "uuid2")
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @NotNull
    private UUID id;

    @CreationTimestamp

    @NotNull
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

}
