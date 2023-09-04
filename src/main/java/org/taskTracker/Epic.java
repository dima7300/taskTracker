package org.taskTracker;

public class Epic extends Task {
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Object getDescription() {
        return super.getDescription();
    }

    @Override
    public void setDescription(Object description) {
        super.setDescription(description);
    }

    @Override
    public int getIdentificationNumber() {
        return super.getIdentificationNumber();
    }

    @Override
    public void setIdentificationNumber(int identificationNumber) {
        super.setIdentificationNumber(identificationNumber);
    }

    @Override
    public void setStatus(String status) {
        super.setStatus(status);
    }

    @Override
    public String getStatus() {
        return super.getStatus();
    }

    @Override
    public String toString() {
        return "Эпик №" + getIdentificationNumber() + ": " + getName() + '\n' +
                "Описание { \n " + getDescription() + " } \n" +
                "Статус - " + getStatus() +'\n';
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
