package com.example.hackforher.Quotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
