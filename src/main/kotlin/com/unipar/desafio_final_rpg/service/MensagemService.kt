package com.unipar.desafio_final_rpg.service

import com.unipar.desafio_final_rpg.model.Mensagem
import com.unipar.desafio_final_rpg.repository.MensagemRepository
import org.springframework.stereotype.Service

@Service
class MensagemService(

    val mensagemRepository: MensagemRepository

) {

    fun salvar(mensagem: Mensagem): Mensagem{
        return mensagemRepository.save(mensagem)
    }

    fun buscarTodas(): List<Mensagem>{
        return mensagemRepository.findAll()
    }
}