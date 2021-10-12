package warehouseapp.warehouse.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
public class ProductDTO {
    @NotNull
    private String name;
    @NotNull
    private Integer categoryId, measurementId;
    @NotNull
    private List<UUID> attachmentIds;
}
