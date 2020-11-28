package com.example.hackforher.Quotes;

import com.example.hackforher.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuoteById(@PathVariable String id){
        return quoteService.getQuoteById(UUID.fromString(id));
    }

    @PostMapping("/favorites/{id}")
    public ResponseEntity<?> addFavoriteQuote(@PathVariable("id") String quoteId,@AuthenticationPrincipal User user){
        return quoteService.addFavoriteQuote(UUID.fromString(quoteId),user);
    }

    @GetMapping("/favorites")
    public ResponseEntity<?> getFavoriteQuotes(@AuthenticationPrincipal User user){
        return quoteService.getFavoriteQuotes(user);
    }
    @DeleteMapping("/favorites/{id}")
    public ResponseEntity<?> deleteFavoriteQuote(@PathVariable String id,@AuthenticationPrincipal User user){
        return quoteService.deleteFavoriteQuote(UUID.fromString(id),user);
    }
}
