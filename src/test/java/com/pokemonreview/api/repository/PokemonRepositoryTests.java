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
import java.util.Optional;

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
    @Test
    public void PokemonRepository_findById_ReturnSelectedPokemonObject() {

        Pokemon pokemon1 = Pokemon.builder()
                .name("kasun")
                .type("electric").build();

        pokemonRepository.save(pokemon1);

        Pokemon pokemon = pokemonRepository.findById(pokemon1.getId()).get();

        Assertions.assertThat(pokemon).isNotNull();
        Assertions.assertThat(pokemon.getName()).isEqualTo("kasun");

    }

    @Test
    public void PokemonRepository_findByType_ReturnPokemon() {

        Pokemon pokemon1 = Pokemon.builder()
                .name("kasun")
                .type("electric").build();

        pokemonRepository.save(pokemon1);

        Pokemon pokemon = pokemonRepository.findByType(pokemon1.getType()).get();

        Assertions.assertThat(pokemon).isNotNull();
        Assertions.assertThat(pokemon.getType()).isEqualTo("electric");

    }

    @Test
    public void PokemonRepository_UpdatePokemon_ReturnUpdatePokemon() {

        Pokemon pokemon1 = Pokemon.builder()
                .name("kasun")
                .type("electric").build();

        pokemonRepository.save(pokemon1);

        Pokemon pokemonSave = pokemonRepository.findById(pokemon1.getId()).get();
        pokemonSave.setName("Kasun");
        pokemonSave.setType("Electric");

        Pokemon updatedPokemon = pokemonRepository.save(pokemonSave);

        Assertions.assertThat(updatedPokemon.getName()).isNotNull();
        Assertions.assertThat(updatedPokemon.getType()).isNotNull();

    }

    @Test
    public void PokemonRepository_DeletePokemon_ReturnPokemonIsEmpty() {

        Pokemon pokemon1 = Pokemon.builder()
                .name("kasun")
                .type("electric").build();

        pokemonRepository.save(pokemon1);

        pokemonRepository.deleteById(pokemon1.getId());

        Optional<Pokemon> pokemonReturn = pokemonRepository.findById(pokemon1.getId());

        Assertions.assertThat(pokemonReturn).isEmpty();

    }


}
