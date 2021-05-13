package com.sanvalero.animalfluxclient.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Creado por @ author: Pedro Orós
 * el 12/05/2021
 */

@Data
@NoArgsConstructor
@ToString
public class Animal {

    private String id;
    private String name;
    private String classification;
    private int height;
    private float weight;
}