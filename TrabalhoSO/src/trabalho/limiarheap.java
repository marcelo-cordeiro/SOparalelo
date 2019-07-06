package trabalho;

import java.util.Queue;

public class limiarheap {
    int tamanhoheap,limiar,linhasutilizadas;

    public limiarheap(int tamanhoheap, int limiar) {
        this.tamanhoheap = tamanhoheap;
        this.limiar = limiar;
    }
    public int porcentagem(int dOcupado){
        int bloco=-1;
        int porcentagem = ((tamanhoheap*4) * limiar) / 100;
        int min = ((tamanhoheap*4) * 50) / 100;
        if(dOcupado >= porcentagem){
            bloco=dOcupado;
            while(bloco > min){
                bloco=bloco-1;
            }
        }
        return bloco;
    }
}
