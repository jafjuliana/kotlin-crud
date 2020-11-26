package com.kestraa.crud

import java.time.LocalDateTime

data class DataCompany(val id: Long, val name: String, val email: String, val contact: String, val createdAt: LocalDateTime
        )

object Companies {
    fun data(): MutableList<DataCompany> =
        mutableListOf(
                DataCompany(0, "Juliana", "juliana@kestraa.com.br", "11966666666", LocalDateTime.now())
        )
}
