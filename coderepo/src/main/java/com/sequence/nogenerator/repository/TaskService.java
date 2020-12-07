package com.sequence.nogenerator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public ResponseTaskDTO saveTask(RequestTaskDTO requestTaskDTO) {
        ResponseTaskDTO responseTask  = new ResponseTaskDTO();
        UUID uuid=UUID.randomUUID(); //Generates random UUID
        String output = computeOutput(requestTaskDTO.getGoal(),requestTaskDTO.getStep());
        Task task = new Task(requestTaskDTO.getGoal(),requestTaskDTO.getStep(), uuid.toString(),"SUCCESS",output);
        taskRepository.save(task);
        responseTask.setTask(uuid.toString());
       return responseTask;
    }

    private String computeOutput(String goal, String step) {
        int goal1 = Integer.parseInt(goal);
        int step1 = Integer.parseInt(step);

        ArrayList<Integer> list = new ArrayList<>();
        while(goal1>=0) {
            list.add(goal1);
            goal1 = goal1 - step1;
        }
        return list.toString();
    }

    @Transactional
    public ResponseStatusDTO getTaskStatusByUUID(String uuid) {
        ResponseStatusDTO responseStatusDTO  = new ResponseStatusDTO();
        for (Task customer : taskRepository.findAll()) {
            System.out.println(customer.getStatus());
        }
        Task task = null;
        String status = "";
       // responseStatusDTO.setResult();
        return responseStatusDTO;
    }

    public ResponseOutputDTO getTaskNumList(String uuid) {
        ResponseOutputDTO responseStatusDTO  = new ResponseOutputDTO();
        List<String> resultData = new ArrayList<>();
        Optional<List<Task>> results = taskRepository.findByUuid(uuid);

        if(results.isPresent()) {
            List<Task> task_list = results.get();

            for(Task task1: task_list) {
                resultData.add(task1.getOutput());
            }
        }
        responseStatusDTO.setResult(resultData);
        return responseStatusDTO;
    }

    public ResponseTaskDTO saveTaskBulk(List<RequestTaskDTO> requestTaskDTO) {
        ResponseTaskDTO responseTask = new ResponseTaskDTO();
        UUID uuid = UUID.randomUUID(); //Generates random UUID
        for(RequestTaskDTO requestTaskDTO1 : requestTaskDTO){
            String output = computeOutput(requestTaskDTO1.getGoal(), requestTaskDTO1.getStep());
            Task task = new Task(requestTaskDTO1.getGoal(), requestTaskDTO1.getStep(), uuid.toString(), "SUCCESS", output);
            taskRepository.save(task);

        }
        responseTask.setTask(uuid.toString());
        return responseTask;
    }
}
