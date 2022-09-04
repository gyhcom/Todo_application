package com.todo.ToDoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoItemAdapter {
    public static ToDoItemResponse toToDoItemResponse(final ToDoItem toDoItem, final List<String> errors) {
        return ToDoItemResponse.builder()
                .toDoItem(toDoItem)
                .errors(Optional.ofNullable(errors).orElse(new ArrayList<>()))
                .build();
    }

    public static ToDoItem toTodoItem(ToDoItemRequest todoItemRequest) {
        if (todoItemRequest == null) {
            return null;
        }

        return ToDoItem.builder()
                .title(todoItemRequest.getTitle())
                .done(todoItemRequest.isDone())
                .build();
    }
}
