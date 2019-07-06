package trabalho;

public class dados1 {
    
    int ini;
    int fim;

    public dados1(int ini, int fim) {
        this.ini = ini;
        this.fim = fim;
    }
    @Override
    public String toString() {
        return ini +","+fim;
    }
}
