package com.nozama.api_nozama;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nozama.api_nozama.model.AvaliacaoProduto;
import com.nozama.api_nozama.model.CadastroEmpresa;
import com.nozama.api_nozama.model.CadastroUsuario;
import com.nozama.api_nozama.model.Categoria;
import com.nozama.api_nozama.model.EmpresaFornecedor;
import com.nozama.api_nozama.model.Endereco;
import com.nozama.api_nozama.model.Entregador;
import com.nozama.api_nozama.model.Estoque;
import com.nozama.api_nozama.model.Fornecedor;
import com.nozama.api_nozama.model.Pagamento;
import com.nozama.api_nozama.model.Pedido;
import com.nozama.api_nozama.model.PedidoEntregador;
import com.nozama.api_nozama.model.Produto;
import com.nozama.api_nozama.model.UsuarioEndereco;

@RestController
public class RESTController {
    public static <T> T executeQuery(String sql, ThrowingFunction<PreparedStatement, T> processor) {
        try (PreparedStatement pstmt = ApiNozamaApplication.getConn().prepareStatement(sql)) {

            return processor.apply(pstmt);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FunctionalInterface
    interface ThrowingFunction<P, R> {
        R apply(P p) throws SQLException;
    }

    @GetMapping(path = "/produto/{id}")
    public Produto getProduct(@PathVariable Integer id) {
        String query = "SELECT * FROM Produto WHERE ProdutoID = ?;";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return new Produto(id, rs.getInt("CategoriaID"), rs.getString("NomeProduto"), rs.getString("Descricao"),
                        rs.getBigDecimal("Preco"), rs.getString("DimensaoDaEmbalagem"),
                        rs.getString("Certificacao"), rs.getString("CodigoDoProduto"), rs.getString("PaisDeOrigem"));
            } catch (Exception e) {
                e.printStackTrace();
                return new Produto();
            }
        });
    }

    @PostMapping(path = "/produto")
    public ResponseEntity<Produto> createProduct(@RequestBody Produto newProduct) {
        String insertQuery = """
                INSERT INTO Produto
                (CategoriaID, NomeProduto, Descricao, Preco, DimensaoDaEmbalagem, Certificacao, CodigoDoProduto, PaisDeOrigem)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement pstmt = ApiNozamaApplication.getConn().prepareStatement(insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Set parameters from the request body
            pstmt.setInt(1, newProduct.getCategoriaID());
            pstmt.setString(2, newProduct.getNomeProduto());
            pstmt.setString(3, newProduct.getDescricao());
            pstmt.setBigDecimal(4, newProduct.getPreco());
            pstmt.setString(5, newProduct.getDimensaoDaEmbalagem());
            pstmt.setString(6, newProduct.getCertificacao());
            pstmt.setString(7, newProduct.getCodigoDoProduto());
            pstmt.setString(8, newProduct.getPaisDeOrigem());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            // Get the generated ID
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);

                    // Return the complete product with generated ID
                    String selectQuery = "SELECT * FROM Produto WHERE ProdutoID = ?";
                    try (PreparedStatement selectStmt = ApiNozamaApplication.getConn().prepareStatement(selectQuery)) {
                        selectStmt.setInt(1, newId);
                        try (ResultSet rs = selectStmt.executeQuery()) {
                            if (rs.next()) {
                                Produto createdProduct = new Produto(
                                        newId,
                                        rs.getInt("CategoriaID"),
                                        rs.getString("NomeProduto"),
                                        rs.getString("Descricao"),
                                        rs.getBigDecimal("Preco"),
                                        rs.getString("DimensaoDaEmbalagem"),
                                        rs.getString("Certificacao"),
                                        rs.getString("CodigoDoProduto"),
                                        rs.getString("PaisDeOrigem"));
                                return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
                            }
                        }
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/usuario/{id}")
    public CadastroUsuario getUsuario(@PathVariable Integer id) {
        String query = "SELECT * FROM CadastroUsuario WHERE UsuarioID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CadastroUsuario(
                            id,
                            rs.getString("CPF"),
                            rs.getString("Nome"),
                            rs.getString("Telefone"),
                            rs.getDate("DataNascimento").toLocalDate(),
                            rs.getString("Senha"));
                }
                return null;
            }
        });
    }

    @GetMapping("/empresa/{id}")
    public CadastroEmpresa getEmpresa(@PathVariable Integer id) {
        String query = "SELECT * FROM CadastroEmpresa WHERE EmpresaID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new CadastroEmpresa(
                            id,
                            rs.getString("CNPJ"),
                            rs.getString("Email"),
                            rs.getString("NomeRepresentante"),
                            rs.getString("Endereco"),
                            rs.getString("Senha"));
                }
                return null;
            }
        });
    }

    @GetMapping("/pedido/{id}")
    public Pedido getPedido(@PathVariable Integer id) {
        String query = "SELECT * FROM Pedido WHERE PedidoID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Pedido(
                            id,
                            rs.getInt("UsuarioID"),
                            rs.getString("DataPedido"),
                            rs.getString("StatusPedido"),
                            rs.getBigDecimal("ValorTotal"),
                            rs.getString("MetodoDePagamento"));
                }
                return null;
            }
        });
    }

    @GetMapping("/entregador/{id}")
    public Entregador getEntregador(@PathVariable Integer id) {
        String query = "SELECT * FROM Entregador WHERE EntregadorID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Entregador(
                            id,
                            rs.getString("Nome"),
                            rs.getString("Email"),
                            rs.getString("Telefone"),
                            rs.getString("Senha"));
                }
                return null;
            }
        });
    }

    @GetMapping("/fornecedor/{id}")
    public Fornecedor getFornecedor(@PathVariable Integer id) {
        String query = "SELECT * FROM Fornecedor WHERE FornecedorID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Fornecedor(
                            id,
                            rs.getString("Nome"),
                            rs.getString("Telefone"));
                }
                return null;
            }
        });
    }

    @GetMapping("/categoria")
    public List<Categoria> getAllCategories() {
        String query = "SELECT * FROM Categoria";
        return executeQuery(query, pstmt -> {
            try (ResultSet rs = pstmt.executeQuery()) {
                List<Categoria> categories = new ArrayList<>();
                while (rs.next()) {
                    categories.add(new Categoria(
                            rs.getInt("CategoriaID"),
                            rs.getString("Nome")));
                }
                return categories;
            }
        });
    }

    @GetMapping("/categoria/{id}")
    public Categoria getCategoria(@PathVariable Integer id) {
        String query = "SELECT * FROM Categoria WHERE CategoriaID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(
                            id,
                            rs.getString("Nome"));
                }
                return null;
            }
        });
    }

    // Continue with other entities...

    @GetMapping("/estoque/{id}")
    public Estoque getEstoque(@PathVariable Integer id) {
        String query = "SELECT * FROM Estoque WHERE EstoqueID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Estoque(
                            id,
                            rs.getInt("ProdutoID"),
                            rs.getInt("Quantidade"));
                }
                return null;
            }
        });
    }

    @PostMapping("/estoque")
    public ResponseEntity<Estoque> createEstoque(@RequestBody Estoque newEstoque) {
        String insertQuery = """
                INSERT INTO Estoque
                (ProdutoID, Quantidade)
                VALUES (?, ?)
                """;

        try (PreparedStatement pstmt = ApiNozamaApplication.getConn().prepareStatement(
                insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, newEstoque.getProdutoID());
            pstmt.setInt(2, newEstoque.getQuantidade());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);

                    // Return the complete stock entry
                    String selectQuery = "SELECT * FROM Estoque WHERE EstoqueID = ?";
                    try (PreparedStatement selectStmt = ApiNozamaApplication.getConn().prepareStatement(selectQuery)) {
                        selectStmt.setInt(1, newId);
                        try (ResultSet rs = selectStmt.executeQuery()) {
                            if (rs.next()) {
                                Estoque createdEstoque = new Estoque(
                                        newId,
                                        rs.getInt("ProdutoID"),
                                        rs.getInt("Quantidade"));
                                return ResponseEntity.status(HttpStatus.CREATED).body(createdEstoque);
                            }
                        }
                    }
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/pagamento/{id}")
    public Pagamento getPagamento(@PathVariable Integer id) {
        String query = "SELECT * FROM Pagamento WHERE PagamentoID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Pagamento(
                            id,
                            rs.getInt("PedidoID"),
                            rs.getBigDecimal("ValorPagamento"),
                            rs.getString("FormaDePagamento"),
                            rs.getDate("DataPagamento").toLocalDate());
                }
                return null;
            }
        });
    }

    @GetMapping("/endereco/{id}")
    public Endereco getEndereco(@PathVariable Integer id) {
        String query = "SELECT * FROM Endereco WHERE EnderecoID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Endereco(
                            id,
                            rs.getString("Rua"),
                            rs.getString("CEP"),
                            rs.getString("Numero"),
                            rs.getString("Cidade"),
                            rs.getString("Estado"),
                            rs.getString("Complemento"));
                }
                return null;
            }
        });
    }

    @GetMapping("/usuario-endereco/{id}")
    public UsuarioEndereco getUsuarioEndereco(@PathVariable Integer id) {
        String query = "SELECT * FROM UsuarioEndereco WHERE UsuarioEnderecoID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new UsuarioEndereco(
                            id,
                            rs.getInt("UsuarioID"),
                            rs.getInt("EnderecoID"));
                }
                return null;
            }
        });
    }

    @GetMapping("/pedido-entregador/{id}")
    public PedidoEntregador getPedidoEntregador(@PathVariable Integer id) {
        String query = "SELECT * FROM PedidoEntregador WHERE PedidoEntregadorID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new PedidoEntregador(
                            id,
                            rs.getInt("PedidoID"),
                            rs.getInt("EntregadorID"));
                }
                return null;
            }
        });
    }

    @GetMapping("/empresa-fornecedor/{id}")
    public EmpresaFornecedor getEmpresaFornecedor(@PathVariable Integer id) {
        String query = "SELECT * FROM EmpresaFornecedor WHERE EmpresaFornecedorID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new EmpresaFornecedor(
                            id,
                            rs.getInt("EmpresaID"),
                            rs.getInt("FornecedorID"),
                            rs.getInt("ProdutoID"));
                }
                return null;
            }
        });
    }

    @GetMapping("/avaliacao-produto/{id}")
    public AvaliacaoProduto getAvaliacaoProduto(@PathVariable Integer id) {
        String query = "SELECT * FROM AvaliacaoProduto WHERE AvaliacaoProdutoID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new AvaliacaoProduto(
                            id,
                            rs.getInt("ProdutoID"),
                            rs.getInt("UsuarioID"),
                            rs.getInt("Nota"),
                            rs.getString("Comentario"),
                            rs.getDate("DataAvaliacao") != null ? rs.getDate("DataAvaliacao").toLocalDate() : null);
                }
                return null;
            }
        });
    }

    // Additional utility endpoints

    @GetMapping("/produto/{id}/estoque")
    public Estoque getEstoqueByProduto(@PathVariable Integer id) {
        String query = "SELECT * FROM Estoque WHERE ProdutoID = ?";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Estoque(
                            rs.getInt("EstoqueID"),
                            id,
                            rs.getInt("Quantidade"));
                }
                return null;
            }
        });
    }

    @GetMapping("/pedido/{id}/produtos")
    public List<Produto> getProdutosByPedido(@PathVariable Integer id) {
        String query = """
                SELECT p.* FROM Produto p
                JOIN Produto_Pedido pp ON p.ProdutoID = pp.ProdutoID
                WHERE pp.PedidoID = ?
                """;
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                List<Produto> produtos = new ArrayList<>();
                while (rs.next()) {
                    produtos.add(new Produto(
                            rs.getInt("ProdutoID"),
                            rs.getInt("CategoriaID"),
                            rs.getString("NomeProduto"),
                            rs.getString("Descricao"),
                            rs.getBigDecimal("Preco"),
                            rs.getString("DimensaoDaEmbalagem"),
                            rs.getString("Certificacao"),
                            rs.getString("CodigoDoProduto"),
                            rs.getString("PaisDeOrigem")));
                }
                return produtos;
            }
        });
    }
}
