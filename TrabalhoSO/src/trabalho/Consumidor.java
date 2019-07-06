package trabalho;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.util.Queue;

public class Consumidor implements Runnable {
        FilaCircular fc;
        int tamanho;
        int limiarPorcentagem;
        int tamanhoheap;
        int ciclos;
        DefaultTableModel model2;
        Queue dLivre;
        Queue dOcupado;
        int[] vet;
        dados retirado;
        dadosocupado d;
        dados1 matriz;
        dadosocupado ocup = new dadosocupado();
        ArrayList<dados1> itensadd = new ArrayList<>();
        long tempo = 0;

    public Consumidor(FilaCircular fc,int ciclos,int limiarPorcentagem, int tamanhoheap, DefaultTableModel model2, Queue dLivre, Queue dOcupado, int[] vet) {
        this.fc = fc;
        this.limiarPorcentagem = limiarPorcentagem;
        this.tamanhoheap = tamanhoheap;
        this.model2 = model2;
        this.dLivre = dLivre;
        this.dOcupado = dOcupado;
        this.vet = vet;
        this.ciclos = ciclos;
    }

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }
         
    @Override
    public void run() {
        long tempoInicio = System.currentTimeMillis(); //função que calcula o tempo de execução.
        for(int cont = 0; cont < ciclos; cont++){
            retirado = fc.dequeue();
            if(retirado != null){
                tamanho = retirado.valor;

                   d=(dadosocupado)dLivre.poll();

                   ocup.id=retirado.id;
                   int flag=0;
                   for(int z=0;z < tamanho ;z++){
                       if(!(d.posicao.isEmpty())){
                           itensadd.add(d.posicao.remove(0));    
                       }
                       else{
                           if(dLivre.peek()!=null){
                               d=(dadosocupado)dLivre.poll();
                               z--;
                           }
                       }
                   }
                   if(flag==0){
                   for(int z=0;z < tamanho ;z++){
                       desalocador ddd = new desalocador();
                       ddd.desalocador(dLivre,dOcupado, model2, tamanhoheap, limiarPorcentagem,vet);
                               dados1 e = new dados1(0,0);
                               matriz=itensadd.remove(0);
                               model2.setValueAt(retirado.id,matriz.ini,matriz.fim);
                               e.ini=matriz.ini;
                               d.id--;
                               e.fim=matriz.fim;
                               vet[0]++;
                               ocup.posicao.add(e);
                    }
                    dLivre.offer(d);
                    dOcupado.offer(ocup);
                    }
            }
            else{
                cont--;
            }
        }
        setTempo(System.currentTimeMillis()-tempoInicio);
        System.out.println(getTempo());
    }
}
