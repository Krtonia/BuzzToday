package com.app.buzztoday.domain.usecases

import com.app.buzztoday.domain.manager.LocalUserManager

class Save(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}