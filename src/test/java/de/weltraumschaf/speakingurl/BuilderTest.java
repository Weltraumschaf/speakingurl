package de.weltraumschaf.speakingurl;

import de.weltraumschaf.speakingurl.Slug.Builder;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

/**
 * Tests for {@link Slug.Builder}.
 * 
 * @author Sascha Droste <pid@posteo.net>
 * @author Sven Strittmatter <weltraumschaf@googlemail.com>
 */
public class BuilderTest {
    
    @Test
    public void newBuiler_alwaysNewInstance() {
        final Builder b1 = Slug.Builder.newBuiler();
        final Builder b2 = Slug.Builder.newBuiler();
        final Builder b3 = Slug.Builder.newBuiler();
        
        assertThat(b1, is(not(nullValue())));
        assertThat(b2, is(not(nullValue())));
        assertThat(b3, is(not(nullValue())));
        
        assertThat(b1, is(not(sameInstance(b2))));
        assertThat(b1, is(not(sameInstance(b3))));
        assertThat(b2, is(not(sameInstance(b1))));
        assertThat(b2, is(not(sameInstance(b3))));
        assertThat(b3, is(not(sameInstance(b1))));
        assertThat(b3, is(not(sameInstance(b2))));
    }
    
    @Test
    public void create_default() {
        final SlugImplementation slugger = (SlugImplementation)Slug.Builder.newBuiler().create();
        
        assertThat(slugger, is(not(nullValue())));
        assertThat(slugger.custom, is(equalTo(new String[0][0])));
        assertThat(slugger.lang, is(equalTo(Language.ENGLISH)));
        assertThat(slugger.maintainCase, is(equalTo(false)));
        assertThat(slugger.mark, is(equalTo(false)));
        assertThat(slugger.titleCase, is(equalTo(false)));
        assertThat(slugger.titleCaseExclude, is(equalTo(new String[0])));
        assertThat(slugger.truncate, is(equalTo(0)));
        assertThat(slugger.uric, is(equalTo(false)));
        assertThat(slugger.uricNoSlash, is(equalTo(false)));
    }
}
