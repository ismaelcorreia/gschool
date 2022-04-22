package api.models.generics;

import java.time.LocalDateTime;

public abstract class GenericEntity extends GenericId{

    private String nome;
    private String apelido;
    private String email;
    private String telefone;
    private LocalDateTime data;
    private String pais;
    private String cidade;
    private String rua;
    private String provincia;
    private String fotografia;


}