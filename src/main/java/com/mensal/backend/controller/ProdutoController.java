package com.mensal.backend.controller;

import com.mensal.backend.dto.CreateProdutoRequest;
import com.mensal.backend.dto.UpdateProdutoRequest;
import com.mensal.backend.model.Produto;
import com.mensal.backend.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@RequestBody CreateProdutoRequest request) {
        Produto novoProduto = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Long id,
            @RequestBody UpdateProdutoRequest request) {
        Produto produtoAtualizado = service.atualizar(id, request);
        return ResponseEntity.ok(produtoAtualizado);
    }

    // adicionar aqui: @GetMapping, @GetMapping("/{id}"),
    // @GetMapping("/buscar") e @DeleteMapping("/{id}")
}