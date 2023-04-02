import java.time.LocalDate;
import java.util.Date;

public class Classificados {
    private String titulo;
    private String descricao;
    private double preco;
    private Date dataPublicacao;
    private String contato;
    private String categoria;
    private boolean status;
    private LocalDate dataInicio;
    private LocalDate dataTermino;

    public Classificados(String titulo, String descricao, double preco, Date dataPublicacao, String contato, String categoria, boolean status, LocalDate dataInicio, LocalDate dataTermino) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.preco = preco;
        this.dataPublicacao = dataPublicacao;
        this.contato = contato;
        this.categoria = categoria;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    // getters e setters

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Date getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(Date dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }
}
