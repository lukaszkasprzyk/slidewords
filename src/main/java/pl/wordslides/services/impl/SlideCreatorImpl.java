package pl.wordslides.services.impl;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;
import pl.wordslides.services.SlideCreator;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class SlideCreatorImpl implements SlideCreator {

    public List<Slide> create(List<Word> words, int depth) {
        if (isAnythingToSplit(words, depth)) {
            return List.empty();
        } else if (depth == 0) {
            return List.of(new Slide(words));
        } else {
            int sliceSize = words.size() - depth;
            return extract(words, sliceSize);
        }
    }

    private List<Slide> extract(List<Word> words, int sliceSize) {
        Set<Slide> slides = new LinkedHashSet<>();
        for (int padFromLeft = 0; padFromLeft <= words.size() - sliceSize; padFromLeft++) {
            Slide subSlide = extractSingleSlide(words, sliceSize, padFromLeft);
            slides.add(subSlide);
        }
        return List.ofAll(slides);
    }

    private Slide extractSingleSlide(List<Word> words, int sliceSize, int padFromLeft) {
        final List<Word> slice = words.subSequence(padFromLeft, sliceSize + padFromLeft);
        return new Slide(slice);
    }

    private boolean isAnythingToSplit(List<Word> words, int depth) {
        return words.isEmpty() || depth >= words.size() || depth < 0;
    }

}
