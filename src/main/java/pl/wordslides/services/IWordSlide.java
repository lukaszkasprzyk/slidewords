package pl.wordslides.services;

import java.util.Map;

public interface IWordSlide {

    Map<String, Long> search(String input);
}
