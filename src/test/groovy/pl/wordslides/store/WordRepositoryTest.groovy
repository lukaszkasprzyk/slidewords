package pl.wordslides.store


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import spock.lang.Specification

@DataJpaTest
class WordRepositoryTest extends Specification {

    @Autowired
    private TestEntityManager entityManager
    @Autowired
    private WordRepository repository

    def "should find by key when exists"() {
        given:
        WordEntity wordEntity = new WordEntity("first", 1)
        entityManager.persist(wordEntity)

        when:
        Optional<WordEntity> word = repository.findByKey("first")

        then:
        word.isPresent()
    }

    def "shouldn't find by key when no exists"() {
        given:
        WordEntity wordEntity = new WordEntity("second", 1)
        entityManager.persist(wordEntity)

        when:
        Optional<WordEntity> word = repository.findByKey("first")

        then:
        word.isEmpty()
    }
}
