package com.example.messanger.domain.mapper

interface Mapper <T,K> {

    fun fromFirstToSecond(first:T):K

    fun fromSecondToFirst(second:K):T
}