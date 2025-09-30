import java.util.HashMap;
import java.util.Scanner;

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
        System.out.println("All Tasks:");
        for(Task task : tasks.values()){
            System.out.println("Task id: " + task.id);
            System.out.println("Task name: " + task.name);
            System.out.println("Task description: " + task.description);
            System.out.println("Task status: " + task.status + "\n");
        }

        System.out.println("---------------------\nAll Epics and their subtasks:");
        for(Epic epic : epics.values()){
            System.out.println("Epic id: " + epic.id);
            System.out.println("Epic name: " + epic.name);
            System.out.println("Epic description: " + epic.description);
            System.out.println("Epic status: " + epic.status + "\n");
            System.out.println("Epic's subtasks:");
            for(Subtask subtask : subtasks.values()){
                System.out.println("Subtask id: " + subtask.id);
                System.out.println("Subtask name: " + subtask.name);
                System.out.println("Subtask description: " + subtask.description);
                System.out.println("Subtask status: " + subtask.status + "\n---------------------");

            }
            System.out.println("\n");
        }

        System.out.println("Back to main menu...");

    }

    // 4
    public void getById(long id){
        if(tasks.containsKey(id)){
            Task task = tasks.get(id);
            System.out.println("Task found!");
            System.out.println("Info:");
            System.out.println("Task ID: " + task.id);
            System.out.println("Name: " + task.name);
            System.out.println("Description: " + task.description);
            System.out.println("Status: " + task.status);
        } else if(subtasks.containsKey(id)){
            Subtask subtask = subtasks.get(id);
            System.out.println("Subtask found!");
            System.out.println("Info:");
            System.out.println("Subtask ID: " + subtask.id);
            System.out.println("Name: " + subtask.name);
            System.out.println("Description: " + subtask.description);
            System.out.println("Status: " + subtask.status);
            System.out.println("Belongs to: " + epics.get(subtask.parentId).name);
        } else if(epics.containsKey(id)){
            Epic epic = epics.get(id);
            System.out.println("Epic found!");
            System.out.println("Info:");
            System.out.println("Epic ID: " + epic.id);
            System.out.println("Name: " + epic.name);
            System.out.println("Description: " + epic.description);
            System.out.println("Status: " + epic.status);
            System.out.println("Subtasks: ");
            for(Subtask subtask : subtasks.values()){
                System.out.println("Subtask ID: " + subtask.id);
                System.out.println("Name: " + subtask.name);
                System.out.println("Description: " + subtask.description);
                System.out.println("Status: " + subtask.status);
            }
        }

        System.out.println("Back to menu...");

    }

    // 5
    public void deleteTasks(){
        System.out.println("This function will delete all the tasks. Are you sure? Y/N");
        Scanner scanner = new Scanner(System.in);
        while(true){
            if(scanner.next().toLowerCase().equals("y")){
                tasks.clear();
                subtasks.clear();
                epics.clear();
                return;
            } else if (scanner.next().toLowerCase().equals("n")){
                System.out.println("Okay, back to main menu...");
                return ;
            } else {
                System.out.println("Invalid input! Please try again.");
            }
        }


    }


    //6
    public void deleteById(long id){
        if(tasks.containsKey(id)){
            System.out.println("Task found!");
            System.out.println("Info:");
            System.out.println("Task ID: " + tasks.get(id).id);
            System.out.println("Name: " + tasks.get(id).name);
            System.out.println("Description: " + tasks.get(id).description);
            System.out.println("Status: " + tasks.get(id).status);
            System.out.println("Removing...");
            tasks.remove(id);
        } else if(subtasks.containsKey(id)){
            System.out.println("Subtask found!");
            System.out.println("Info:");
            System.out.println("Subtask ID: " + subtasks.get(id).id);
            System.out.println("Name: " + subtasks.get(id).name);
            System.out.println("Description: " + subtasks.get(id).description);
            System.out.println("Status: " + subtasks.get(id).status);
            System.out.println("Removing...");
            subtasks.remove(id);
        } else if(epics.containsKey(id)){
            System.out.println("Epic found!");
            System.out.println("Info:");
            System.out.println("Epic ID: " + epics.get(id).id);
            System.out.println("Name: " + epics.get(id).name);
            System.out.println("Description: " + epics.get(id).description);
            System.out.println("Status: " + epics.get(id).status);
            System.out.println("Subtasks: ");
            for(Subtask subtask : subtasks.values()){
                System.out.println("Subtask ID: " + subtask.id);
                System.out.println("Name: " + subtask.name);
                System.out.println("Description: " + subtask.description);
                System.out.println("Status: " + subtask.status + "\n");
            }
            System.out.println("Removing...");
            epics.remove(id);
        } else {
            System.out.println("Invalid input! Back to menu...");
            return ;
        }

        System.out.println("Removed successfully!");
        System.out.println("Back to main menu...");

    }


}

