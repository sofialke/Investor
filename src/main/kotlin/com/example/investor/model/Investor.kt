package com.example.investor.model

import org.springframework.data.annotation.*
import org.springframework.data.mongodb.core.mapping.Document
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.mongodb.core.index.Indexed
import java.math.BigDecimal
import java.time.Instant

@Document(collection = "investors")
@JsonIgnoreProperties(ignoreUnknown = true)
data class Investor(
    @Id val id: String? = null,
    @Indexed val name: String, //index to speed up the querying
    val status: String?,
    val preferredGeographicalAreas: List<String> = emptyList(),
    val preferredInvestmentTypes: List<String> = emptyList(),
    val sectors: List<String> = emptyList(),
    val verticals: List<String> = emptyList(),
    val macroAreas: List<String> = emptyList(),
    val type: String?,
    val macroType: String?,
    val website: String?,
    val image: String?,
    val isOld: Boolean?,
    @CreatedDate val createdAt: Instant? = Instant.now(),
    @LastModifiedDate val updatedAt: Instant? = Instant.now(),
    val creatorEmail: String,
    val adminEmail: String,
    @Indexed val completenessScore: Int?,
    val hqLocation: Address?,
    val financials: Financials?,
    val descriptions: InvDescriptions?,
    val contacts: Set<Contacts> = emptySet()
)

data class Address(
    val address: String?,
    val city: String?,
    val state: String?,
    val zip: String?,
    val country: String?,
    val phone: String?,
    val email: String?,
    val fax: String?,
    val sn: String?
)

data class Financials(
    val invMin: BigDecimal?,
    val invMax: BigDecimal?,
    val invAvg: BigDecimal?,
    val dealMax: BigDecimal?,
    val dealMin: BigDecimal?,
    val cmpValMin: BigDecimal?,
    val cmpValMax: BigDecimal?,
    val ebitdaMin: BigDecimal?,
    val ebitdaMax: BigDecimal?,
    val ebitMin: BigDecimal?,
    val ebitMax: BigDecimal?
)

data class InvDescriptions(
    val it: String?,
    val en: String?,
    val fr: String?,
    val de: String?,
    val es: String?,
    val ru: String?,
    val ch: String?
)

data class Contacts(
    val firstName: String?,
    val lastName: String?,
    val email: String?,
    val phone: String?,
    val mobile: String?,
    val fax: String?,
    val role: String?,
    val orderNum: Int?
)

