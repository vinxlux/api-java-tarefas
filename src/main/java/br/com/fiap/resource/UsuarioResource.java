package br.com.fiap.resource;

import br.com.fiap.models.Usuario;
import br.com.fiap.security.PasswordHash;
import br.com.fiap.security.TokenService;
import jakarta.inject.Inject;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    // Simulação de um banco de dados de usuários
    private static List<Usuario> usuarios = new ArrayList<>();

    @Inject
    TokenService tokenService;

    @POST
    @Path("/cadastrar")
    @PermitAll
    public Response cadastrar(Usuario usuario) {
        usuario.setSenha(PasswordHash.hashPassword(usuario.getSenha()));
        usuarios.add(usuario); // Adiciona o usuário à "base de dados"
        return Response.status(Response.Status.CREATED).entity(usuario).build();
    }

    @POST
    @Path("/login")
    @PermitAll
    public Response login(Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarios.stream()
                .filter(u -> u.getLogin().equals(usuario.getLogin()))
                .findFirst();

        if (usuarioEncontrado.isPresent() && PasswordHash.verificarSenha(usuario.getSenha(), usuarioEncontrado.get().getSenha())) {
            String token = tokenService.generateToken(usuarioEncontrado.get());
            return Response.ok("{\"token\": \"" + token + "\"}").build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
