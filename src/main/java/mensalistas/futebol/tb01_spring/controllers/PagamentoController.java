package mensalistas.futebol.tb01_spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import mensalistas.futebol.tb01_spring.models.*;
import mensalistas.futebol.tb01_spring.repositories.*;

@RestController
@RequestMapping("api")
public class PagamentoController {

    @Autowired
    PagamentoRepository rep;

    // Método GET /api/pagamentos -- lista os pagamentos
    @GetMapping("/pagamentos")
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {

        try {

            List<Pagamento> pl = new ArrayList<Pagamento>();

            rep.findAll().forEach(pl::add);

            if (pl.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pl, HttpStatus.OK);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método GET /api/pagamentos/id -- lista pagamento especifico
    @GetMapping("/pagamentos/{cod_jogador}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable("cod_pagamento") int cod_pagamento) {

        try {

            Optional<Pagamento> pagamento = rep.findById(cod_pagamento);

            if (pagamento.isPresent())
                return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método POST /api/pagamentos -- cria um novo pagamento
    @PostMapping("/pagamentos")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pay) {

        try {
            Pagamento p = rep.save(new Pagamento(pay.getAno(), pay.getMes(), pay.getValor(), pay.getCod_jogador()));

            return new ResponseEntity<>(p, HttpStatus.CREATED);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método PUT /api/pagamentos/id -- edita um pagamento
    @PutMapping("/pagamentos/{cod_pagamento}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("cod_pagamento") int cod_pagamento, @RequestBody Pagamento p)
    {
        Optional<Pagamento> data = rep.findById(cod_pagamento);

        if (data.isPresent())
        {
            Pagamento pay = data.get();
            pay.setAno(p.getAno());
            pay.setMes(p.getMes());
            pay.setValor(p.getValor());
            pay.setCod_jogador(p.getCod_jogador());

            return new ResponseEntity<>(rep.save(pay), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // Método DELETE /api/pagamentos/id -- deleta um pagamento
    @DeleteMapping("/pagamentos/{cod_pagamento}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("cod_pagamento") int cod_pagamento) {
        try {
            rep.deleteById(cod_pagamento);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Método DELETE /api/pagamentos -- deleta todos
    @DeleteMapping("/pagamentos")
    public ResponseEntity<HttpStatus> deleteAllPagamentos() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}