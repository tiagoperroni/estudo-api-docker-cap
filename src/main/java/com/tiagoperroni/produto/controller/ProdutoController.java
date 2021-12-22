package com.tiagoperroni.produto.controller;

import java.util.List;

import com.tiagoperroni.produto.model.Produto;
import com.tiagoperroni.produto.service.ProdutoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAll() {
        return new ResponseEntity<>(this.produtoService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto prod = this.produtoService.findById(id);
        if (prod != null) {
            return new ResponseEntity<>(this.produtoService.findById(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<Produto> findByNome(@PathVariable String nome) {
        return new ResponseEntity<>(this.produtoService.findByNome(nome), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody Produto produto) {
        return new ResponseEntity<>(this.produtoService.saveProduto(produto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        String resposta = this.produtoService.updateProduto(id, produto);
        if (resposta != null) {
            return new ResponseEntity<>(resposta, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Produto com id " + id + " não foi encontrado.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> updateProduto(@PathVariable Long id) {
        String resposta = this.produtoService.deleteProduto(id);
        if (resposta != null) {
            return new ResponseEntity<>(resposta, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>("Produto com id " + id + " não foi encontrado.", HttpStatus.NOT_FOUND);
        }
    }
}
