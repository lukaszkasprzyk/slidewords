package pl.wordslides.data;


import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@ToString(includeFieldNames = false)
public class Slide {

    @NonNull
    private final List<Word> words;

    public int size() {
        return words.size();
    }

    public String key() {
        return this.words.stream().map(Word::getValue).collect(Collectors.joining(" "));
    }
}
