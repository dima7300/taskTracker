package org.taskTracker;


public class Subtask extends Task {


    @Override
    public void setName(String name) {

        super.setName(name);
    }

    @Override
    public void setDescription(Object description) {
        super.setDescription(description);
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public void setIdentificationNumber(int identificationNumber) {
        super.setIdentificationNumber(identificationNumber);
    }

    @Override
    public int getIdentificationNumber() {
        return super.getIdentificationNumber();
    }

    @Override
    public String getStatus() {
        return super.getStatus();
    }

    @Override
    public String toString() {
        return "    Подзадача №" + getIdentificationNumber() + ": " + getName() + '\n' +
                "   Описание: " + getDescription() + '\n' +
                "   Статус - " + getStatus() + "\n";
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
