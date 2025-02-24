package Espacos;

import java.util.Collection;
import java.util.List;

public class Quadro {
    private final List<List<Espaco>> espacos;

    public Quadro(List<List<Espaco>> espacos) {
        this.espacos = espacos;
    }

    public List<List<Espaco>> getEspacos() {
        return espacos;
    }

    public boolean trocarValor(final int coluna, final int linha, final int valor){
        var espaco = espacos.get(coluna).get(linha);
        if(espaco.isFixo()) return false;

        espaco.setAtual(valor);
        return true;
    }

    public boolean limparValor(final int coluna, final int linha){
        var espaco = espacos.get(coluna).get(linha);
        if(espaco.isFixo()) return false;

        espaco.limparEspaco();
        return true;
    }

    public void resetar(){
        espacos.forEach(coluna -> coluna.forEach(Espaco::limparEspaco));
    }

    public boolean jogoFinalizado(){
        return espacos.stream()
                .flatMap(Collection::stream)
                .allMatch(espaco -> espaco.isFixo() || (espaco.getAtual() != null && espaco.getAtual().equals(espaco.getEsperado())));
    }
}