package pl.wordslides.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.wordslides.services.IWordSlide;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final IWordSlide wordSlide;

    @GetMapping("/words/{input}")
    public Map<String, Integer> doSearch(@PathVariable String input) {
        return wordSlide.search(input);
    }
}
