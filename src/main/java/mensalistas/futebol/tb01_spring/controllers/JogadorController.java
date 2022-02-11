package mensalistas.futebol.tb01_spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mensalistas.futebol.tb01_spring.models.Jogador;
import mensalistas.futebol.tb01_spring.repositories.JogadorRepository;

@RestController
@RequestMapping("api")

public class JogadorController {

    @Autowired
    JogadorRepository rep;

    // Método GET /api/jogadores -- lista os jogadores
    @GetMapping("/jogadores")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome) {

        try {
            List<Jogador> pl = new ArrayList<Jogador>();

            if (nome == null){
                rep.findAll().forEach(pl::add);

            } else {
                rep.findByNomeContaining(nome).forEach(pl::add);
            }

            if (pl.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pl, HttpStatus.OK);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método GET /api/jogadores -- lista jogador especifico
    @GetMapping("/jogadores/{cod_jogador}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("cod_jogador") int cod_jogador) {

        try {

            Optional<Jogador> jogador = rep.findById(cod_jogador);

            if (jogador.isPresent())
                return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método POST /api/jogadores -- cria um novo jogador
    @PostMapping("/jogadores")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador player) {

        try {
            System.out.println(player);
            Jogador j = rep.save(new Jogador(player.getNome(), player.getEmail(), player.getDataNasc()));


            return new ResponseEntity<>(j, HttpStatus.CREATED);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método PUT /api/jogadores/id -- edita um jogador
    @PutMapping("/jogadores/{cod_jogador}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("cod_jogador") int cod_jogador, @RequestBody Jogador j)
    {
        Optional<Jogador> data = rep.findById(cod_jogador);

        if (data.isPresent())
        {
            Jogador player = data.get();
            player.setNome(j.getNome().isEmpty() ? player.getNome() : j.getNome());
            player.setEmail(j.getEmail().isEmpty() ? player.getEmail() : j.getEmail());
            player.setDataNasc(j.getDataNasc().toString().isEmpty() ? player.getDataNasc() : j.getDataNasc());

            return new ResponseEntity<>(rep.save(player), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // Método DELETE /api/jogadores -- deletr um jogador
    @DeleteMapping("/jogadores/{cod_jogador}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("cod_jogador") int cod_jogador) {
        try {
            rep.deleteById(cod_jogador);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Método DELETE /api/jogadores -- deleta todos
    @DeleteMapping("/jogadores")
    public ResponseEntity<HttpStatus> deleteAllJogadores() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
