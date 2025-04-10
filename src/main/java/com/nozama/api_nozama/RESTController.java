package com.nozama.api_nozama;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nozama.api_nozama.model.Product;

@RestController
public class RESTController {
    public static <T> T executeQuery(String sql, ThrowingFunction<PreparedStatement, T> processor) {
        try (Connection conn = ApiNozamaApplication.getConn();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
    public Product getProduct(@PathVariable Integer id) {
        String query = "SELECT * FROM Produto WHERE ProdutoID = ?;";
        return executeQuery(query, pstmt -> {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                rs.next();
                return new Product(id, rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6),
                        rs.getString(7), rs.getString(8), rs.getString(9));
            } catch (Exception e) {
                e.printStackTrace();
                return new Product();
            }
        });
    }
}
