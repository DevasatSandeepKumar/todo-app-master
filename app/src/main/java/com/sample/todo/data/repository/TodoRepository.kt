package com.sample.todo.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import com.sample.todo.data.local.preferences.TodoSort
import com.sample.todo.data.local.todo.dao.TodoDao
import com.sample.todo.data.mapper.toEntity
import com.sample.todo.data.mapper.toModel
import com.sample.todo.data.model.TodoModel

class TodoRepository(
    private val dao: TodoDao
) {

    // INSERT

    suspend fun insertTodo(todo: TodoModel) {
        withContext(Dispatchers.IO) {
            dao.insertTodo(todo.toEntity())
        }
    }

    // UPDATE

    suspend fun updateTodo(todo: TodoModel) {
        withContext(Dispatchers.IO) {
            dao.updateTodo(todo.toEntity())
        }
    }

    // DELETE

    suspend fun deleteTodo(todo: TodoModel) {
        withContext(Dispatchers.IO) {
            dao.deleteTodo(todo.toEntity())
        }
    }

    suspend fun deleteAllCompletedTodo() {
        withContext(Dispatchers.IO) {
            dao.deleteAllCompletedTodo()
        }
    }

    suspend fun deleteAllTodo() {
        withContext(Dispatchers.IO) {
            dao.deleteAllTodo()
        }
    }

    // GET

    fun getTodos(query: String, hideCompleted: Boolean, sort: TodoSort): Flow<List<TodoModel>> {
        return dao.getTodos(
            query = query,
            hideCompleted = hideCompleted,
            sort = sort
        ).map { entities ->
            entities.map { entity ->
                entity.toModel()
            }
        }
    }
}