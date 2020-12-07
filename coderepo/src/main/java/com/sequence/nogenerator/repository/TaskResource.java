package com.sequence.nogenerator.repository;

import org.springframework.http.HttpStatus;
import org.springframework.http
        .ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/")
public class TaskResource {

    TaskService taskService;

    TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * {@code GET  /tasks/{uuid}/status} : gets the Task status for given UUID
     * @param uuid UUID of Task
     * @return Response Status DTO
     */
    @GetMapping("/tasks/{uuid}/status")
    public ResponseEntity<ResponseStatusDTO> getTaskStatus(@PathVariable("uuid") String uuid) {
        ResponseStatusDTO responseStatusDTO =  taskService.getTaskStatusByUUID(uuid);

        return new ResponseEntity<>(responseStatusDTO,HttpStatus.OK);
    }


    /**
     * {@code GET  /tasks/{uuid}} : gets the Task Result List
     * @param uuid
     * @return
     */
    @GetMapping("/tasks/{uuid}")
    public ResponseEntity<ResponseOutputDTO> getTaskNumList(@PathVariable("uuid") String uuid, @RequestParam("action") String action) {
        ResponseOutputDTO responseOutputDTO = null;
        if(action.equals(Constants.ACTION))
        {
             responseOutputDTO  = taskService.getTaskNumList(uuid);
        }

        return new ResponseEntity<>(responseOutputDTO,HttpStatus.OK);
    }


    /**
     * {@code POST  /generate} : generates a task.
     *
     * @param requestTaskDTO goal and step input for Task
     * @return responseTask with UUID
     */
    @PostMapping(
            path = "/generate",
            consumes = "application/json",
            produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public ResponseEntity<ResponseTaskDTO> addTask(
            @RequestBody RequestTaskDTO requestTaskDTO) {

        ResponseTaskDTO responseTaskDTO = taskService.saveTask(requestTaskDTO);
        return new ResponseEntity<>(responseTaskDTO, HttpStatus.ACCEPTED);
    }

    /**
     * {@code POST  /generate} : generates a task.
     *
     * @param requestTaskDTO goal and step input for Task
     * @return responseTask with UUID
     */
    @PostMapping(
            path = "/bulkGenerate",
            consumes = "application/json",
            produces = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)

    public ResponseEntity<ResponseTaskDTO> addTask(
            @RequestBody List<RequestTaskDTO> requestTaskDTO) {

        ResponseTaskDTO responseTaskDTO = taskService.saveTaskBulk(requestTaskDTO);
        return new ResponseEntity<>(responseTaskDTO, HttpStatus.ACCEPTED);
    }

}
