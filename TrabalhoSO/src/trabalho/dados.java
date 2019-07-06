package trabalho;

public class dados {
    int valor;
    int id;

    public dados(int valor, int id) {
        this.valor = valor;
        this.id = id;
    }
  
    @Override
    public String toString() {
        return valor +","+id;
    }
    
}
