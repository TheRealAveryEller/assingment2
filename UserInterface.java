import java.text.SimpleDateFormat;
import java.io.*;
import java.util.*;

public class UserInterface {
    LinkedList<Task> list = new LinkedList<>();

    private Scanner in = new Scanner(System.in);

    private String welcomeMsg = "Welcome To The Task Manager, Friend!";
    private String normalMsg = "Basic Operations";
    private String taskMsg = "Task Specific Operations";
    private String listMsg = "List Specific Operations";
    private String invalidInputMsg = "That is not a valid option!";
    private String invalidFormatMsg = "That is not the valid format!";
    private String createNewTaskMsg = "Create A New Task!";
    private String fnfMsg = "That File Wasn't Found!";
    private String loadMsg = "Load A File";
    private String saveMsg = "Save A File";
    private String incompleteMsg = "Incomplete Tasks";
    
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
            "View Incomplete Tasks",
            taskMsg,
            listMsg,
            saveMsg,
            "Exit"
        };

        talkToUser(normalMsg);
        choice = getResponse(options);

        switch(choice) {
            case 1:
                viewIncompleteList();
                break;
            case 2:
                taskSpecific();
                break;
            case 3:
                listSpecific();
                break;
            case 4:
                saveList();
            default:
                exitProgram();
        }
    }

    private void viewIncompleteList() {
        int choice;
        String[] viewOptions = {
            "Return To Options",
            "Exit"
        };
        talkToUser(incompleteMsg);
        for(Task task: list) {
            if(!task.isComplete())
                System.out.println("\n" + task);
        }
        System.out.print("\nReturn To Menu");
        choice = getResponse(viewOptions);
        switch(choice) {
            case 1:
                normal();
                break;
            default:
                exitProgram();
        }
    }

    private void listSpecific() {
        int choice;
        String[] listOptions = {
            "View Entire List",
            "Order List",
            "Save List",
            "Load List",
            "Exit"
        };

        talkToUser(listMsg);
        choice = getResponse(listOptions);

        switch(choice) {
            case 1:
                viewEntireList();
                break;
            case 2:
                orderList();
                break;
            case 3:
                saveList();
                break;
            case 4:
                loadList();
                break;
            default:
                exitProgram();
        }
    }

    private void taskSpecific() {
        int choice;
        String[] taskOptions = {
            "Create A New Task",
            "View All Operations",
            "Exit"
        };

        talkToUser(taskMsg);
        choice = getResponse(taskOptions);

        switch(choice) {
            case 1:
                createNewTask();
                break;
            case 2:
                normal();
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
        orderByPriority();
    }

    private void orderByPriority() {
        // ListIterator<Task> iterator = list.listIterator();
        // if(iterator != null || iterator.next() != null ) {
        //     LinkedList<Task> temp = new LinkedList<>();
        //     ListIterator<Task> previous, current, next;
        //     current = iterator;
        //     while(current != null) {
        //         next = current.next();
        //         previous = temp;
        //         while (previous.next!=null && previous.next.priority != current.priority) {
        //             previous = previous.next;
        //         }
        //         current.next() = previous.next();
        //         previous.next() = current;
        //         current = next;
        //     }
        // }

        Collections.sort(list, Task.PriorityComparator);
        System.out.println(list);


    }

    private void saveList() {
        String filename;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        talkToUser(saveMsg);
        System.out.print("\nFile Name:\t");
        filename = in.next();

        try {
            fos = new FileOutputStream(filename);
            oos = new ObjectOutputStream(fos);
            for(Task task: list)
            oos.writeObject(task);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        normal();        
    }

    private void loadList() {
        boolean more = true;
        String filename;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        talkToUser(loadMsg);
        System.out.print("\nFile Name:\t");
        filename = in.next();
        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
            while(more) {
                Task task = (Task) ois.readObject();
                if(task != null) {
                    list.add(task);
                }
                else {
                    more = false;
                }
            }
                
            in.close();
        } catch (EOFException eof) {
            talkToUser("File Read Completely");
        } 
        catch (FileNotFoundException fnf) {
            talkToUser(fnfMsg);
            loadList();
        }
        catch (IOException ioe) {
            System.out.print("IO Failure: ");
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException cnf) {
            talkToUser("The Task Class Can't Be Found!");
        }
        normal();
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