package pl.wordslides.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class SearchControllerTest extends Specification {

    @Autowired
    private SearchController controller

    def "return key with counter for given input"() {
        given:
        def inputSearch = "Mary"
        when:
        def searchResult = controller.doSearch(inputSearch)
        then:
        searchResult.size() == 1
        searchResult.get(inputSearch) == 1
    }

    def "return key with counter for null"() {
        given:
        def inputSearch = null
        when:
        def searchResult = controller.doSearch(inputSearch)
        then:
        searchResult.size() == 0
    }


    def "return key with counter for long input"() {
        given:
        def inputSearch = "Mary ".repeat(100)
        when:
        def searchResult = controller.doSearch(inputSearch)
        then:
        searchResult.size() == 1
        searchResult.get("Mary") == 1
    }
}
