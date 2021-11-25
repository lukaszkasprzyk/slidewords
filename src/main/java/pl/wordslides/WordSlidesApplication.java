package pl.wordslides;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.wordslides.store.WordEntity;
import pl.wordslides.store.WordRepository;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootApplication
@Slf4j
public class WordSlidesApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordSlidesApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(WordRepository repository) {
        return (args) -> {
            // save a few words
            repository.save(new WordEntity("Mary", 1l));
            repository.save(new WordEntity("Mary gone", 2l));
            repository.save(new WordEntity("Mary's gone", 3l));
            repository.save(new WordEntity("went Mary's", 4l));
            repository.save(new WordEntity("went", 5l));
            repository.save(new WordEntity("input", 6l));

            IntStream.rangeClosed(1, 100).asLongStream().forEach(value ->
                    repository.save(new WordEntity("test" + value, value))
            );
            // fetch all words
            log.info("Words found with findAll():");
            log.info("-------------------------------");
            for (WordEntity words : repository.findAll()) {
                log.info(words.toString());
            }
            log.info("");

            // fetch an individual one
            Optional<WordEntity> word = repository.findByKey("Mary");
            log.info("Word found with findByKey(Word):");
            log.info("--------------------------------");
            log.info("Count found:{}", word.get());
            log.info("");
        };
    }
}
