import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

interface TaskManager {

    public List<Task> history = new ArrayList<>();
    public HashMap<Long, Task> tasks = new HashMap<Long, Task>();
    public HashMap<Long, Subtask> subtasks = new HashMap<>();
    public HashMap<Long, Epic> epics = new HashMap<>();

    Scanner scanner = new Scanner(System.in);
;

    //  CREATE TASK: TASK
    public void createTask(Task task);

    // CREATE TASK: SUBTASK
    public void createTask(Subtask subtask);

    // CREATE TASK: EPIC TASK
    public void createTask(Epic epic);

    // UPDATE TASK
    public void updateTask(Task task, long id);

    // UPDATE SUBTASK
    public void updateTask(Subtask subtask, long id);


    // UPDATE EPIC TASK
    public void updateTask(Epic epic, long id);


    // GET TASKS
    public void getTasks();

    // GET TASKS BY ID
    public void getById(long id);

    // DELETE
    public void deleteTasks();


    // DELETE BY DI
    public void deleteById(long id);

    // HISTORY: 10 LASTS
    public void history();


}

