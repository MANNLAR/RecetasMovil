package com.plateup.app.core.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.sessionDataStore by preferencesDataStore(name = "session")

class SessionManager(private val context: Context) {
    private val currentUserKey = longPreferencesKey("current_user")

    val currentUserId: Flow<Long?> = context.sessionDataStore.data.map { prefs ->
        prefs[currentUserKey]
    }

    suspend fun setCurrentUser(id: Long?) {
        context.sessionDataStore.edit { prefs ->
            if (id == null) prefs.remove(currentUserKey) else prefs[currentUserKey] = id
        }
    }
}
