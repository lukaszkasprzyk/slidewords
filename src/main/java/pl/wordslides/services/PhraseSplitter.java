package pl.wordslides.services;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.wordslides.data.Word;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class PhraseSplitter {

    private static final Pattern WHITE_SPACE = Pattern.compile("\\s+");

    public List<Word> getWords(String input) {
        if (StringUtils.hasText(input)) {
            return WHITE_SPACE.splitAsStream(input.trim()).map(Word::new).distinct().collect(Collectors.toList());
        } else return emptyList();
    }
}
