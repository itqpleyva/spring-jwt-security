package com.itqpleyva.springjwtsecurity.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Arbol {

    private int hojas;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
}