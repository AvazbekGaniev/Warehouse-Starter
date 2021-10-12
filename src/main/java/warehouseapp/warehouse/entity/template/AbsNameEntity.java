package warehouseapp.warehouse.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbsNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    @NotNull
    @Column(nullable = false)
    private String name;
    @NotNull
    @Column(nullable = false)
    private boolean active = true;
//    private String nameRu;
//    private String nameEn;
}
