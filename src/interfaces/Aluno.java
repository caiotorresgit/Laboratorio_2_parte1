package interfaces;

class Aluno {
    private String nome;
    private String cpf;
    private String matricula;
    private String vertente;

    public Aluno(String nome, String cpf, String matricula, String vertente) {
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.vertente = vertente;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getVertente() {
        return vertente;
    }
    public void setVertente(String vertente) {
        this.vertente = vertente;
    }
}
