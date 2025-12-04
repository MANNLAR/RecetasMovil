package com.plateup.app.domain.model

data class User(
    val id: Long,
    val username: String,
    val email: String?,
    val createdAt: Long
)
