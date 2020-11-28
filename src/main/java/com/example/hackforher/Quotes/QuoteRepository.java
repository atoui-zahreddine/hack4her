package com.example.hackforher.Quotes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {
    @Query( nativeQuery = true,value = "SELECT * FROM quote  ORDER BY RAND() LIMIT 1")
    Optional<Quote> getRandomQuote();
}
