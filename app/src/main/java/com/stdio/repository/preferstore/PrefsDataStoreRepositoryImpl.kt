package com.stdio.repository.preferstore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.stdio.model.SignInAccount
import com.stdio.model.User
import com.stdio.utils.extension.fromJson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by pvduc9773 on 08/08/2022.
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_pass")

class PrefsDataStoreRepositoryImpl @Inject constructor(
    private val gson: Gson,
    private val dataStore: DataStore<Preferences>
) : PrefsDataStoreRepository {
    companion object {
        private val KEY_SIGN_IN_ACCOUNT = stringPreferencesKey("key_sign_in_account")
        private val KEY_USER_INFO = stringPreferencesKey("key_user_info")
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

    override suspend fun setSignInAccount(signInAccount: SignInAccount) {
        setObject(KEY_SIGN_IN_ACCOUNT, signInAccount)
    }

    override suspend fun getSignInAccount(): SignInAccount? {
        return getObject(KEY_SIGN_IN_ACCOUNT)
    }

    override suspend fun setUserInfo(userInfo: User) {
        setObject(KEY_USER_INFO, userInfo)
    }

    override suspend fun getUserInfo(): User? {
        return getObject(KEY_USER_INFO)
    }
}

suspend fun PrefsDataStoreRepository.getTokenType(): String? {
    return getSignInAccount()?.tokenType
}

suspend fun PrefsDataStoreRepository.getAccessToken(): String? {
    return getSignInAccount()?.accessToken
}