package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserDatabase {
    private static final Map<String, String> users = new HashMap<>();

    // Adiciona usuários padrão para teste
    static {
        users.put("admin", "1234");
        users.put("Guilherme", "456");
        users.put("a", "1");
    }

    // Método para validar login
    public static boolean validateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    // Método para cadastrar um novo usuário
    public static boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // Usuário já existe
        }
        users.put(username, password);
        return true;
    }
}
