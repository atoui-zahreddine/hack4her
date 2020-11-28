package com.example.hackforher.Quotes;

import com.example.hackforher.Exception.NotFoundException;
import com.example.hackforher.Exception.ResourceExistException;
import com.example.hackforher.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public ResponseEntity<?> createQuote(Quote quote){
        return new ResponseEntity<>(quoteRepository.save(quote), HttpStatus.CREATED);
    }

    public ResponseEntity<?> getAllQuotes(){
        return new ResponseEntity<>(quoteRepository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<?> getRandomQuote(){
        return new ResponseEntity<>(quoteRepository.getRandomQuote(),HttpStatus.OK);
    }

    public ResponseEntity<?> getQuoteById(UUID quoteId){
        var quote = getQuote(quoteId);
        return new ResponseEntity<>(quote,HttpStatus.OK);
    }

    private Quote getQuote(UUID quoteId) {
        return quoteRepository.findById(quoteId)
                .orElseThrow(() -> new NotFoundException(HttpStatus.NOT_FOUND.value(), "no quote with this id  "));
    }

    public ResponseEntity<?> addFavoriteQuote(UUID quoteId, User user) {
        var quote=getQuote(quoteId);
        var isAlreadyFavorite=user.getFavoriteQuotes()
                .stream()
                .anyMatch((q)-> q.getId().equals(quoteId));

        if(isAlreadyFavorite){
            throw new ResourceExistException(150,"already added to favorite quotes");
        }

        quote.getUsersFavoriteQuotes().add(user);
        user.getFavoriteQuotes().add(quote);
        quoteRepository.save(quote);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    public ResponseEntity<?> deleteFavoriteQuote(UUID quoteId,User user){
        if(!quoteRepository.existsById(quoteId)){
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "no quote with this id");
        }
        quoteRepository.deleteFavoritesQuote(quoteId.toString(),user.getId().toString());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> getFavoriteQuotes(User user) {
        return new ResponseEntity<>(user.getFavoriteQuotes(),HttpStatus.OK);
    }
}
