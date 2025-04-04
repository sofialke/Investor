package com.example.investor.config

import com.mongodb.reactivestreams.client.MongoClients
import jakarta.annotation.PostConstruct
import org.bson.Document
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

@Configuration
class MongoConfig {
    //could add some schema validation to the investor object in mango db --> for now I put the schema validation directly in the mangoDB
    //db.createCollection("investors", {
    //  validator: {
    //    $jsonSchema: {
    //      bsonType: "object",
    //      required: ["name", "status", "creatorEmail", "adminEmail"],
    //      properties: {
    //        name: {
    //          bsonType: "string",
    //          description: "must be a string and is required"
    //        },
    //        status: {
    //          bsonType: "string",
    //          enum: ["active", "inactive"],
    //          description: "must be either 'active' or 'inactive'"
    //        },
    //        creatorEmail: {
    //          bsonType: "string",
    //          pattern: "^\\S+@\\S+\\.\\S+$",
    //          description: "must be a valid email address"
    //        },
    //        adminEmail: {
    //          bsonType: "string",
    //          pattern: "^\\S+@\\S+\\.\\S+$",
    //          description: "must be a valid email address"
    //        },
    //        preferredGeographicalAreas: {
    //          bsonType: "array",
    //          items: {
    //            bsonType: "string"
    //          },
    //          description: "must be an array of strings"
    //        },
    //        completenessScore: {
    //          bsonType: "int",
    //          minimum: 0,
    //          maximum: 100,
    //          description: "must be an integer between 0 and 100"
    //        }
    //      }
    //    }
    //  }
    //});
}
