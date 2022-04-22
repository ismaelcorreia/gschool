package api.models.enums;

public enum Status {

    DEACTIVE("Desativado"),
    ACTIVE("Ativo"),
    CREATED("Criado"),
    SUSPENSED("Suspenso"),
    DELETED("Deletado"),
    BLOCKED("Bloqueado"),
    DONE("Feito"),
    IN_PROGRESS("Em progresso"),
    PAID("Pago"),
    EXPIRED("Expirado"),
    PENDING("Pendente"),
    STOPPED("Parado");
    private String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
