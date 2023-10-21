package exercise.controller;

import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Post create(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping
    Iterable<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    Post index(@PathVariable Long id) {
        return postRepository.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @PutMapping(path = "/{id}")
    Post update(@RequestBody Post post, @PathVariable Long id) {
        Post postFromBase = postRepository
                                .findById(id)
                                .orElseThrow(
                                    () -> new ResourceNotFoundException("Post with id " + id + " not found"));

        postFromBase.setTitle(post.getTitle());
        postFromBase.setBody(post.getBody());
        return postRepository.save(postFromBase);
    }
    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) {
        postRepository.deleteById(id);
        commentRepository.deleteByPostId(id);
    }
}
// END
