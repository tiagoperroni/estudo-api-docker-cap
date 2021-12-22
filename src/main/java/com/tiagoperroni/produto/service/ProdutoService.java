package com.tiagoperroni.produto.service;

import java.time.LocalDateTime;
import java.util.List;

import com.tiagoperroni.produto.model.Produto;
import com.tiagoperroni.produto.repository.ProdutoRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> getAll() {
        return this.produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        Produto prod = this.produtoRepository.findById(id).orElse(null);
        return prod;

    }

    public Produto findByNome(String nome) {
        Produto prod = this.produtoRepository.findByNome(nome);
        return prod;

    }

    public Produto saveProduto(Produto produto) {
        produto.setDataCadastro(LocalDateTime.now());
        return this.produtoRepository.save(produto);
    }

    public String updateProduto(Long id, Produto produto) {
        Produto prod = this.findById(id);
        if (prod != null) {
            BeanUtils.copyProperties(produto, prod, "id", "dataCadastro");
            this.produtoRepository.save(prod);
            return "Produto atualizado com sucesso.";
        } else {
            return null;
        }
    }

    public String deleteProduto(Long id) {
        Produto prod = this.findById(id);
        if (prod != null) {
            this.produtoRepository.deleteById(id);
            return "Produto deletado com sucesso.";
        } else {
            return null;
        }
    }
}
