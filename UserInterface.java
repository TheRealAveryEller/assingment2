import java.util.*;

public class UserInterface {
    LinkedList<Task> list = new LinkedList<>();

    private Scanner in = new Scanner(System.in);

    private String welcomeMsg = "Welcome To The Task Manager, Friend!";
    private String normalMsg = "Operations";
    private String invalidInputMsg = "That is not a valid option!";
    private String createNewTaskMsg = "Create A New Task!";
    
    public void welcome() {
        int choice;
        String[] options = {
            "Load Saved Data",
            "Start New List",
            "Quit"
        };

        talkToUser(welcomeMsg);
        choice = getResponse(options);

        switch(choice) {
            case 1: 
                loadList();
                break;
            case 2: 
                normal();
                break;
            default:
                exitProgram();
        }
    }

    private void normal() {
        int choice;
        String[] options = {
            "Create A New Task",
            "View Entire List",
            "Order List",
            "Save List",
            "Load List",
            "Exit"
        };

        talkToUser(normalMsg);
        choice = getResponse(options);

        switch(choice) {
            case 1:
                createNewTask();
                break;
            case 2:
                viewEntireList();
                break;
            case 3:
                orderList();
                break;
            case 4:
                saveList();
                break;
            case 5:
                loadList();
                break;
            default:
                exitProgram();
        }
    }

    private int getResponse(String[] options) {
        String optionsString = "";
        int numOptions = options.length;
        int choice;
        for(int x = 0; x < numOptions; x++) {
            optionsString += "\n" + (x + 1) + ":\t" + options[x];
        }
        do {
            System.out.println(optionsString);
            System.out.print("Choice:\t");
            choice = in.nextInt();
            if(choice < 1 || choice > numOptions){
                talkToUser(invalidInputMsg);
            }
        } while(choice < 1 || choice > numOptions);
        return choice;        
    }

    // Maybe Add Date Validation
    // Needs Cleaning
    private void createNewTask() {
        int[] dueDate = new int[3];
        String description = "";
        String[] priorityOptions = {
            "Meaningless",
            "Can Procrastinate For A Short Time",
            "Should Get Done By Due Date",
            "Immediate Action"
        };
        int priority;
        int compChoice;
        

        talkToUser(createNewTaskMsg);

        System.out.print("\nDue Date\nYear:\t");
        dueDate[0] = in.nextInt();
        do{
            System.out.print("Month:\t");
            dueDate[1] = in.nextInt();
        }while(dueDate[1] > 12 || dueDate[1] < 1);
        do{
            System.out.print("Day:\t");
            dueDate[2] = in.nextInt();
        }while(dueDate[2] > 31 || dueDate[2] < 1);
        in.nextLine();
        do{
            System.out.print("Description:\t");
            description = in.nextLine();
        } while(description == "");        

        System.out.print("Priority");
        priority = getResponse(priorityOptions);
        
        do{
            System.out.print("Completed is set to false. OK?\n1:\tYes\n2:\tNo\nChoice:\t");
            compChoice = in.nextInt();
        } while (compChoice < 1 || compChoice > 2);
        if(compChoice == 1) {
            Task newTask = new Task(dueDate, description, priority);
            list.add( newTask );
        } else if (compChoice == 2) {
            Task newTask = new Task(dueDate, description, priority, compChoice);
            list.add( newTask );
        }
        
        normal();
    }

    private void orderList() {
        System.out.println("This is the hard part");
    }

    private void saveList() {
        System.out.println("Do This");
    }

    private void loadList() {
        System.out.println("Do This");
    }

    private void talkToUser(String string) {
        dashesFor(string);
        System.out.println("\n" + string);
        dashesFor(string);
    }

    private void dashesFor(String string) {
        for(char c: string.toCharArray()) {
			System.out.print("-");
        }
    }
    
    private void exitProgram() {
        System.out.println("\nThank you, bye!\n");
        System.exit(0);
    }

    private void viewEntireList() {
        System.out.println(list);
        normal();
    }
}