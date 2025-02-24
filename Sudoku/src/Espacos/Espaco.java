package Espacos;

public class Espaco {

    private Integer atual;
    private final int esperado;
    private final boolean fixo;

    public Espaco(final int esperado, boolean fixo) {
        this.esperado = esperado;
        this.fixo = fixo;
        if(fixo){
            atual = esperado;
        }
    }

    public Integer getAtual() {
        return atual;
    }

    public void setAtual(Integer atual) {
        if(fixo) return;
        this.atual = atual;
    }

    public void limparEspaco(){
        setAtual(null);
    }
    
    public int getEsperado() {
        return esperado;
    }

    public boolean isFixo() {
        return fixo;
    }
}
