package com.example.buzztoday.domain.usecases

import com.example.buzztoday.domain.manager.LocalUserManager

class Save(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}