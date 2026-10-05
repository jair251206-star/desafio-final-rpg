package com.unipar.desafio_final_rpg.controller

import com.unipar.desafio_final_rpg.model.Personagem
import com.unipar.desafio_final_rpg.service.PersonagemService
import org.springframework.web.bind.annotation.*
import com.unipar.desafio_final_rpg.model.Mensagem
import com.unipar.desafio_final_rpg.service.MensagemService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.client.RestClient

@RestController
class PersonagemController(

    @Value("\${rival.url}")
    private val rivalUrl: String,

    val personagemService: PersonagemService,

    val mensagemService: MensagemService,

    val restClient: RestClient

) {

    // ENVIAR MENSAGEM
    @GetMapping("/msg")
    fun mandarMensagemParaPersonagemRival(
        @RequestParam mensagem: String
    ) {

        
        try {
            restClient.post()
                .uri(rivalUrl)
                .contentType(MediaType.TEXT_PLAIN)
                .body(mensagem)
                .retrieve()
                .toBodilessEntity()

        } catch (e: Exception) {
            println("Deu erro: ${e.message}")
        }
    }

    // RECEBER MENSAGEM
    @PostMapping("/ouvir", consumes = [MediaType.TEXT_PLAIN_VALUE])
    fun receberMensagemDoPersonagemRival(
        @RequestBody mensagem: String
    ) {

        println("Mensagem recebida: $mensagem")

        mensagemService.salvar(
            Mensagem(
                remetente = "Rival",
                texto = mensagem
            )
        )
    }

    // LISTAR MENSAGENS
    @GetMapping("/mensagens")
    fun listarMensagens(): List<Mensagem>{
        return mensagemService.buscarTodas()
    }

    // SALVAR
    @PostMapping("/salvar")
    fun salvar(@RequestBody personagem: Personagem): Personagem{
        return personagemService.salvar(personagem)
    }

    // LISTAR TODOS
    @GetMapping("/personagens")
    fun buscarTodos(): List<Personagem>{
        return personagemService.buscarTodos()
    }

    // BUSCAR POR ID
    @GetMapping("/personagem")
    fun buscarPorId(@RequestParam id: Long): Personagem{
        return personagemService.buscarPorId(id)
    }

    // EDITAR
    @PutMapping("/editar")
    fun editar(@RequestBody personagem: Personagem): Personagem{
        return personagemService.salvar(personagem)
    }

    // DELETAR
    @DeleteMapping("/deletar")
    fun deletar(@RequestParam id: Long){
        personagemService.excluirPersonagem(id)
    }

    // BATALHA
    @PostMapping("/batalha")
    fun batalhar(
        @RequestParam atacanteId: Long,
        @RequestParam defensorId: Long
    ): Personagem {

        val atacante = personagemService.buscarPorId(atacanteId)
        val defensor = personagemService.buscarPorId(defensorId)

        return personagemService.atacar(atacante, defensor)
    }

}