package Todo;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Todo_list {

    private ArrayList<Todo_item> todo_items = new ArrayList<Todo_item>();

    public Todo_list() {

    }

    // Method to add an action item to the list
    public void add_todo(int id, String action_item, LocalDate dueDate) {

        // Create a Todo object
        Todo_item item = new Todo_item(id, action_item, dueDate);
        // Add the item to the list
        if (todo_items.add(item)) {
            System.out.println("Item - " + item.toString() + " Added Successfully");
        } else {
            System.out.println("Could not add item");
        }

    }

    public ArrayList<Todo_item> get_todo_list() {
        return todo_items;
    }

    // Method to remove an action item from the list
    public String remove_todo(LocalDate dueDate) {

        int i = 0;
        // Declare an iterator to avoid java.util.concurrent modification exception
        Iterator<Todo_item> iterator = todo_items.iterator();

        // Get item to remove by looping through the entire list
        while (iterator.hasNext()) {
            Todo_item item = iterator.next();

            if (item.get_dueDate().isEqual(dueDate)) {
                System.out.println("Removing Item: " + item.toString());

                // Store item info string in a variable
                i++;
                // Remove item from iterator not the list to avoid concurrent modification
                // exception
                iterator.remove();
            }
        }

        String return_string = Integer.toString(i) + " items removed";
        if (i > 0) {
            return return_string;
        }
        // Return error message
        return "Cannot find Item";
    }

    // Method to return the action items due at a certain date
    public void get_todos_for_date(LocalDate dueDate) {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Todos for Date: " + dueDate.toString());

        // Look for todos for a specific date by looping through the entire list
        for (Todo_item item : todo_items) {
            if (item.get_dueDate().isEqual(dueDate)) {
                System.out.println(item.toString());
            }
        }

        System.out.println("-----------------------------------------------------------------------------");
    }

    // Method to display the entire list
    public void display_todo_list() {

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Current To-do list: ");
        // Display all todos currently in the list
        for (Todo_item item : todo_items) {
            System.out.println(item.toString());
        }

        System.out.println("-----------------------------------------------------------------------------");

    }

}