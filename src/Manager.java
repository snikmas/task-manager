import java.util.HashMap;

public class Manager {
    public HashMap<Long, Task> tasks = new HashMap<Long, Task>();
    public HashMap<Long, Subtask> subtasks = new HashMap<>();
    public HashMap<Long, Epic> epics = new HashMap<>();

    int id = 1;

    // 1
    public void createTask(Task task){
        tasks.put(task.id, task);
        System.out.println("Task created!");

    }
    public void createTask(Subtask subtask){
        subtasks.put(subtask.id, subtask);
        System.out.println("Subtask created!");
    }

    public void createTask(Epic epic){
        epics.put(epic.id, epic);
        System.out.println("Epic created!");
    }

    // 2
    public void updateTask(Task task, long id){
        System.out.println("hihih updateTask");

    }


    // 3
    public void getTasks(){
        System.out.println("hiiiii get tasks");
    }

    // 4
    public void getById(long id){
        System.out.println("hihii getbyid");
        if(tasks.containsKey(id)){
            System.out.println("Task found!");
            System.out.println("Info:");
            System.out.println("Task ID: " + tasks.get(id));
            System.out.println("Name: " + subtasks.get(id));
            System.out.println("Description: " + subtasks.get(id).description);
            System.out.println("Status: " + subtasks.get(id).status);

        }

    }

    // 5
    public void deleteTasks(){
        System.out.println("hiiiii delete tasks");

    }



    //6
    public void deleteById(long id){
        System.out.println("hihih deltee");

    }


}

