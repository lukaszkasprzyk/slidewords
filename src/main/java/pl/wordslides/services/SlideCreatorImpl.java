package pl.wordslides.services;


import org.springframework.stereotype.Service;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SlideCreatorImpl implements SlideCreator {

    public List<Slide> create(List<Word> words, int depth) {
        if (isAnythingToSplit(words, depth)) {
            return Collections.emptyList();
        } else if (depth == 0) {
            return List.of(new Slide(words));
        } else {
            int sliceSize = words.size() - depth;
            return extract(words, sliceSize);
        }
    }

    private List<Slide> extract(List<Word> words, int sliceSize) {
        List<Slide> slides = new ArrayList<>();
        for (int padFromLeft = 0; padFromLeft <= words.size() - sliceSize; padFromLeft++) {
            Slide subSlide = extractSingleSlide(words, sliceSize, padFromLeft);
            slides.add(subSlide);
        }
        return slides;
    }

    private Slide extractSingleSlide(List<Word> words, int sliceSize, int padFromLeft) {
        final List<Word> slice = words.subList(padFromLeft, sliceSize + padFromLeft);
        return new Slide(slice);
    }

    private boolean isAnythingToSplit(List<Word> words, int depth) {
        return words.isEmpty() || depth >= words.size() || depth < 0;
    }

}
