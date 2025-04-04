package com.example.investor.controller

import com.example.investor.model.Investor
import com.example.investor.service.InvestorService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/investors")
class InvestorController(private val investorService: InvestorService) {

    @PostMapping
    fun createInvestor(@RequestBody investor: Investor): Mono<Investor> =
        investorService.createInvestor(investor)

    @GetMapping("/{id}")
    fun getInvestor(@PathVariable id: String): Mono<Investor> =
        investorService.getInvestorById(id)

    @GetMapping("/name/{name}")
    fun getInvestorsByName(@PathVariable name: String): Mono<Investor> {
        return investorService.getInvestorsByName(name)
    }

    @PutMapping("/{id}")
    fun updateInvestor(@PathVariable id: String, @RequestBody investor: Investor): Mono<Investor> =
        investorService.updateInvestor(id, investor)

    @DeleteMapping("/{id}")
    fun deleteInvestor(@PathVariable id: String): Mono<Void> =
        investorService.deleteInvestor(id)

    @PostMapping("/bulk-insert")
    fun bulkInsert(@RequestBody investors: List<Investor>): Flux<Investor> =
        investorService.bulkInsertInvestorsParallel(investors)
}
