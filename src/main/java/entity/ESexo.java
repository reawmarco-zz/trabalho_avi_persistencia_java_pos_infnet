package entity;

public enum ESexo {

    MASCULINO("M"),
    FEMININO("F");

    private String sexo;

    ESexo(String sexo){
        this.sexo = sexo;
    }

    public String getSexo(){
        return sexo;
    }
}
