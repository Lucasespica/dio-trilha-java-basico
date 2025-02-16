public class Iphone {
    private ReprodutorMusical reprodutorMusical;
    private AparelhoTelefonico aparelhoTelefonico;
    private NavegadorInternet navegadorInternet;

    public Iphone() {
        this.reprodutorMusical = new ReprodutorMusical();
        this.aparelhoTelefonico = new AparelhoTelefonico();
        this.navegadorInternet = new NavegadorInternet();
    }

    public void iniciar(String operacao) {
        switch (operacao) {
            case "tocarMusica":
                reprodutorMusical.tocar();
                break;
            case "pausarMusica":
                reprodutorMusical.pausar();
                break;
            case "selecionarMusica":
                reprodutorMusical.selecionarMusica("Musica Exemplo");
                break;
            case "ligar":
                aparelhoTelefonico.ligar("123456789");
                break;
            case "atender":
                aparelhoTelefonico.atender();
                break;
            case "iniciarCorreioVoz":
                aparelhoTelefonico.iniciarCorreioVoz();
                break;
            case "exibirPagina":
                navegadorInternet.exibirPagina("http://exemplo.com");
                break;
            case "adicionarNovaAba":
                navegadorInternet.adicionarNovaAba();
                break;
            case "atualizarPagina":
                navegadorInternet.atualizarPagina();
                break;
            default:
                System.out.println("Operação não reconhecida");
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        Iphone iphone = new Iphone();
        iphone.iniciar("tocarMusica");
        iphone.iniciar("ligar");
        iphone.iniciar("exibirPagina");
    }
}