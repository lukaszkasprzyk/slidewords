package pl.wordslides.store;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WordRepository extends CrudRepository<WordEntity, String> {

    Optional<WordEntity> findByKey(String key);
}
