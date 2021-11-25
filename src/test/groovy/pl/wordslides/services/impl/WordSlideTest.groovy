package pl.wordslides.services.impl

import io.vavr.collection.List
import pl.wordslides.store.WordEntity
import pl.wordslides.data.Slide
import pl.wordslides.data.Word
import pl.wordslides.services.IWordSlide
import pl.wordslides.services.PhraseSplitter
import pl.wordslides.services.SlideCreator
import pl.wordslides.store.WordRepository
import spock.lang.Specification

class WordSlideTest extends Specification {

    WordRepository wordRepository = Mock(WordRepository)
    SlideCreator slideCreator = Mock(SlideCreator)
    PhraseSplitter phraseSplitter = Mock(PhraseSplitter)

    IWordSlide underTest

    def setup() {
        underTest = new WordSlide(slideCreator, wordRepository, phraseSplitter)
    }

    def "for empty input empty result"() {
        def searchPhrase = ""
        given:
        phraseSplitter.getWords(searchPhrase) >> List.empty()
        when:
        def result = underTest.search(searchPhrase)
        then:
        result.size() == 0
    }

    def "single string input present in store"() {
        given:
        def searchPhrase = "input"
        wordRepository.findByKey(searchPhrase) >> Optional.of(new WordEntity(searchPhrase, 1))
        def words = List.of(new Word(searchPhrase))
        phraseSplitter.getWords(searchPhrase) >> words
        slideCreator.create(words, 0) >> List.of(new Slide(words))

        when:
        def result = underTest.search("input")
        then:
        result.size() == 1
    }

    def "multi string input partially present in store"() {
        given:
        def searchPhrase = "Mary went Mary's gone"
        wordRepository.findByKey("went Mary\'s") >> Optional.of(new WordEntity("went Mary\'s", 4))
        wordRepository.findByKey("Mary") >> Optional.of(new WordEntity("Mary", 1))
        wordRepository.findByKey(_) >> Optional.empty()

        def dWord = new Word("went Mary\'s")
        def singleWord = new Word("Mary")
        def words = List.of(dWord, singleWord)
        phraseSplitter.getWords(searchPhrase) >> words
        slideCreator.create(_, _) >> List.of(new Slide(List.of(singleWord)), new Slide(List.of(dWord)))

        when:
        def result = underTest.search(searchPhrase)
        then:
        result.size() == 2
        result == ["went Mary\'s": 4, "Mary": 1]
    }

/*    def "one thousand words repeated without match in store"() {
        given:
        def searchPhrase = "A MARRY".repeat(1000)
        wordRepository.findByKey(_) >> Optional.empty()
        phraseSplitter.getWords(searchPhrase) >> Lists
        when:
        def result = underTest.search(searchPhrase)
        then:
        result.size() == 0
    }*/

/*    def "one thousand words repeated existing in store"() {
        expect:
        def expected = ["Mary": 1]
        when:
        def result = underTest.search("Mary ".repeat(1000))
        then:
        result.size() == 1
        result == expected

    }*/
}
