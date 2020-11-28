package com.example.hackforher.Quotes;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, UUID> {
    @Query( nativeQuery = true,value = "SELECT * FROM quote  ORDER BY RAND() LIMIT 1")
    Optional<Quote> getRandomQuote();

    @EntityGraph(attributePaths = "usersFavoriteQuotes")
    Optional<Quote> findById(UUID quoteId);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from user_favorite_quotes as f " +
            "where f.user_id=:userId  and f.quote_id=:quoteId")
    void deleteFavoritesQuote(@Param("userId") String userId,
                              @Param("quoteId") String quoteId);
}
