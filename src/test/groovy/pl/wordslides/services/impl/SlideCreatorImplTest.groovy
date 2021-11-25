package pl.wordslides.services.impl

import io.vavr.collection.List
import pl.wordslides.data.Word
import pl.wordslides.services.PhraseSplitter
import pl.wordslides.services.SlideCreator
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class SlideCreatorImplTest extends Specification {

    SlideCreator underTest = new SlideCreatorImpl()

    def "empty input empty result"() {
        expect:
        def slides = underTest.create(cW(""), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        depth || expectedSize || expectedKeys
        0     || 0            || []
    }

    def "null input empty result"() {
        expect:
        def slides = underTest.create(cW(null), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        depth || expectedSize || expectedKeys
        0     || 0            || []
    }

    def "to high or to low  depth"() {
        expect:
        def slides = underTest.create(cW("input"), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        depth || expectedSize || expectedKeys
        2     || 0            || []
        -1    || 0            || []
    }

    def "single word"() {
        expect:
        def slides = underTest.create(cW("input"), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        depth || expectedSize || expectedKeys
        0     || 1            || ["input"]
    }

    def "same word two times"() {
        expect:
        def slides = underTest.create(cW("input input"), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        depth || expectedSize || expectedKeys
        // 0     || 1            || ["input input"]
        1     || 1            || ["input"]
    }

    def "two different words"() {
        expect:
        def slides = underTest.create(cW("input text"), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        depth || expectedSize || expectedKeys
        0     || 1            || ["input text"]
        1     || 2            || ["input", "text"]

    }

    def "four different words"() {
        expect:
        def slides = underTest.create(cW(input), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        input                  | depth || expectedSize || expectedKeys
        "input text size test" | 3     || 4            || ["input", "text", "size", "test"]
        "input text size test" | 2     || 3            || ["input text", "text size", "size test"]
        "input text size test" | 1     || 2            || ["input text size", "text size test"]
        "input text size test" | 0     || 1            || ["input text size test"]
    }

    def "Check for Mary went Mary's gone"() {
        expect:
        def slides = underTest.create(cW(" Mary went Mary's\ngone "), depth)
        slides.size() == expectedSize
        slides.map { slide -> slide.key() }.toJavaArray() == expectedKeys
        where:
        depth || expectedSize || expectedKeys
        0     || 1            || ["Mary went Mary's gone"]
        1     || 2            || ["Mary went Mary's", "went Mary's gone"]
        2     || 3            || ["Mary went", "went Mary's", "Mary's gone"]
        3     || 4            || ["Mary", "went", "Mary's", "gone"]
    }

    private static List<Word> cW(String input) {
        return new PhraseSplitter().getWords(input)
    }

}
