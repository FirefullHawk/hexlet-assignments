package exercise.controller;

import exercise.model.Comment;
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

import exercise.repository.CommentRepository;
import exercise.exception.ResourceNotFoundException;

// BEGIN
@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentRepository commentRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Comment create(@RequestBody Comment comment) {
        return commentRepository.save(comment);
    }

    @GetMapping
    Iterable<Comment> index() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    Comment index(@PathVariable Long id) {
        return commentRepository.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("Comment with id " + id + " not found"));
    }

    @PutMapping(path = "/{id}")
    Comment update(@RequestBody Comment comment, @PathVariable Long id) {
        Comment commentFromBase = commentRepository
                                .findById(id)
                                .orElseThrow(
                                    () -> new ResourceNotFoundException("Comment with id " + id + " not found"));

        commentFromBase.setBody(comment.getBody());
        return commentRepository.save(commentFromBase);
    }
    @DeleteMapping(path = "/{id}")
    void delete(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
}
// END
