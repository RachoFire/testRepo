import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // Static list of users, acting as a database
    private static ArrayList<User> users = new ArrayList<>();

    // Mock authentication service that always returns the first user when log in, and does nothing when sign up
    private static IAuthenticationService authService = new IAuthenticationService() {
        @Override
        public User signUp(String username, String password) {
            return null;
        }

        @Override
        public User logIn(String username, String password) {
            return users.get(0);
        }
    };
    private static boolean isRunning = true;

    /**
     * The entry point of the application.
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        users.add(new User("test", "test"));
        while (isRunning) {
            showMenu();
        }
    }

    /**
     * Displays the main menu to the user.
     */
    public static void showMenu() {
        System.out.println("Welcome to the To-Do List Application!");
        System.out.println("1. Log in");
        System.out.println("2. Sign up");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        // Ask for user choice
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        handleMenu(choice);
    }

    /**
     * Handles the user's choice, mapping the menu options to the corresponding methods.
     * @param choice The user's choice.
     */
    public static void handleMenu(int choice) {
        switch (choice) {
            case 1:
                onLogIn();
                break;
            case 2:
                onSignUp();
                break;
            case 3:
                onExit();
                break;
            default:
                System.out.println("Invalid choice!");
                showMenu();
        }
    }

    /**
     * Handles the log-in process, and the post-login operations.
     */
    public static void onLogIn() {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = authService.logIn(username, password);
        System.out.println("Welcome, " + user.getUsername() + "!");
        // TODO Now: Create an instance of the ToDoList class with the logged-in user and call the run method
    }

    /**
     * Handles the sign-up process.
     */
    public static void onSignUp() {
        System.out.print("Enter your username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        User user = authService.signUp(username, password);
        // TODO Now: Show a message based on the result of the signUp method:
        // - If the user is not null, show "User <username> has been created successfully!"
        // - If the user is null, show "The username is already taken!"
        if (user != null) {
            System.out.println("User " + user.getUsername() " has been created succesfully!");
        }
        else {
            System.out.println("The username is already taken!");
        }
    }

    /**
     * Exits the application by setting the `isRunning` flag to false.
     */
    public static void onExit() {
        isRunning = false;
    }
}