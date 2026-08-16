package com.mensal.backend.service;

import com.mensal.backend.dto.CreateProdutoRequest;
import com.mensal.backend.dto.ProdutoResponse;
import com.mensal.backend.dto.UpdateProdutoRequest;
import com.mensal.backend.exception.ProdutoNaoEncontradoException;
import com.mensal.backend.model.Produto;
import com.mensal.backend.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoResponse criar(CreateProdutoRequest request) {
        Produto produto = new Produto();
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setQuantidadeEstoque(request.quantidadeEstoque());
        produto.setCategoria(request.categoria());
        return toResponse(repository.save(produto));
    }

    public ProdutoResponse atualizar(Long id, UpdateProdutoRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setQuantidadeEstoque(request.quantidadeEstoque());
        produto.setCategoria(request.categoria());

        return toResponse(repository.save(produto));
    }

    public List<ProdutoResponse> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ProdutoResponse> buscarPorNome(String nome) {
        return repository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::toResponse)
                .toList();
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        return toResponse(produto);
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
        repository.deleteById(id);
    }

    private ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(), produto.getNome(), produto.getDescricao(),
                produto.getPreco(), produto.getQuantidadeEstoque(), produto.getCategoria()
        );
    }
}