package pl.wordslides.services;

import io.vavr.collection.List;
import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;

public interface SlideCreator {

    List<Slide> create(List<Word> words, int depth);

}
