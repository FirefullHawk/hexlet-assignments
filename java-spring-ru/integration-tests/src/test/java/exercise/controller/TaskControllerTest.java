package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// END
@SpringBootTest
@AutoConfigureMockMvc
// BEGIN
public class TaskControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ObjectMapper om;

    @Test
    public void testUpdate() throws Exception {
        var task = Instancio.of(Task.class)
                       .ignore(Select.field(Task::getId))
                       .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                       .supply(Select.field(Task::getTitle), () -> faker.lorem().paragraph())
                       .create();
        taskRepository.save(task);

        var data = new HashMap<>();
        data.put("title", "Clean the mess");
        data.put("description", "Do something!");

        var request = put("/tasks/" + task.getId())
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(om.writeValueAsString(data));

        mockMvc.perform(request)
            .andExpect(status().isOk());

        task = taskRepository.findById(task.getId()).get();
        assertThat(task.getTitle()).isEqualTo(("Clean the mess"));
    }

    @Test
    public void testCreate() throws Exception {
        var task = new Task();

        task.setTitle(faker.lorem().word());
        task.setDescription(faker.lorem().paragraph());

        var request = post("/tasks")
                          .contentType(MediaType.APPLICATION_JSON)
                          .content(om.writeValueAsString(task));

        mockMvc.perform(request)
            .andExpect(status().isCreated());

        var taskFromBase = taskRepository.findByTitle(task.getTitle()).get();
        assertThat(taskFromBase.getTitle()).isEqualTo((task.getTitle()));

    }
}
// END

