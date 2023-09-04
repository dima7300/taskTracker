package org.taskTracker;


public class Task extends Object {
    private String name;
    private Object description;
    private int identificationNumber;
    private String status;

    public Task() {
        this.name = "";
        this.description = "";
        this.identificationNumber = 0;
        this.status = "NEW";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(int identificationNumber) {
        this.identificationNumber = identificationNumber;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Задача №" + identificationNumber + ": " + name + '\n' +
                "Описание: " + description + '\n' +
                "Статус - " + status;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}


