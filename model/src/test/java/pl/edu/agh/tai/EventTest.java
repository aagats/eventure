package pl.edu.agh.tai;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;

public class EventTest {

    private List<User> observators;
    private User user1mock;
    private User user2mock;
    private Event event;
    private Place location;
    private Category category;
    private Set<Category> categories;

    @Before
    public void setUp() throws Exception {
        observators = new ArrayList<>();
        user1mock = mock(CustomUser.class);
        user2mock = mock(CustomUser.class);
        observators.add(user1mock);
        observators.add(user2mock);
        location = mock(Place.class);
        categories = new HashSet<>();
        categories.add(Category.ART);
        event = new Event(456321, "watermelon", location, categories, true);
        event.setObservators(observators);
    }

    @Test
    public void addObservator() throws Exception {
        //given
        User user3mock = mock(CustomUser.class);

        //when
        event.addObservator(user3mock);

        //then
        assertThat(event.getObservators()).containsExactly(user1mock, user2mock, user3mock);
    }

    @Test
    public void deleteObservator() throws Exception {
        //given

        //when
        event.deleteObservator(user1mock);

        //then
        assertThat(event.getObservators()).containsExactly(user2mock);
    }
}