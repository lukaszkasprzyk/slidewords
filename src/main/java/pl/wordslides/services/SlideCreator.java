package pl.wordslides.services;


import pl.wordslides.data.Slide;
import pl.wordslides.data.Word;

import java.util.List;

public interface SlideCreator {

    List<Slide> create(List<Word> words, int depth);

}
