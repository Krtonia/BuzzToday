package com.example.buzztoday.domain.usecases

import com.example.buzztoday.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class Read(
    private val localUserManager: LocalUserManager
) {

     operator fun invoke():Flow<Boolean>{
        return localUserManager.readAppEntry()
    }

}