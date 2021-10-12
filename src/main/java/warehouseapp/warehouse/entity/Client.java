package warehouseapp.warehouse.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import warehouseapp.warehouse.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Client extends AbsNameEntity {
    @NotNull
    private String phoneNumber;
}
