package pl.edu.agh.tai;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;

public class PostTest {

    private List<Comment> comments;
    private Event event;
    private final LocalDateTime publishDate = LocalDateTime.now();
    private User admin;
    private Post post;
    private Comment comment1mock;
    private Comment comment2mock;

    @Before
    public void setUp() throws Exception {
        event = mock(Event.class);
        comments = new ArrayList<>();
        comment1mock = mock(Comment.class);
        comment2mock = mock(Comment.class);
        comments.add(comment1mock);
        comments.add(comment2mock);
        admin = mock(CustomUser.class);
        post = new Post(event, publishDate, comments, admin);
    }

    @Test
    public void addComment() throws Exception {
        //given
        Comment comment3mock = mock(Comment.class);

        //when
        post.addComment(comment3mock);

        //then
        assertThat(post.getComments()).containsExactly(comment1mock, comment2mock, comment3mock);
    }

    @Test
    public void deleteComment() throws Exception {
        //given

        //when
        post.deleteComment(comment1mock);

        //then
        assertThat(post.getComments()).containsExactly(comment2mock);
    }
}