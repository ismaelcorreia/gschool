package api.models.enums;

public enum UserRole {
    Administrator("Administrador"),
    Operator("Operador"),
    Visitant("Visitante"),
    Assistent("Assistente"),
    Public("Público"),
    Client("Cliente");
    private String name;

    UserRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
