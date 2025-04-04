package com.example.investor.service

import com.example.investor.model.Investor
import com.example.investor.repository.InvestorRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureDataMongo
class InvestorServiceTest {

    @Autowired
    lateinit var investorService: InvestorService

    @Autowired
    lateinit var investorRepository: InvestorRepository

    @BeforeEach
    fun setUp() {
        // Ensure the database is empty before each test
        investorRepository.deleteAll().block()
    }

    @Test
    fun `should save investor correctly`() {
        // Given
        val investor = Investor(
            name = "Test Investor",
            status = "active",
            creatorEmail = "creator@example.com",
            adminEmail = "admin@example.com",
            completenessScore = 1,
            descriptions = null,
            financials = null,
            hqLocation = null,
            image = null,
            isOld = null,
            macroType = null,
            type = null,
            website = null
        )

        // When
        val savedInvestor = investorService.createInvestor(investor).block() // Save the investor using the service

        // Then
        assertNotNull(savedInvestor) // Ensure it was actually saved
        assertEquals("Test Investor", savedInvestor?.name)

        // Verify retrieval
        val result = investorService.getInvestorsByName("Test Investor").block()
        assertNotNull(result)
        assertEquals("Test Investor", result?.name)
        assertEquals("active", result?.status)
    }
}
