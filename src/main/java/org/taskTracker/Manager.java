package org.taskTracker;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Manager {
    private Scanner scanner = new Scanner(System.in);
    private HashMap<Object, Object> taskTable = new HashMap<>();
    private HashMap<Object, Object> subTaskTable = new HashMap<>();
    private HashMap<Object, Object> epicTaskTable = new HashMap<>();
    private int number = 0;

    private void printTaskTable() {      // Вывод списка задач +
        for (Map.Entry<Object, Object> entry : taskTable.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private void printSubtaskTable() {     // Вывод списка подзадач +
        for (Map.Entry<Object, Object> entry : subTaskTable.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private void printEpicTable() {         // Вывод списка эпиков +
        for (Map.Entry<Object, Object> entry : epicTaskTable.entrySet()) {
            System.out.println(entry.getValue());
        }
    }

    private Object createTask() {    // создание задачи +
        Task task = new Task();
        number++;
        System.out.println("Напишите название задачи");
        task.setName(scanner.nextLine());
        System.out.println("Напишите описание");
        task.setDescription(scanner.nextLine());
        task.setIdentificationNumber(number);
        taskTable.put(number, task);
        return task;
    }

    private Object createSubtask() {    // создание подзадачи +  //поменял на поле класса
        Subtask subtask = new Subtask();
        number++;
        System.out.println("Название подзадачи");
        subtask.setName(scanner.nextLine());
        System.out.println("Напишите описание");
        subtask.setDescription(scanner.nextLine());
        subtask.setIdentificationNumber(number);
        subTaskTable.put(number, subtask);
        return subtask;
    }

    private void createEpic() {     // создание эпика
        Epic epic = new Epic();
        List<Object> xxx = new ArrayList<>();
        System.out.println("Название эпика");
        epic.setName(scanner.nextLine());
        while (true) {
            String num;
            System.out.println("1 - Добавит подзадачу\n" +
                    "2 - Skip");
            num = scanner.nextLine();
            if (num.equals("1")) {
                createSubtask();
                xxx.add(subTaskTable.get(number));
            } else if (num.equals("2")) {
                break;
            } else {
                System.out.println("неправильный ввод");
            }
        }
        number++;
        epic.setDescription(xxx);
        epic.setIdentificationNumber(number);
        epicTaskTable.put(number, epic);
//        return epic;
    }

    private Task returnTaskIdentification() { // получение задачи по идентификатору +
        int num;
        while (true) {
            System.out.println("Введите идентификационный номер задачи");
            num = scanner.nextInt();
            if (taskTable.get(num) != null) {
                break;
            } else {
                System.out.println("Задачи с таким номером нет");
            }
        }
        return (Task) taskTable.get(num);
    }

    private Subtask returnSubtaskIdentification() { // получение подзадачи по идентификатору +
        int num;
        while (true) {
            System.out.println("Введите идентификационный номер подзадачи");
            num = scanner.nextInt();
            if (subTaskTable.get(num) != null) {
                break;
            } else {
                System.out.println("Задачи с таким номером нет");
                return null;
            }
        }
        return (Subtask) subTaskTable.get(num);
    }

    private Epic returnEpicIdentification() {   // получение эпика по идентификатору +
        int num;
        while (true) {
            System.out.println("Введите идентификационный номер эпика");
            num = scanner.nextInt();
            if (epicTaskTable.get(num) != null) {
                break;
            } else {
                System.out.println("Задачи с таким номером нет");
                return null;
            }
        }
        return (Epic) epicTaskTable.get(num);
    }


    private void removeTask() {     // удаление всех задач +
        taskTable.clear();
        System.out.println("все задачи удалены");
    }

    private void removeSubTask(Subtask subtask) {     //удаление подзадач по идентификатору +
        subTaskTable.values().remove(subtask);
        for (Map.Entry<Object, Object> entry : epicTaskTable.entrySet()) {
            Epic epic = (Epic) epicTaskTable.get(entry.getKey());
            Object[] object = ((Collection<?>) epic.getDescription()).toArray();
            for (Object x : object) {
                if (x.equals(subtask)) {
                    epic.setDescription(Arrays.stream(object).filter(y -> !y.equals(subtask)).collect(Collectors.toList()));
                    break;
                }
            }
        }
    }

    private void removeSubTaskInEpic(Epic epic) {     // удаление всех подзадач в эпике +
        subTaskTable.values().removeAll((Collection<?>) epic.getDescription());
        epic.setDescription(null);
        System.out.println("все подзадачи удалены");
    }

    private void removeEpic() {     // удаление всех эпиков +
        epicTaskTable.clear();
        subTaskTable.clear();
        System.out.println(" эпик удален");
    }

    private void removeIdentificationEpic(Epic epic) { //удаление эпика по индексу +
        epicTaskTable.values().remove(epic);
        subTaskTable.values().removeAll((Collection<?>) epic.getDescription());
    }

    private void updateTask(Task task) {   // обновление задачи
        String num;
        scanner.nextLine();
        task.setStatus("IN_PROGRESS");
        while (true) {
            System.out.println("Выберете нужное \n" +
                    "1 - Изменить имя \n" +
                    "2 - Изменить описание \n" +
                    "3 - Сохранить и выйти в меню\n" +
                    "4 - Завершить задачу");
            num = scanner.nextLine();
            if (num.equals("1")) {
                System.out.println("Напишите новое имя ");
                task.setName(scanner.nextLine());
            } else if (num.equals("2")) {
                System.out.println("Напишите новое описание");
                task.setDescription(scanner.nextLine());
            } else if (num.equals("3")) {
                break;
            } else if (num.equals("4")) {
                task.setStatus("DONE");
                break;
            } else {
                System.out.println("Неверный ввод");
            }
        }
        taskTable.put(task.getIdentificationNumber(), task);
    }

    private Object updateSubtask(Subtask subtask) {     //  обновление подзадачи
        String num;
        scanner.nextLine();
        subtask.setStatus("IN_PROGRESS");
        while (true) {
            System.out.println("Выберете нужное \n" +
                    "1 - Изменить имя \n" +
                    "2 - Изменить описание \n" +
                    "3 - Сохранить и выйти в меню\n" +
                    "4 - Завершить подзадачу");
            num = scanner.nextLine();
            if (num.equals("1")) {
                System.out.println("Напишите новое имя ");
                subtask.setName(scanner.nextLine());
            } else if (num.equals("2")) {
                System.out.println("Напишите новое описание");
                subtask.setDescription(scanner.nextLine());
            } else if (num.equals("3")) {
                break;
            } else if (num.equals("4")) {
                subtask.setStatus("DONE");
                break;
            } else {
                System.out.println("Неверный ввод");
            }
        }
        return subTaskTable.put(subtask.getIdentificationNumber(), subtask);
    }

    private void updateEpic(Epic epic) {   // обновление эпика
        String num;
        scanner.nextLine();
        while (true) {
            System.out.println("Выберете нужное \n" +
                    "1 - Изменить имя \n" +
                    "2 - Изменить подзадачи \n" +
                    "3 - Сохранить и выйти в меню\n" +
                    "4 - Добавить подзадачу\n");
//            scanner.nextLine();
            num = scanner.nextLine();
            if (num.equals("1")) {
                System.out.println("Напишите новое имя ");
                epic.setName(scanner.nextLine());
            } else if (num.equals("2")) {
                System.out.println(epic.getDescription());
                Subtask subtask = returnSubtaskIdentification();
                Object[] object2 = ((Collection<?>) epic.getDescription()).toArray();
                for (Object i : object2) {
                    if (i.equals(subtask)) {
                        i = updateSubtask(subtask);
                        System.out.println(i);
                    }
                }
            } else if (num.equals("3")) {
                break;
            } else if (num.equals("4")) {
                break; // дописать метод для добавления подзадачи
            } else {
                System.out.println("Неверный ввод");
            }
        }
        taskTable.put(epic.getIdentificationNumber(), epic);
    }

    private void epicStatusUpdate() {
        Epic epic;
        Subtask subtask;
        for (Map.Entry<Object, Object> entry : epicTaskTable.entrySet()) {
            epic = (Epic) entry.getValue();
            Object[] objects = ((Collection<?>) epic.getDescription()).toArray();
            int u = 0;
            for (Object x : objects) {
                subtask = (Subtask) x;
                if (subtask.getStatus().equals("IN_PROGRESS")) {
                    epic.setStatus("IN_PROGRESS");
                }
                if (subtask.getStatus().equals("DONE")) {
                    u++;
                }
            }
            if (u == objects.length) {
                epic.setStatus("DONE");
            }
        }
    }

    public void start() {
        System.out.println("Выберете из Меню");
        while (true) {
            epicStatusUpdate();
            System.out.println("1 - создать Задачу \n" +
                    "2 - Вывести все задачи \n" +
                    "3 - Получить задачу по ID \n" +
                    "4 - Удалить задачу по ID \n" +
                    "5 - Удалить все задачи \n" +
                    "6 - Обновить задачу \n" +
                    "7 - Выход");
            String num;
            num = scanner.nextLine();
            if (num.equals("1")) {
                System.out.println("Выберите какую задачу создать");
                while (true) {
                    System.out.println("1 - Обычная задача \n" +
                            "2 - Эпик задача");
                    num = scanner.nextLine();
                    if (num.equals("1")) {
                        createTask();
                        System.out.println("Задача создана\n");
                        break;
                    } else if (num.equals("2")) {
                        createEpic();
                        System.out.println("Эпик создан\n");
                        break;
                    } else {
                        System.out.println("Неправильный ввод\n");
                    }
                }
            } else if (num.equals("2")) {
                System.out.println("        ЗАДАЧИ      ");
                printTaskTable();
                System.out.println("\n      Эпики       ");
                printEpicTable();
            } else if (num.equals("3")) {
                while (true) {
                    System.out.println("Какую задачу вы хотите найти? \n" +
                            "1 - Задача \n" +
                            "2 - Эпик \n" +
                            "3 - Подзадача");
                    num = scanner.next();
                    if (num.equals("1")) {
                        if (taskTable.size() == 0) {
                            System.out.println("Задач нет");
                            scanner.nextLine();
                        } else {
                            System.out.println(returnTaskIdentification());
                            scanner.nextLine();
                        }
                        break;
                    } else if (num.equals("2")) {
                        System.out.println(returnEpicIdentification());
                        scanner.nextLine();
                        break;
                    } else if (num.equals("3")) {
                        System.out.println(returnSubtaskIdentification());
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Неправильный ввод\n");
                    }
                }
            } else if (num.equals("4")) {
                while (true) {
                    System.out.println("Какую задачу вы хотите Удалить? \n" +
                            "1 - Задача \n" +
                            "2 - Эпик \n" +
                            "3 - Подзадача");
                    num = scanner.nextLine();
                    if (num.equals("1")) {
                        taskTable.values().remove(returnTaskIdentification());
                        System.out.println("Задача удалена\n");
                        scanner.nextLine();
                        break;
                    } else if (num.equals("2")) {
                        removeIdentificationEpic(returnEpicIdentification());
                        System.out.println("Эпик удален\n");
                        scanner.nextLine();
                        break;
                    } else if (num.equals("3")) {
                        removeSubTask(returnSubtaskIdentification());
                        System.out.println("Подзадача удалена\n");
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Неправильный ввод\n");
                    }
                }
            } else if (num.equals("5")) {
                while (true) {
                    System.out.println("Выберите нужное\n" +
                            "1 - Удалить все задачи\n" +
                            "2 - Удалить все Эпики\n" +
                            "3 - Удалить все подзадачи определенного Эпика");
                    num = scanner.nextLine();
                    if (num.equals("1")) {
                        removeTask();
                        break;
                    } else if (num.equals("2")) {
                        removeEpic();
                        break;
                    } else if (num.equals("3")) {
                        removeSubTaskInEpic(returnEpicIdentification());
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Неправильный ввод");
                    }
                }
            } else if (num.equals("6")) {
                while (true) {
                    System.out.println("Выберете нужное\n" +
                            "1 - Обновить Эпик\n" +
                            "2 - Обновить Задачу");
                    num = scanner.nextLine();
                    if (num.equals("1")) {
                        updateEpic(returnEpicIdentification());
//                        scanner.nextLine();
                        break;
                    } else if (num.equals("2")) {
                        updateTask(returnTaskIdentification());
//                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Неправильный ввод");
                    }
                }
            } else if (num.equals("7")) {
                System.out.println("\n Выход \n");
                break;
            } else {
                System.out.println("Неверный ввод");
            }
        }
    }
}

/*
- вроде все готово!!!!
- сделать метод для добавления задачи
- получение задач по идетификатору разобраться с выполнение кода вместо int сделать String через ParseInt()
 */