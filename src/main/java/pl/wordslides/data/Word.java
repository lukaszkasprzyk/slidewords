package pl.wordslides.data;

import lombok.*;

@Getter
@EqualsAndHashCode
@ToString(includeFieldNames = false)
@RequiredArgsConstructor
public class Word {

    @NonNull
    private final String value;
    private boolean visited;

    public void setVisited() {
        visited = true;
    }
}
