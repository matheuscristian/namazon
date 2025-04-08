package com.nozama.api_nozama;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(path = "/test")
    public String test() {
        ArrayList<String> l = new ArrayList<String>();
        String query = "SHOW TABLES;";
        Statement stmt = null;
        try {
            stmt = ApiNozamaApplication.getConn().createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String tableName = rs.getString(1);
                l.add(tableName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return String.join(" ", l);
    }
}
