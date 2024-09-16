package com.miken.kotlinsampler.model.repository

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface GenericRepository<T : IdentifiedEntity, K> {

    val innerRepos: JpaRepository<T, K>

    fun save(entity: T): T {
        return innerRepos.save(entity)
    }

    fun findById(id: K): Optional<T> {
        return innerRepos.findById(id!!)
    }

    fun findAll(): List<T> {
        return innerRepos.findAll()
    }
}
