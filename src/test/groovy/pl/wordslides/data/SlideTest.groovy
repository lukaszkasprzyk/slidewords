package pl.wordslides.data

import io.vavr.collection.List
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SlideTest extends Specification {

    def "slide with empty list of worlds should return not null slide"() {
        when:
        def result = new Slide(List.empty())
        then:
        result.size() == 0
        result.key() == ""
    }

    def "multiple word should pass"() {
        when:
        def word1 = new Word("test1")
        def word2 = new Word("test2")
        def word3 = new Word("test3")
        def result = new Slide(List.of(word1, word2, word3))
        then:
        result.size() == 3
        result.key() == "test1 test2 test3"
    }

    def "null input cause exception"() {
        when:
        new Slide(null)
        then:
        thrown NullPointerException
    }


}
