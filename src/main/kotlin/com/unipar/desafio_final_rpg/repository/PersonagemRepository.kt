package com.unipar.desafio_final_rpg.repository

import com.unipar.desafio_final_rpg.model.Personagem
import org.springframework.data.jpa.repository.JpaRepository

interface PersonagemRepository : JpaRepository<Personagem, Long>