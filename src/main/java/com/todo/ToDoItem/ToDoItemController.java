package com.todo.ToDoItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoItemController {

    @Autowired
    private ToDoItemService toDoItemService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody ToDoItemResponse get(@PathVariable(value = "id") String id) {
        List<String> errors = new ArrayList<>();
        ToDoItem toDoItem = null;
        try {
            toDoItem = toDoItemService.get(id);
        } catch (final Exception e) {
            errors.add(e.getMessage());
        }
        return ToDoItemAdapter.toToDoItemResponse(toDoItem, errors);
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody List<ToDoItemResponse> getAll() {
        List<String> erros = new ArrayList<>();
        List<ToDoItem> toDoItems = toDoItemService.getAll();
        List<ToDoItemResponse> toDoItemResponses = new ArrayList<>();

        toDoItems.stream().forEach(toDoItem -> {
            toDoItemResponses.add(ToDoItemAdapter.toToDoItemResponse(toDoItem, erros));
        });

        return toDoItemResponses;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ToDoItemResponse create(@RequestBody final ToDoItemRequest todoItemRequest) {
        List<String> errors = new ArrayList<>();
        ToDoItem toDoItem = ToDoItemAdapter.toTodoItem(todoItemRequest);
        try {
            toDoItem = toDoItemService.create(toDoItem);
        } catch (final Exception e) {
            errors.add(e.getMessage());
            e.printStackTrace();
        }

        return ToDoItemAdapter.toToDoItemResponse(toDoItem, errors);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody ToDoItemResponse update(@RequestBody final ToDoItemRequest toDoItemRequest) {
        List<String> errors = new ArrayList<>();
        ToDoItem toDoItem = ToDoItemAdapter.toTodoItem(toDoItemRequest);
        try{
            toDoItem = toDoItemService.update(toDoItem);
        }catch (final Exception e){
            errors.add(e.getMessage());
            e.printStackTrace();
        }
        return ToDoItemAdapter.toToDoItemResponse(toDoItem, errors);
    }
}
