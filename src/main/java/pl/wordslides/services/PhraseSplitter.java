package pl.wordslides.services;

import io.vavr.collection.List;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.wordslides.data.Word;

import java.util.regex.Pattern;

@Service
public class PhraseSplitter {

    private static final Pattern WHITE_SPACE = Pattern.compile("\\s+");

    public List<Word> getWords(String input) {
        return StringUtils.hasText(input) ? List.ofAll(WHITE_SPACE.splitAsStream(input.trim()).map(Word::new)) : List.empty();

    }
}
