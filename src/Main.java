import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        System.out.println("Welcome to TaskManager!");
        Scanner scanner = new Scanner(System.in);
        int userInput;

        String[] menu = {
                "[1] Create New Task",
                "[2] Update Task",
                "[3] Get All Tasks",
                "[4] Get a Task by Task ID",
                "[5] Delete All Tasks",
                "[6] Delete Task by Task ID",
                "[7] Get All Subtasks of an Epic",
                "[0] Exit"
        };

        while(true){
            System.out.println("\n=============================");
            System.out.println("=========== MENU ============");
            System.out.println("=============================");
            for(String menuItem : menu){
                System.out.println(menuItem);
            }
            System.out.println("===============================");


            while (true) {
                System.out.print(">> ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter an integer.");
                    scanner.nextLine(); // consume the invalid input
                    continue;
                }

                userInput = scanner.nextInt();

                if (userInput < 0 || userInput >= menu.length) {
                    System.out.println("Invalid input! Try again.");
                    continue;
                }

                break; // valid input
            }


            switch (userInput){
                case 1 -> {
                    System.out.println("Which kind of task would you like to Create?\n[1] Task\n[2] Epic\n[3] Subtask\n");

                    while(true){
                        while(!scanner.hasNextInt()){
                            System.out.println("Invalid user input! Try again.");
                            scanner.nextLine();
                        }
                        userInput = scanner.nextInt();
                        scanner.nextLine();
                        switch(userInput){
                            case 1 -> {
                                Task task = new Task();
                                System.out.print("Name: ");
                                task.name = scanner.nextLine();
                                System.out.print("Description: ");
                                task.description = scanner.nextLine();
                                task.status = Status.NEW;
                                task.id = manager.id;
                                manager.id++;

                                manager.createTask(task);
                            }
                            case 2 -> {
                                Epic epic = new Epic();
                                System.out.print("Name: ");
                                epic.name = scanner.nextLine();
                                System.out.print("Description: ");
                                epic.description = scanner.nextLine();
                                epic.status = Status.NEW;

                                epic.id = manager.id;
                                manager.id++;

                                System.out.println("How many subtasks would you like to create?");
                                while(true){
                                    while(!scanner.hasNextInt()){
                                        System.out.println("Invalid user input! Try again.");
                                        scanner.nextLine();
                                    }
                                    userInput = scanner.nextInt();
                                    if(userInput < 0){
                                        System.out.println("Invalid user input! Try again.");
                                    } else break;
                                }

                                // for buffer \n
                                scanner.nextLine();
                                for(int i = 0; i < userInput; i++){
                                    Subtask subtask = new Subtask();

                                    System.out.print("Name: ");
                                    subtask.name = scanner.nextLine();
                                    System.out.print("Description: ");
                                    subtask.description = scanner.nextLine();
                                    subtask.status = Status.NEW;
                                    subtask.id = manager.id;
                                    subtask.parentId = epic.id;

                                    epic.subtasks.put(subtask.id, subtask);
                                    manager.subtasks.put(subtask.id, subtask);
                                    manager.createTask(subtask);
                                }
                                manager.createTask(epic);
                            }
                            case 3 -> {
                                Subtask subtask = new Subtask();
                                System.out.print("Name: ");
                                subtask.name = scanner.nextLine();
                                System.out.print("Description: ");
                                subtask.description = scanner.nextLine();
                                subtask.status = Status.NEW;
                                subtask.id = manager.id;
                                manager.id++;

                                System.out.println("Input Epic ID to add the subtask:");
                                while (true) {
                                    while (!scanner.hasNextInt()) {
                                        System.out.println("Invalid user input! Try again.");
                                        scanner.nextLine();
                                    }
                                    userInput = scanner.nextInt();
                                    if (!manager.epics.containsKey((long)userInput)) {
                                        System.out.println("Invalid user input! Try again.");
                                    } else break;
                                }

                                scanner.nextLine();
                                manager.epics.get((long)userInput).subtasks.put(subtask.id, subtask);
                                manager.createTask(subtask);
                                Helpers.recheckEpic(manager.epics.get((long)userInput));

                                System.out.println("Added!");
                            }
                        }
                        break;
                    }
                    // auto break
                }
                case 2 -> {
                    System.out.println("Input a Task ID");
                    while (true) {
                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid user input! Try again.");
                            scanner.nextLine();
                        }
                        userInput = scanner.nextInt();
                        scanner.nextLine();
                    if (manager.tasks.containsKey((long)userInput)) {
                        manager.updateTask(manager.tasks.get((long)userInput), userInput);
                    } else if (manager.epics.containsKey((long)userInput)) {
                        manager.updateTask(manager.epics.get((long)userInput), userInput);
                    } else if (manager.subtasks.containsKey((long)userInput)) {
                        manager.updateTask(manager.subtasks.get((long)userInput), userInput);
                    } else {
                        System.out.println("Invalid user input! Please try later...");
                        continue;
                        }
                        break;
                    }

                    System.out.println("Updated! Back to main menu...");
                }
                case 3 -> manager.getTasks();
                case 5 -> manager.deleteTasks();
                case 6 -> {
                    System.out.println("What would you like to delete?");
                    while(true){
                        if(!scanner.hasNextInt()){
                            System.out.println("Invalid input! Please try again.");
                            scanner.nextLine();
                            continue;
                        }
                        userInput = scanner.nextInt();
                        scanner.nextLine();
                        manager.deleteById(userInput);

                        break;
                    }

                }
                case 4 -> {
                    System.out.println("Input the task's ID");

                    while(true){
                        while(!scanner.hasNextInt()){
                            System.out.println("Invalid user input! Try again.");
                            scanner.nextLine();
                        }
                        userInput = scanner.nextInt();
                        scanner.nextLine();
                        if(!manager.tasks.containsKey((long)userInput)
                        && !manager.subtasks.containsKey((long)userInput)
                        && !manager.epics.containsKey((long)userInput)){
                            System.out.println("Invalid user input! Try again.");
                        } else break;
                    }
                    manager.getById(userInput);
                }
                case 7 -> {
                    System.out.println("Available Epic IDs: " + manager.epics.keySet());
                    System.out.println("Input the Epic task's ID");
                    while(true){
                        while(!scanner.hasNextInt()){
                            System.out.println("Invalid user input! Try again.");
                            scanner.nextLine();
                        }
                        userInput = scanner.nextInt();
                        scanner.nextLine();
                        if(!manager.epics.containsKey((long)userInput)){
                            System.out.println("Invalid user input! Try again later");
                            continue;
                        }

                        Epic epic = manager.epics.get((long)userInput);
                        System.out.println("Subtasks for Epic " + epic.name + " (ID: " + epic.id + ")");

                        if(epic.subtasks.isEmpty()){
                            System.out.println("No Subtasks!");
                        } else {
                            for(Subtask subtask : manager.epics.get((long)userInput).subtasks.values()){
                                System.out.println("Info:");
                                System.out.println("Subtask ID: " + subtask.id);
                                System.out.println("Name: " + subtask.name);
                                System.out.println("Description: " + subtask.description);
                                System.out.println("Status: " + subtask.status);
                            }
                        }
                        break;
                    }

                }
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }

            }
            System.out.println("\n Back to menu...");


    }

    }
}