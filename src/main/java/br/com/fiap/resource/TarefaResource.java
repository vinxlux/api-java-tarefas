package br.com.fiap.resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/tarefas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TarefaResource {
    @GET
    @RolesAllowed("USER")
    public List<String> listarTodas() {
        return List.of("Estudar Quarkus", "Implementar JWT", "Tomar café ☕");
    }
}
