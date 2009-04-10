package AVL;

class Nodo {
    private int dado;
    private Nodo esquerda;
    private Nodo direita;
    private int altura;
    
    Nodo(int elemento) {
        dado  = elemento;        
        esquerda     = null;
        direita    = null;
        altura   = 0;
    }
    
    public int getDado() {
        return dado;
    }
    
    public void setDado(Integer dado) {
        this.dado = dado;
    }
    
    public Nodo getEsquerda() {
        return esquerda;
    }
    
    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }
    
    public Nodo getDireita() {
        return direita;
    }
    
    public void setDireita(Nodo direita) {
        this.direita = direita;
    }
    
    public int getAltura() {
        return altura;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
}