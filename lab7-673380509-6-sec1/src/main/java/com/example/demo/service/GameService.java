package com.example.demo.service;

import com.example.demo.model.Game;
import com.example.demo.repository.GameRepository;
import com.example.demo.strategy.DiscountContext;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {
    private final GameRepository gameRepository;
    private final DiscountContext discountContext;

    // @Autowired
    public GameService(GameRepository gameRepository, DiscountContext discountContext){
        this.gameRepository = gameRepository;
        this.discountContext = discountContext;
    }
    
    public List<Game> getAllGames(){
        List<Game> games = gameRepository.findAll();
        for (Game game : games){
            if(game.getPrice() != null){
                double finalPrice = discountContext.calculate(game.getPrice(), game.getDiscountType());
                game.setFinalPrice(finalPrice);
            }
        }
        return games;
    }
    public Game getGameById(Long id){
        Game game = gameRepository.findById(id).orElse(null);
        if(game != null && game.getPrice() != null){
            double finalPrice = discountContext.calculate(game.getPrice(), game.getDiscountType());
            game.setFinalPrice(finalPrice);
        }
        return game;
    }
    public void saveGame(Game game){
        gameRepository.save(game);
    }
    public void deleteGame(Long id){
        gameRepository.deleteById(id);
    }
}
