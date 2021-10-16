# Client Server Todo Application

**Name:** Tegveer Singh\
**Student ID:** 100730432

This is the source code for the **Assignment 1** for the course Distributed Systems SOFE 4790U

## Description

### Testing

Enter the following commands from the parent directory to test the individual packages and their functionality functionality

```bash
javac -d output ClientServerApplication/Server.java ClientServerApplication/Client.java
java -cp output ClientServerApplication.Server
javac -d output Todo/Todo_test.java Todo/Todo_list.java Todo/Todo_item.java
java -cp output Todo.Todo_test
```

### Format

The format followed for the todo items in the list: Item-name - Course-name\_
