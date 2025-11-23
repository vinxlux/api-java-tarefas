package br.com.fiap.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordHash {
    public static String hashPassword(String senha) {
        String salt = BCrypt.gensalt(10);
        return BCrypt.hashpw(senha, salt);
    }

    public static boolean verificarSenha(String senha, String hashPassword) {
        return BCrypt.checkpw(senha, hashPassword);
    }
}
