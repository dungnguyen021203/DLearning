package com.example.dlearning.common

interface ApiMapper<Domain, Entity> {
    fun mapToDomain(apiDto: Entity): Domain
}