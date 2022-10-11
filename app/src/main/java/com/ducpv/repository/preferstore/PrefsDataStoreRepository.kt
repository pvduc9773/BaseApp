package com.ducpv.repository.preferstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ducpv.model.AccountSession
import com.ducpv.model.User
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
    suspend fun clearAccountSession()
    suspend fun setAccountSession(accountSession: AccountSession?)
    suspend fun getAccountSession(): AccountSession?
    suspend fun setUserInfo(userInfo: User?)
    suspend fun getUserInfo(): User?
}

class PrefsDataStoreRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val dataStore: DataStore<Preferences>
) : PrefsDataStoreRepository {
    companion object {
        private val PREFERENCES_KEY_ACCOUNT_SESSION = stringPreferencesKey("preferences_key_account_session")
        private val PREFERENCES_KEY_USER_INFO = stringPreferencesKey("preferences_key_user_info")
    }

    private suspend fun clear(key: Preferences.Key<String>) {
        dataStore.edit { it.remove(key) }
    }

    private suspend fun <T> get(key: Preferences.Key<T>): T? {
        return dataStore.data.map { it[key] }.firstOrNull()
    }

    private suspend inline fun <reified T> getObject(key: Preferences.Key<String>): T? {
        val json = get(key)
        return gson.fromJson<T>(json)
    }

    private suspend fun <T> set(key: Preferences.Key<T>, value: T) {
        dataStore.edit { it[key] = value }
    }

    private suspend fun setObject(key: Preferences.Key<String>, value: Any) {
        set(key, gson.toJson(value))
    }

    override suspend fun clearAccountSession() {
        clear(PREFERENCES_KEY_ACCOUNT_SESSION)
    }

    override suspend fun setAccountSession(accountSession: AccountSession?) {
        if (accountSession == null) return
        setObject(PREFERENCES_KEY_ACCOUNT_SESSION, accountSession)
    }

    override suspend fun getAccountSession(): AccountSession? {
        return getObject(PREFERENCES_KEY_ACCOUNT_SESSION)
    }

    override suspend fun setUserInfo(userInfo: User?) {
        if (userInfo == null) return
        else setObject(PREFERENCES_KEY_USER_INFO, userInfo)
    }

    override suspend fun getUserInfo(): User? {
        return getObject(PREFERENCES_KEY_USER_INFO)
    }
}

suspend fun PrefsDataStoreRepository.getTokenType(): String? {
    return getAccountSession()?.tokenType
}

suspend fun PrefsDataStoreRepository.getAccessToken(): String? {
    return getAccountSession()?.accessToken
}
