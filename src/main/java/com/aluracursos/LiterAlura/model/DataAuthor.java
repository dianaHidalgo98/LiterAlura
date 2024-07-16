package com.aluracursos.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DataAuthor(
        @JsonAlias("name") String name,
        @Nullable
        @JsonAlias("birth_year") String birthDate,
        @Nullable
        @JsonAlias("death_year") String deathDate

) {
}
