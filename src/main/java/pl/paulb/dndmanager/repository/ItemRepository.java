package pl.paulb.dndmanager.repository;

import org.springframework.data.repository.CrudRepository;
import pl.paulb.dndmanager.model.Item;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
