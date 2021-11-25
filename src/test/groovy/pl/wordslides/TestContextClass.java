package pl.wordslides;

import org.springframework.boot.test.context.TestComponent;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import pl.wordslides.services.PhraseSplitter;
import pl.wordslides.services.impl.SlideCreatorImpl;


public class TestContextClass {

    @Bean
    public PhraseSplitter wordsFactory() {
        return new PhraseSplitter();
    }


    @Bean
    public SlideCreatorImpl creator() {
        return new SlideCreatorImpl();
    }



/*
    @Bean
    public WordSlide wordSlide() {
        return new WordSlideImpl(creator(), mockStore(), wordsFactory());
    }*/


}
