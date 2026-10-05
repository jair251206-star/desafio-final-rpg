package com.unipar.desafio_final_rpg.repository

import com.unipar.desafio_final_rpg.model.Mensagem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MensagemRepository : JpaRepository<Mensagem, Long>