package com.ducpv.repository.preferstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ducpv.utils.extension.fromJson
import com.google.gson.Gson
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "base-app")

interface PrefsDataStoreRepository {
    // TODO: implement
}

class PrefsDataStoreRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val dataStore: DataStore<Preferences>
) : PrefsDataStoreRepository {
    companion object {
        private val PREFERENCES_KEY_XXX = stringPreferencesKey("preferences_key_xxx")
    }

    private suspend fun clear(key: Preferences.Key<String>) {
        dataStore.edit { it.remove(key) }
    }

    private suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }

    private suspend fun <T> get(key: Preferences.Key<T>): T? {
        return dataStore.data.map { it[key] }.firstOrNull()
    }

    private suspend fun setObject(key: Preferences.Key<String>, value: Any) {
        set(key, gson.toJson(value))
    }

    private suspend inline fun <reified T> getObject(key: Preferences.Key<String>): T? {
        val json = get(key)
        return gson.fromJson<T>(json)
    }
}
