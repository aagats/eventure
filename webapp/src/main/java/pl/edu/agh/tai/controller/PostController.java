package pl.edu.agh.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.tai.model.Event;
import pl.edu.agh.tai.model.Place;
import pl.edu.agh.tai.model.Post;
import pl.edu.agh.tai.persistence.PostDao;
import pl.edu.agh.tai.persistence.dtos.PostDto;
import pl.edu.agh.tai.persistence.entitites.PostEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostDao postDao;

    @RequestMapping(path="api/posts/{id}", method = RequestMethod.GET)
    public Post showPost(@PathVariable(value="id") long id) {
        return null;
    }

    @RequestMapping(path="api/posts", method = RequestMethod.GET)
    public List<Post> showPosts() throws IOException {
        return null;
    }

    @RequestMapping(path ="api/posts", method = RequestMethod.POST)
    public Post addPost(PostDto post) {
        return null;
    }
}
