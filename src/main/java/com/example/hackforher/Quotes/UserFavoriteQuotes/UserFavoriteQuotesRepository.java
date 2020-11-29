package com.example.hackforher.Quotes.UserFavoriteQuotes;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserFavoriteQuotesRepository extends JpaRepository<UserFavoriteQuotes, UUID> {
    @Transactional
    @Modifying
    @Query("delete from UserFavoriteQuotes u where u.user.id=:userId and u.quote.id=:quoteId")
    void deleteFavoritesQuote(@Param("userId") UUID userId,@Param("quoteId") UUID quoteId);

    @Query("select q from UserFavoriteQuotes q where q.user.id=:id")
    @EntityGraph(attributePaths = "quote")
    List<UserFavoriteQuotes> findByUserId(@Param("id") UUID id);
    @Query("select q from UserFavoriteQuotes q where q.quote.id=:quoteId and q.user.id=:userId")
    @EntityGraph(attributePaths = "quote")
    Optional<UserFavoriteQuotes> findByUserIdAndQuoteID(@Param("quoteId") UUID quoteId,@Param("userId") UUID userId);
}
