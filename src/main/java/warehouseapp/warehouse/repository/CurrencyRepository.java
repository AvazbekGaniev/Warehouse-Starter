package warehouseapp.warehouse.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import warehouseapp.warehouse.entity.Currency;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    @Override
    List<Currency> findAll();
}
