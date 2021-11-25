package pl.wordslides.services.impl;

import io.vavr.collection.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;
import pl.wordslides.services.IWordSlide;
import pl.wordslides.services.PhraseSplitter;
import pl.wordslides.services.SlideCreator;
import pl.wordslides.store.WordRepository;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class WordSlide implements IWordSlide {

    private final SlideCreator slideCreator;
    private final WordRepository wordRepository;
    private final PhraseSplitter phraseSplitter;

    @Override
    public Map<String, Integer> search(String input) {
        Map<String, Integer> result = new HashMap<>();
        final List<Word> wordsToUseInSearch = phraseSplitter.getWords(input);

        for (int depth = 0; depth <= wordsToUseInSearch.size() - 1; depth++) {
            final List<Slide> slides = slideCreator.create(wordsToUseInSearch, depth);
            slides.filter(this::isSlideNotVisited).forEach(slide -> {
                final String key = slide.key();
                wordRepository.findByKey(key).ifPresent(wordEntity -> {
                    result.put(key, wordEntity.getCount());
                    markSlideAsVisited(slide);
                });
            });
        }

        return result;
    }

    private void markSlideAsVisited(Slide slide) {
        slide.getWords().forEach(Word::setVisited);
    }

    private boolean isSlideNotVisited(Slide slide) {
        return !slide.getWords().exists(Word::isVisited);
    }

}
