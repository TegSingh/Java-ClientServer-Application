package Todo;

import java.util.*;
import java.io.*;
import java.time.LocalDate;
import Todo.Todo_list;

public class Todo_test {
    public static void main(String[] args) {

        Todo_list todo_list = new Todo_list();

        System.out.println("Adding items to the list");

        // Add items to the Todo list
        LocalDate dueDate1 = LocalDate.of(2021, 10, 21);
        todo_list.add_todo(1, "Assignment 1 - Distributed Systems", dueDate1);

        LocalDate dueDate2 = LocalDate.of(2021, 10, 19);
        todo_list.add_todo(2, "Assignment 1 - Embedded Systems", dueDate2);

        LocalDate dueDate3 = LocalDate.of(2021, 10, 17);
        todo_list.add_todo(3, "Proposal Draft - Distributed Systems", dueDate3);

        LocalDate dueDate4 = LocalDate.of(2021, 10, 21);
        todo_list.add_todo(4, "Report R1 - Capstone Design Systems", dueDate4);

        LocalDate dueDate5 = LocalDate.of(2021, 10, 21);
        todo_list.add_todo(5, "Individual Activity Log #3 - Capstone Design Systems", dueDate5);

        // Display the entire list
        todo_list.display_todo_list();

        // Get Todos for a certain date
        LocalDate get_todo_date = LocalDate.of(2021, 10, 21);
        todo_list.get_todos_for_date(get_todo_date);

        // Test to add an item to the list using user input
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Action Item [Use Format provided in the README File]:");
        String action_item = sc.nextLine();

        System.out.println("Enter Task ID: ");
        int id = sc.nextInt();

        System.out.println("Enter year for due date: ");
        int year = sc.nextInt();

        System.out.println("Enter month for due date: ");
        int month = sc.nextInt();

        System.out.println("Enter day for due date: ");
        int day = sc.nextInt();

        LocalDate add_date = LocalDate.of(year, month, day);
        todo_list.add_todo(id, action_item, add_date);

        // Display the current state of the todo list
        todo_list.display_todo_list();

        // Test to remove an item from the list given a particular ID
        todo_list.remove_todo(5);

        // Display the final state of the Todo-list
        todo_list.display_todo_list();

    }
}