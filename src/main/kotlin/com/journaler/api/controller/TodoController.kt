package com.journaler.api.controller

import com.journaler.api.data.Todo
import com.journaler.api.data.TodoDTO
import com.journaler.api.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    private lateinit var service: TodoService


    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTodos(): Iterable<TodoDTO> = service.getTodos()


    @PutMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun insertTodo(
        @RequestBody todo: TodoDTO
    ): TodoDTO = service.insertTodo(todo)

    @DeleteMapping(
        value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun deleteTodo(
        @PathVariable(name = "id") id: String
    ) = service.deleteTodo(id)


    @PostMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun updateTodo(@RequestBody todo: TodoDTO): TodoDTO = service.updateTodo(todo)

    @PostMapping(
        value = ["/later_than"],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getTodosLaterThan(@RequestBody payload: TodoLaterThanRequest):
            Iterable<TodoDTO> = service.getScheduleLaterThan(payload.date)

}