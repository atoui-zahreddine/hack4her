package com.example.hackforher.Quotes;

import com.example.hackforher.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("")
    public ResponseEntity<?> createQuote(@Valid @RequestBody Quote quote){
        return quoteService.createQuote(quote);
    }
    @GetMapping("")
    public ResponseEntity<?> getAllQuotes(){
        return quoteService.getAllQuotes();
    }
    @GetMapping("/random")
    public ResponseEntity<?> getRandomQuote(){
        return quoteService.getRandomQuote();
    }
    @PostMapping("/favorites/{id}")
    public ResponseEntity<?> addFavoriteQuote(@AuthenticationPrincipal User user){
        return null;
    }

}
