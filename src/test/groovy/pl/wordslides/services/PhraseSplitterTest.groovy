package pl.wordslides.services


import spock.lang.Specification
import spock.lang.Unroll

import java.util.stream.Collectors

@Unroll
class PhraseSplitterTest extends Specification {

    PhraseSplitter underTest = new PhraseSplitter()

    def "input #input have words #result"() {
        def words = underTest.getWords(input)
        def array = words.stream().map { word -> word.getValue() }.collect(Collectors.toList())
        expect:
        array == result

        where:
        input                       || result
        ""                          || []
        " \n\t\f\r"                 || []
        null                        || []
        "input"                     || ["input"]
        "input test"                || ["input", "test"]
        "input\ntest"               || ["input", "test"]
        "input\ttest"               || ["input", "test"]
        "input\ftest"               || ["input", "test"]
        "input\rtest"               || ["input", "test"]
        " input test "              || ["input", "test"]
        " input             test "  || ["input", "test"]
        "\n\t\f\rinput  test\n\t\f" || ["input", "test"]
    }
}
