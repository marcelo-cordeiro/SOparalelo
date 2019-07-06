package trabalho;

public class FilaCircular {
 
    final int TAMMAX;
    dados[] vetor;
    int inicio = 0, fim = 0, tamanho = 0;
    boolean available = false;

    public FilaCircular(int tamanho){
        vetor = new dados[tamanho];
        for(int i = 0;i < tamanho;i++){
            vetor[i] = new dados(0,0);
        }
        TAMMAX = tamanho;
    }
    
    public synchronized void enqueue(int x, int id) {
        
        while (available == true) {
            try {
                //wait for Consumer to get value
                wait();
            } catch (InterruptedException e) { }
        }
        
        if (tamanho == TAMMAX) {
            System.out.println("Fila Cheia!");
        } else {
            vetor[fim].valor = x;
            vetor[fim].id = id;
            fim = (fim + 1) % TAMMAX;
            tamanho++;
            if(tamanho == TAMMAX){
                 available = true;
            }
        }
         notifyAll();
    }

    public synchronized dados dequeue() {
        
        while (available == false) {
            try {
                //wait for Producer to put value
                wait();
            } catch (InterruptedException e) { }
        }
        
        if (tamanho > 0) {
            dados tmp = new dados(0,0);
            tmp.id = vetor[inicio].id;
            tmp.valor = vetor[inicio].valor;
            vetor[inicio].valor = 0;
            vetor[inicio].id = 0;
            inicio = (inicio + 1) % TAMMAX;
            tamanho--;
            notifyAll();
            return tmp;
        } else {
            System.out.println("Fila Vazia!");
            available = false;
        }
        notifyAll();
        return null;
    }

    public void mostraFila() {
        for (int i = 0; i < TAMMAX; i++) {
            if (vetor[i] != null) {
                System.out.print("[" + vetor[i].toString() + "]");
            }
        }
        System.out.println("");
    }

    public dados[] getVetor() {
        return vetor;
    }
}