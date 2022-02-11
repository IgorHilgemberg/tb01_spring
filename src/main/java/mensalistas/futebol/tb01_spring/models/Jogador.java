package mensalistas.futebol.tb01_spring.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "jogador")

public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cod_jogador;

    @Column(nullable = false, length = 60)
    private String nome;

    @Column(nullable = false, length = 60)
    private String email;

    // @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private Date dataNasc;

    @OneToMany(mappedBy = "cod_jogador")
    private List<Pagamento> pagamentos;

    public Jogador() {

    }

    public Jogador(String nome, String email, Date dataNasc) {
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
    }

    public int getCod_jogador() {
        return cod_jogador;
    }

    public void setCod_jogador(int cod_jogador) {
        this.cod_jogador = cod_jogador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date data) {
        this.dataNasc = data;
    }

    @Override
    public String toString() {
        return "Jogador{" + "cod_jogador=" + cod_jogador + ", nome=" + nome + ", email=" + email + ", data=" + dataNasc + '}';
    }
}
