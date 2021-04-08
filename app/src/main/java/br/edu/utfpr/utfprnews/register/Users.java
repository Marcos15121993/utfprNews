package br.edu.utfpr.utfprnews.register;

public class Users {
    private String nome;
    private String  sobrenome;
    private String senha;
    private String Email;


    public Users(String nome, String sobrenome, String senha, String email) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.senha = senha;
        Email = email;
    }

    public Users() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
