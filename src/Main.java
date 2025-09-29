import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        System.out.println("Welcome to TaskManager!");
        Scanner scanner = new Scanner(System.in);
        int userInput = -1;

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
                    scanner.next(); // consume the invalid input
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
                            scanner.next();
                        }
                        userInput = scanner.nextInt();
                        switch(userInput){
                            case 1 -> {
                                Task task = new Task();
                                System.out.print("Name: ");
                                task.name = scanner.next();
                                System.out.print("Description: ");
                                task.description = scanner.next();
                                task.status = Status.NEW;
                                task.id = manager.id;
                                manager.id++;

                                manager.createTask(task);
                            }
                            case 2 -> {
                                Epic epic = new Epic();
                                System.out.print("Name: ");
                                epic.name = scanner.next();
                                System.out.print("Description: ");
                                epic.description = scanner.next();
                                epic.status = Status.NEW;

                                epic.id = manager.id;
                                manager.id++;

                                System.out.println("How many subtasks would you like to create?");
                                while(true){
                                    while(!scanner.hasNextInt()){
                                        System.out.println("Invalid user input! Try again.");
                                        scanner.next();
                                    }
                                    userInput = scanner.nextInt();
                                    if(userInput < 0){
                                        System.out.println("Invalid user input! Try again.");
                                    } else break;
                                }
                                for(int i = 0; i < userInput; i++){
                                    Subtask subtask = new Subtask();

                                    System.out.print("Name: ");
                                    subtask.name = scanner.next();
                                    System.out.print("Description: ");
                                    subtask.description = scanner.next();
                                    subtask.status = Status.NEW;
                                    subtask.id = manager.id;
                                    subtask.parentId = epic.id;

                                    epic.subtasks.put(subtask.id, subtask);
                                    manager.createTask(subtask);
                                }
                                manager.createTask(epic);
                            }
                            case 3 -> {
                                Subtask subtask = new Subtask();
                                System.out.print("Name: ");
                                subtask.name = scanner.next();
                                System.out.print("Description: ");
                                subtask.description = scanner.next();
                                subtask.status = Status.NEW;
                                subtask.id = manager.id;
                                manager.id++;

                                System.out.println("Input Epic ID to add the subtask:");
                                while (true) {
                                    while (!scanner.hasNextInt()) {
                                        System.out.println("Invalid user input! Try again.");
                                        scanner.next();
                                    }
                                    userInput = scanner.nextInt();
                                    if (!manager.epics.containsKey(userInput)) {
                                        System.out.println("Invalid user input! Try again.");
                                    } else break;
                                }

                                manager.epics.get(userInput).subtasks.put(subtask.id, subtask);
                                System.out.println("Added!");
                            }
                        }
                        break;
                    }
                    // auto break
                }
//                    case 2
                case 3 -> manager.getTasks();
                case 5 -> manager.deleteTasks();
                case 4 -> {
                    System.out.println("Input the task's ID");

                    while(true){
                        while(!scanner.hasNextInt()){
                            System.out.println("Invalid user input! Try again.");
                            scanner.next();
                        }
                        userInput = scanner.nextInt();
                        if(manager.tasks.containsKey((long)userInput)
                        && !manager.subtasks.containsKey((long)userInput)
                        && !manager.epics.containsKey((long)userInput)){
                            System.out.println("Invalid user input! Try again.");
                        } else break;
                    }
                    manager.getById(userInput);
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