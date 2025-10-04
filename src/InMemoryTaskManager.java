import java.util.Scanner;

public class InMemoryTaskManager implements TaskManager {

    int userInput = 0;
    int id = 1;

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

    // UPDATE TASK
    public void updateTask(Task task, long id){
        System.out.println("New Name: ");
        String newName = scanner.next();
        if(!newName.isEmpty()) task.name = newName;

        System.out.println("New Description: ");
        String newDescription = scanner.next();
        if(!newDescription.isEmpty()) task.description = newDescription;

        System.out.println("New Status: [1] NEW\n[2] IN_PROGRESS\n[3] DONE");
        while(true){
            while(!scanner.hasNextInt()){
                System.out.println("Invalid user input! Try again.");
                scanner.next();
                continue;
            }
            userInput = scanner.nextInt();
            if(userInput > 3 || userInput < 0 ){
                System.out.println("Invalid user input! Try again.");
                continue;
            } else break;
        }
        switch(userInput){
            case 1 -> {
                if(task.status != Status.NEW) task.status = Status.DONE;
            }
            case 2 -> {
                if(task.status != Status.IN_PROGRESS) task.status = Status.DONE;
            }
            case 3 -> {
                if(task.status != Status.DONE) task.status = Status.IN_PROGRESS;
            }
        }
    }

    // UPDATE SUBTASK
    public void updateTask(Subtask subtask, long id){
        System.out.println("New Name: ");
        String newName = scanner.next();
        if(!newName.isEmpty()) subtask.name = newName;

        System.out.println("New Description: ");
        String newDescription = scanner.next();
        if(!newDescription.isEmpty()) subtask.description = newDescription;

        System.out.println("New Status: [1] NEW\n[2] IN_PROGRESS\n[3] DONE");
        while(true){
            while(!scanner.hasNextInt()){
                System.out.println("Invalid user input! Try again.");
                scanner.next();
                continue;
            }
            userInput = scanner.nextInt();
            if(userInput > 3 || userInput < 0 ){
                System.out.println("Invalid user input! Try again.");
                continue;
            } else break;
        }
        switch(userInput){
            case 1 -> {
                if(subtask.status != Status.NEW) subtask.status = Status.DONE;
                Helpers.recheckEpic(epics.get(subtask.parentId));
            }
            case 2 -> {
                if(subtask.status != Status.IN_PROGRESS) subtask.status = Status.DONE;
                Helpers.recheckEpic(epics.get(subtask.parentId));
            }
            case 3 -> {
                if(subtask.status != Status.DONE) subtask.status = Status.IN_PROGRESS;
                Helpers.recheckEpic(epics.get(subtask.parentId));
            }
        }

    };


    // UPDATE EPIC TASK
    public void updateTask(Epic epic, long id){


        System.out.println("If you don't wanna changes: just press Enter");
        System.out.println("New Name: ");
        String newName = scanner.next();
        if(!newName.isEmpty()) epic.name = newName;

        System.out.println("New Description: ");
        String newDescription = scanner.next();
        if(!newDescription.isEmpty()) epic.description = newDescription;

        System.out.println("New Status: [1] NEW\n[2] IN_PROGRESS\n[3] DONE");
        while(true){
            while(!scanner.hasNextInt()){
                System.out.println("Invalid user input! Try again.");
                scanner.next();
                continue;
            }
            userInput = scanner.nextInt();
            if(userInput > 3 || userInput < 0 ){
                System.out.println("Invalid user input! Try again.");
                continue;
            } else break;
        }
        switch(userInput) {
            case 1 -> epic.status = Status.DONE;
            case 2 -> epic.status = Status.DONE;
            case 3 -> epic.status = Status.IN_PROGRESS;
        }

    };

    public void getTasks(){
        {
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
    }

    public void getById(long id){
        {
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

    };

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


    };

    public void deleteById(long id){
        if(tasks.containsKey(id)){
            System.out.println("Task found!");
            System.out.println("Removing...");
            tasks.remove(id);
        } else if(subtasks.containsKey(id)){
            System.out.println("Subtask found!");
            System.out.println("Removing...");
            subtasks.remove(id);
        } else if(epics.containsKey(id)){
            System.out.println("Epic found!");
            System.out.println("Removing...");
            epics.remove(id);

            for(Subtask subtask : subtasks.values()){
                if(subtask.parentId == id){
                    subtasks.remove(id);
                }
            }
        } else {
            System.out.println("Invalid input! Back to menu...");
            return ;
        }

        System.out.println("Removed successfully!");
        System.out.println("Back to main menu...");

    }

    public void history(){
        System.out.println("History:");
        for(int i = 0; i < history.size(); i++){
            Task task = tasks.get(history.get(i).id);
            System.out.println("The #" + i);
            System.out.println("Task id: " + task.id);
            System.out.println("Task name: " + task.name);
            System.out.println("Task description: " + task.description);
            System.out.println("Task status: " + task.status);
            if(task instanceof Epic){
                System.out.println("Subtasks: ");
                if(subtasks.isEmpty()){
                    System.out.println("No subtasks found!");
                } else {
                    for(Subtask subtask : subtasks.values()){
                        System.out.println("Subtask id: " + subtask.id);
                        System.out.println("Name: " + subtask.name);
                        System.out.println("Description: " + subtask.description);
                        System.out.println("Status: " + subtask.status);
                }
            } else if (task instanceof Subtask){
                    if(task.parentId > 0){

                    }
                }

            }
            System.out.println("\n");

        }
    };


}
