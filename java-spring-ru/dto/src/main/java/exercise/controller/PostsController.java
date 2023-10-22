package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.stream.Collectors;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
@RequestMapping("/posts")
public class PostsController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    Iterable<PostDTO> index() {
        return postRepository.findAll().stream().map(this::toPostDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    PostDTO index(@PathVariable Long id) {
        Post post = postRepository
                        .findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));

        return toPostDTO(post);
    }

    private PostDTO toPostDTO(Post post) {
        var postDTO = new PostDTO();

        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setComments(commentRepository
                                .findByPostId(post.getId())
                                .stream()
                                .map(this::toCommentDTO)
                                .collect(Collectors.toList()));

        return postDTO;
    }

    private CommentDTO toCommentDTO(Comment comment) {
        var commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setBody(comment.getBody());

        return commentDTO;
    }
}
// END
