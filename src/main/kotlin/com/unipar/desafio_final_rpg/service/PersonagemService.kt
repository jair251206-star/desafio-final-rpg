package com.unipar.desafio_final_rpg.service

import com.unipar.desafio_final_rpg.model.Personagem
import com.unipar.desafio_final_rpg.repository.PersonagemRepository
import org.springframework.stereotype.Service

@Service
class PersonagemService(

    val personagemRepository: PersonagemRepository

) {

    // SALVAR
    fun salvar(personagem: Personagem): Personagem{
        return personagemRepository.save(personagem)
    }

    // LISTAR TODOS
    fun buscarTodos(): List<Personagem>{
        return personagemRepository.findAll()
    }

    // BUSCAR POR ID
    fun buscarPorId(id: Long): Personagem{
        return personagemRepository.findById(id).get()
    }

    // DELETAR
    fun excluirPersonagem(id: Long){
        personagemRepository.deleteById(id)
    }

    // BATALHA
    fun atacar(atacante: Personagem, defensor: Personagem): Personagem {

        defensor.vida -= atacante.forca

        if(defensor.vida < 0){
            defensor.vida = 0
        }

        return personagemRepository.save(defensor)
    }

}