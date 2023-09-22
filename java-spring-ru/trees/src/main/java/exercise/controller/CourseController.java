package exercise.controller;

import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCorses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(path = "/{id}/previous")
    public Iterable<Course> getPath(@PathVariable long id) {
        var path = courseRepository.findById(id).getPath();

        var splitPath = path == null? null : path.split("\\.");

        List<Course> response = new ArrayList<>();

        if (splitPath == null) {
            return response;
        }

        Arrays.stream(splitPath)
                .forEach(courseId -> response.add(courseRepository.findById(Integer.parseInt(courseId))));

        return response;
    }
    // END

}
