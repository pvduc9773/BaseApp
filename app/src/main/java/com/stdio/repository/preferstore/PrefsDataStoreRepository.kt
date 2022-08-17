package com.stdio.repository.preferstore

import com.stdio.model.SignInAccount
import com.stdio.model.User

/**
 * Created by pvduc9773 on 08/08/2022.
 */
interface PrefsDataStoreRepository {
    suspend fun setSignInAccount(signInAccount: SignInAccount)
    suspend fun getSignInAccount(): SignInAccount?
    suspend fun setUserInfo(userInfo: User)
    suspend fun getUserInfo(): User?
}