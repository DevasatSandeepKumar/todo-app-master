package com.sample.todo.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.io.IOException

class Preferences(context: Context) {

    companion object {
        /**
         *  Preferences name.
         */
        const val PREFS = "prefs"

        /**
         * Determines how to order ToDos.
         */
        private val TODO_SORT = stringPreferencesKey("taskSort")
        private val DEFAULT_TODO_SORT = TodoSort.BY_DATE.name

        /**
         * Determines if completed ToDos are hidden on the List.
         */
        private val HIDE_COMPLETED = booleanPreferencesKey("hideCompleted")
        private const val DEFAULT_HIDE_COMPLETED = false
    }

    private val Context.store by preferencesDataStore(name = PREFS)
    private val store = context.store


    // GET

    val preferences: Flow<PreferencesModel> = store.data
        .catch { emit(handleIOException(it)) }
        .map { preferences ->
            val sort = TodoSort.valueOf(preferences[TODO_SORT] ?: DEFAULT_TODO_SORT)
            val hideCompleted = preferences[HIDE_COMPLETED] ?: DEFAULT_HIDE_COMPLETED
            return@map PreferencesModel(
                sort = sort,
                hideCompleted = hideCompleted
            )
        }

    // MISC

    private fun handleIOException(t: Throwable): Preferences {
        return if (t is IOException) emptyPreferences() else throw t
    }

    // MODEL

    data class PreferencesModel(
        val sort: TodoSort,
        val hideCompleted: Boolean
    )
}