package trabalho;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class Produtor implements Runnable{
    private int tamanho,max,min;
    Random r = new Random();
    DefaultTableModel model1;
    DefaultTableModel model2;
    FilaCircular fc;
    int ciclos;
    long tempo = 0;
    public Produtor(FilaCircular fc, int tamanho, int max, int min,DefaultTableModel model1,DefaultTableModel model2, int ciclos) {
        this.tamanho = tamanho;
        this.max = max;
        this.min = min;
        this.fc = fc;
        this.model1 = model1;
        this.model2 = model2;
        this.ciclos = ciclos;
    }
    
    /*public void produzir(FilaCircular fc, int id,int max,int min,DefaultTableModel model1,DefaultTableModel model2) {
        tamanho=min + r.nextInt(max - min);
        fc.enqueue(tamanho,id);
        model1.addRow(new Object[]{id,tamanho});
    }*/

    public int getTamanho() {
        return tamanho;
    }

    public int getMax() {
        return max;
    }

    public int getMin() {
        return min;
    }

    public FilaCircular getFc() {
        return fc;
    }     

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public long getTempo() {
        return tempo;
    }

    public void setTempo(long tempo) {
        this.tempo = tempo;
    }
    
    @Override
    public void run(){
        //FUNÇÃO QUE GERA OS ID RANDOM SEM REPETIR
        List numeros = new ArrayList();
        for (int i = 0; i < ciclos; i++) {
            numeros.add(i+1);
        }
        
        Collections.shuffle(numeros);
        long tempoInicio = System.currentTimeMillis(); //função que calcula o tempo de execução.
        for(int cont = 0; cont < ciclos; cont++){
            setTamanho(getMin() + r.nextInt(getMax() - getMin()));
            fc.enqueue(getTamanho(),Integer.parseInt(String.valueOf(numeros.get(cont))));
            model1.addRow(new Object[]{Integer.parseInt(String.valueOf(numeros.get(cont))),getTamanho()});
        }
        setTempo(System.currentTimeMillis()-tempoInicio);
    }
}
