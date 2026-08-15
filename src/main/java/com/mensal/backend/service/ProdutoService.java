package com.mensal.backend.service;

import com.mensal.backend.dto.CreateProdutoRequest;
import com.mensal.backend.dto.UpdateProdutoRequest;
import com.mensal.backend.model.Produto;
import com.mensal.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criar(CreateProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setQuantidadeEstoque(request.quantidadeEstoque());
        produto.setCategoria(request.categoria());
        return repository.save(produto);
    }

    public Produto atualizar(Long id, UpdateProdutoRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setQuantidadeEstoque(request.quantidadeEstoque());
        produto.setCategoria(request.categoria());

        return repository.save(produto);
    }

    // adicionar aqui: listarTodos(), buscarPorId(), deletar()
}