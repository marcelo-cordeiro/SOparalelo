package trabalho;

import java.util.Queue;
import javax.swing.table.DefaultTableModel;

public class desalocador {
    public void desalocador(Queue dLivre,Queue dOcupado,DefaultTableModel model2,int tamanho,int limiarPorcentagem,int[] inf){
        limiarheap limiar = new limiarheap(tamanho,limiarPorcentagem);
        int l=0;
        dadosocupado dados;
        dados1 dados1;
        dadosocupado dadoslivres = new dadosocupado();
        l = limiar.porcentagem(inf[0]);
        for(int k=0;k<=l;k++){
                dados = (dadosocupado) dOcupado.poll();
                if(dados!=null){
                dados1 d;
                int tam = dados.posicao.size();
                for(int w=0;w<tam;w++){
                    k++;
                    d=dados.posicao.remove(0);
                    dados1 e = new dados1(d.ini,d.fim);
                    dadoslivres.id++;
                    dadoslivres.posicao.add(e);
                    dLivre.add(dadoslivres);
                    inf[0]--;
                    model2.setValueAt("-",d.ini,d.fim);
                }
                }
                else{
                    break;
                }
        }
    }
}
