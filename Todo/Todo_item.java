package Todo;

import java.util.*;
import java.io.*;
import java.time.*;

public class Todo_item {

    private int id; // Client ID
    private String action_item; // Name of the to-do item
    private LocalDate dueDate; // Due date of the to-do

    // Define the constructor for a todo item
    public Todo_item(int id, String action_item, LocalDate dueDate) {
        this.id = id;
        this.action_item = action_item;
        this.dueDate = dueDate;
    }

    // Getter for action_item
    public String get_action_item() {
        return this.action_item;
    }

    // Getter for dueDate
    public LocalDate get_dueDate() {
        return this.dueDate;
    }

    // Getter for Client ID
    public int get_id() {
        return this.id;
    }

    // The tostring method to display the todo_item as string
    public String toString() {
        String return_string = "ID: " + this.id + " Task: " + this.action_item + " Due: " + dueDate.toString();
        return return_string;
    }
}
