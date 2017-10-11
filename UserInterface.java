import java.text.SimpleDateFormat;
import java.util.*;

public class UserInterface {
    LinkedList<Task> list = new LinkedList<>();

    private Scanner in = new Scanner(System.in);

    private String welcomeMsg = "Welcome To The Task Manager, Friend!";
    private String normalMsg = "Operations";
    private String invalidInputMsg = "That is not a valid option!";
    private String invalidFormatMsg = "That is not the valid format!";
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
        Date dueDate;
        int priority;
        String description = "";
        String[] priorityOptions = {
            "Meaningless",
            "Can Procrastinate For A Short Time",
            "Should Get Done By Due Date",
            "Immediate Action"
        };
        
        talkToUser(createNewTaskMsg);

        dueDate = captureDate();
        description = captureDescription();
        System.out.print("Task Priority");
        priority = getResponse(priorityOptions);
        list.add(new Task(dueDate, description, priority));
        normal();
    }

    private Date captureDate() {
        boolean formatted;

        String dateHolder;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd");
        Date date = null;
        
        do{
            formatted = true;
            try {
                System.out.print("\nEnter the Date in dd-MMM-yyyy Format\nIE: 12-JAN-2020\nDate:\t");
                dateHolder = in.next();
                date = dateFormat.parse(dateHolder);
            } catch (Exception e) {
                formatted = false; 
                talkToUser(invalidFormatMsg);
            }
        } while (!formatted);
        return date;
    }

    private String captureDescription() {
        in.nextLine();
        System.out.print("Task Description:\t");
        return in.nextLine();
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
        System.out.println();
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