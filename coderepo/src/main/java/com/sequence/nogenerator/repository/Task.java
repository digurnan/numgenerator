package com.sequence.nogenerator.repository;

import javax.persistence.*;

@Entity
public class Task {

    public Task() {}

    public Task(
            String goal,
            String step,String uuid,String status, String output)
    {

        super();

        this.goal = goal;

        this.step = step;

        this.status = status;

        this.uuid = uuid;

        this.output = output;


    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="task_id")
    private Integer id;

    @Column(name = "goal", nullable = false)
    private String goal;

    @Column(name = "step", nullable = false)
    private String step;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "status", nullable = false)
    String status;


    @Column(name = "uuid", nullable = false)
    private String uuid;


    @Column(name = "output", nullable = false)
    private String output;

    // Overriding the toString method
    // to find all the values
    @Override
    public String toString()
    {
        return "Task [id="
                + id + ", goal="
                + goal + ", step="
                + step + "]";
    }

    // Getters and setters of
    // the properties
    public Integer getId()
    {

        return id;
    }

    public void setId(Integer id)
    {

        this.id = id;
    }
    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }


    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
