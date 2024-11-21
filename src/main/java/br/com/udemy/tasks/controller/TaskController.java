package br.com.udemy.tasks.controller;

import br.com.udemy.tasks.controller.converter.TaskDTOConverter;
import br.com.udemy.tasks.controller.dto.TaskDTO;
import br.com.udemy.tasks.model.Task;
import br.com.udemy.tasks.service.TaskService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;
    private final TaskDTOConverter convert;

    public TaskController(TaskService service, TaskDTOConverter convert) {
        this.service = service;
        this.convert = convert;
    }

    @GetMapping
    public Mono<List<TaskDTO>> getTasks() {
        return service.list()
                .map(convert::convertList);
    }

    @PostMapping
    public Mono<TaskDTO> createTask(@RequestBody  Task task) {
        return service.insert(task)
                .map(convert::converter);
    }

}
