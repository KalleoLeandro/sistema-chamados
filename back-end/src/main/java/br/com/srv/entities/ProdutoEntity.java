package br.com.srv.entities;

import java.math.BigDecimal;

import br.com.srv.models.requests.ProdutoRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;
    
    @Column(name = "preco_compra")
    private BigDecimal preco_compra;
    
    @Column(name = "preco_venda")
    private BigDecimal preco_venda;
    
    @Column(name = "quantidade")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "id_medida", nullable = false)
    private MedidaEntity medida;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private CategoriaEntity categoria;  
    
    public ProdutoEntity(ProdutoRequest produtoRequest) {
    	this.id = produtoRequest.getId();
    	this.nome = produtoRequest.getNome();
    	this.preco_compra = produtoRequest.getPreco_compra();
    	this.preco_venda = produtoRequest.getPreco_venda();
    	this.quantidade = produtoRequest.getQuantidade();
    	
    }
}