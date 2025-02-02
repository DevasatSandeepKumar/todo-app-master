package com.sample.todo.data.local.todo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sample.todo.data.local.todo.constant.TodoDatabaseConst
import com.sample.todo.data.local.todo.converter.TodoConverter
import com.sample.todo.data.local.todo.dao.TodoDao
import com.sample.todo.data.local.todo.entity.TodoEntity

@TypeConverters(TodoConverter::class)
@Database(
    entities = [TodoEntity::class],
    version = TodoDatabaseConst.VERSION
)
abstract class TodoDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDao
}