package estacio.mestredosmago.prototipo.processo.dtos;


import estacio.mestredosmago.prototipo.processo.Processo;

public record DadosListagemProcesso(String numProcesso, String statusProcesso) {

    public DadosListagemProcesso(Processo processo) {
        this(processo.getNumProcesso(), processo.getStatusProcesso());


    }

}