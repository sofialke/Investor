package com.example.investor.repository

import com.example.investor.model.Investor
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface InvestorRepository : ReactiveMongoRepository<Investor, String> {
    fun findByName(name: String): Mono<Investor>  // Return one investor --> the first one found in the DB with that name
}
