package com.example.investor.repository

import com.example.investor.model.Investor
import com.example.investor.service.InvestorService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.Mono.`when`
import reactor.test.StepVerifier

@SpringBootTest
@AutoConfigureDataMongo
class InvestorRepositoryTest {

    @Autowired
    lateinit var investorRepository: InvestorRepository

    @BeforeEach
    fun setUp() {
        // Ensure the database is empty before each test
        investorRepository.deleteAll().block()
    }

    @Test
    fun `findByName returns investor from MongoDB`() {
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
        investorRepository.save(investor).block()  // Save the investor

        // When
        val result = investorRepository.findByName("Test Investor").block()  // Find the investor

        // Then
        assertNotNull(result)
        assertEquals("Test Investor", result?.name)
        assertEquals("active", result?.status)
    }
}
