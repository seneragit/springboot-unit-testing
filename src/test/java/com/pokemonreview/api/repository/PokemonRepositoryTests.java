package com.pokemonreview.api.repository;

import com.pokemonreview.api.models.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.rules.Timeout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTests {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Test
    public void PokemonRepository_SaveAll_ReturnSavedPokemon() {

        //Arrange

        Pokemon pokemon = Pokemon.builder()
                .name("senera")
                .type("electric").build();

        //Act
        Pokemon savedPokemon = pokemonRepository.save(pokemon);

        //Assert
        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getId()).isGreaterThan(0);
    }
    @Test
    public void PokemonRepository_GetAll_ReturnsMoreThanOnePokemon() {

        Pokemon pokemon1 = Pokemon.builder()
                .name("kasun")
                .type("electric").build();

        Pokemon pokemon2 = Pokemon.builder()
                .name("nisal")
                .type("electric").build();

        pokemonRepository.save(pokemon1);
        pokemonRepository.save(pokemon2);

        List<Pokemon> allPokemon = pokemonRepository.findAll();

        Assertions.assertThat(allPokemon).isNotNull();
        Assertions.assertThat(allPokemon.size()).isEqualTo(2);


    }
}
