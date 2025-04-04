package com.example.investor.service
import com.example.investor.model.Investor
import com.example.investor.repository.InvestorRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Service
class InvestorService(private val investorRepository: InvestorRepository) {

    fun createInvestor(investor: Investor): Mono<Investor> =
        investorRepository.save(investor)

    fun getInvestorById(id: String): Mono<Investor> =
        investorRepository.findById(id)

    fun getInvestorsByName(name: String): Mono<Investor> {
        return investorRepository.findByName(name)
            .switchIfEmpty(Mono.error(NoSuchElementException("Investor not found")))
    }

    fun updateInvestor(id: String, updatedInvestor: Investor): Mono<Investor> =
        investorRepository.findById(id)
            .flatMap { existing ->
                val newInvestor = existing.copy(
                    name = updatedInvestor.name,
                    status = updatedInvestor.status,
                    preferredGeographicalAreas = updatedInvestor.preferredGeographicalAreas
                )
                investorRepository.save(newInvestor)
            }

    fun deleteInvestor(id: String): Mono<Void> =
        investorRepository.deleteById(id)

    fun bulkInsertInvestorsParallel(investors: List<Investor>): Flux<Investor> =
        Flux.fromIterable(investors)
            .limitRate(1000) // Process 1000 at a time- When consuming large data streams to avoid overloading memory
            .onBackpressureDrop { println("Dropped: $it") } //if buffering is not an option? When it's okay to lose some data (e.g., real-time feeds), the lost data is logged
            //.onBackpressureBuffer(5000) // Buffer 5000 records - When you want to store excess elements until the consumer is ready.
            .parallel() //faster processing using multiple threads - splits fluxes into parallel streams
            .runOn(Schedulers.boundedElastic())//dynamic thread pool, blocking operations (ex. writes), if a thread is blocked by an operation, a new thread (dynamic) is created, up to a reasonable amount of threads
            .flatMap { investorRepository.save(it) }//applies saves() method to each investor--> non-blocking
            .sequential() //merges parallel streams into single final sequence at the end of the process
    //.onBackpressureDrop { println("Dropped: $it") }
}
