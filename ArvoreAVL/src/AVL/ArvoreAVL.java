package AVL;

public class ArvoreAVL {    
    Nodo raiz;    
    public ArvoreAVL( ) {
        raiz = null;
    }
    
    public boolean eVazio( ) {
        return raiz == null;
    }
    
    public Nodo inserir( int  dado, Nodo atual ) {
        if (atual == null ) // se não tiver nada cria raiz
            atual = new Nodo(dado);
        else if( dado <  atual.getDado()) { // se novo menor que o existente
            atual.setEsquerda(inserir(dado, atual.getEsquerda())); // insere recursivamente
            if (altura(atual.getEsquerda()) - altura(atual.getDireita()) == 2 ) // se houver diferença igual a 2
                if(dado <  atual.getEsquerda().getDado()) // se novo é menor que esquerda do atual
                    atual = girarFilhoEsquerda (atual);  // faz giro
                else
                    atual = duploFilhoEsquerda (atual);  // faz giro
        
        } else if( dado > atual.getDado()) { // se novo maior que o existente
            atual.setDireita(inserir( dado, atual.getDireita() )); // insere na direita
            if( altura( atual.getDireita()) - altura( atual.getEsquerda()) == 2 ) // se houver diferença na altura igual a 2
                if( dado >  atual.getDireita().getDado()) // se novo é menor que direita do atual
                    atual = girarFilhoDireita( atual ); // faz giro
                else
                    atual = duploFilhoDireita( atual ); // faz giro
        } else
            ;  // nao faz nada
        atual.setAltura (maximo(altura( atual.getEsquerda()), altura( atual.getDireita())) + 1); //seta altura do nodo q foi inserido
        return atual;
    }
    
    public boolean eBalanceada(Nodo nodo) { // verifica se a árvore está balanceada
        Nodo atual = nodo;
        while (atual != null) { // verifica nós da Direita
            if (altura(atual.getDireita()) - altura( atual.getEsquerda()) == 2) {
                atual = atual.getDireita();
                return false;
            }
            atual = atual.getDireita();
        }
        atual = nodo;
        while (atual != null) { // verifica nós da Direita
            if (altura(atual.getDireita()) - altura( atual.getEsquerda()) == 2) {
                atual = atual.getEsquerda();
                return false;
            }
            atual = atual.getDireita();
        }
        return true;
    }
    
    public void efetuaBalanceamento(Nodo nodo) {    // efetua balançeamento efetuando os giros
        if (nodo.getDado() >  nodo.getDireita().getDado())
            raiz = girarFilhoDireita (raiz); // giro se dado da raiz maior que dado da direita
        else
            raiz = duploFilhoDireita(raiz); // giro se dado da raiz menor que dado da direita
    }
    
    public boolean remover (int dado, Nodo nodo) { // método de remoção 
        Nodo atual = nodo;
        Nodo anterior = nodo;
        boolean eFilhoEsquerda = true;
        while (atual.getDado() !=dado) {
            anterior = atual;
            if (dado < atual.getDado()) { // vai pra esquerda?
                eFilhoEsquerda = true; 
                atual = atual.getEsquerda();
            } else { // ou direita?
                eFilhoEsquerda = false;
                atual = atual.getDireita();
            }
            if (atual == null) 
                return false;
        }
        if ((atual.getEsquerda()) == null && (atual.getDireita() == null)) { // se ambos os lados forem nulos
            if (atual == raiz) // se for raiz árvore é vazia
                raiz = null;
            else if (eFilhoEsquerda) // verifica se é filho esquerda ou não
                anterior.setEsquerda(null); // remove
            else
                anterior.setDireita(null); // remove
        } 
        else if (atual.getDireita() == null) // se esquerda é nulo
            if (atual ==  raiz) 
                raiz = atual.getEsquerda();
            else
                if (eFilhoEsquerda) // verifica  se é filho esquerda
                    anterior.setEsquerda(atual.getEsquerda());
                else
                    anterior.setDireita(atual.getEsquerda());
        else if (atual.getEsquerda() == null) // se esquerda é nulo
            if (atual == raiz) 
                raiz = atual.getDireita(); 
            else
                if (eFilhoEsquerda) // verifica se é filho esquerda
                    anterior.setEsquerda(atual.getDireita());
                else
                    anterior.setDireita(atual.getDireita());
        else { // se nem esquerda nem direita ou ambos forem nulos, entra aqui
            Nodo sucessor = getSucessor(atual); // pega o sucessor
            if (atual == raiz)
                raiz = sucessor;
            else if (eFilhoEsquerda) 
                anterior.setEsquerda(sucessor);
            else 
                anterior.setDireita(sucessor);
            sucessor.setEsquerda(atual.getEsquerda());
            }
        if (eBalanceada(raiz))          // se estiver balanceada remoção está terminada
            return true;
        else {
            efetuaBalanceamento(raiz); // se não efetua giros para balanceamento
            return true;
        }
    }
    
    public Nodo getSucessor(Nodo nodo) {  // pega o sucessor
        Nodo sucessorAnterior = nodo;
        Nodo sucessor = nodo;
        Nodo atual = nodo.getDireita(); // vai para filho da direita
        while (atual != null) { // enquanto houver
            sucessorAnterior = sucessor; 
            sucessor = atual.getEsquerda(); 
        }
        
        if (sucessor != nodo.getDireita()) {
            sucessorAnterior.setEsquerda(sucessor.getDireita());
            sucessor.setDireita(nodo.getDireita());
        }
        return sucessor;
    }
    
    public Nodo procurar( int dado, Nodo t ) { // método de procura
        while(t!= null )
            if(dado < t.getDado())
                t = t.getEsquerda();
            else if( dado >  t.getDado())
                t = t.getDireita();
            else
                return t; // se achar retorna t
        return null; // se não achar retorna null
    }
    
    public static int altura( Nodo t ) { // retorna a altura
        return t == null ? -1 : t.getAltura();
    }
    
    public static int maximo (int lhs, int rhs) { // retorna maxima
        return lhs > rhs ? lhs : rhs;
    }
    
    public static Nodo girarFilhoEsquerda (Nodo k2) { // giro
        Nodo k1 = k2.getEsquerda();
        k2.setEsquerda(k1.getDireita());
        k1.setDireita(k2);
        k2.setAltura( maximo( altura( k2.getEsquerda() ), altura( k2.getDireita())) + 1);
        k1.setAltura(maximo( altura( k1.getEsquerda() ), k2.getAltura()) + 1);
        return k1;
    }
    
    public static Nodo girarFilhoDireita( Nodo k1 ) { // giro
        Nodo k2 = k1.getDireita();
        k1.setDireita(k2.getEsquerda());
        k2.setEsquerda(k1);
        k1.setAltura( maximo( altura( k1.getEsquerda()), altura( k1.getDireita()) ) + 1);
        k2.setAltura( maximo( altura( k2.getDireita() ), k1.getAltura()) + 1);
        return k2;
    }
    
    public static Nodo duploFilhoEsquerda( Nodo k3 ) {
        k3.setEsquerda(girarFilhoDireita(k3.getEsquerda()));
        return girarFilhoEsquerda( k3);
    }
    
    public static Nodo duploFilhoDireita( Nodo k1 ) {
        k1.setDireita(girarFilhoEsquerda( k1.getDireita() ));
        return girarFilhoDireita( k1 );
    }
    
    void inserir(int dado) { // inserir que chama inserir com parametro dado e raiz
        raiz = inserir(dado, raiz);
    }
    
    public boolean procurar( int dado) { // procurar que chama procurar com parametro dado e raiz
        if (procurar(dado, raiz) == null)
            return false;
        return true;
    }
    
    public static void main( String [ ] args ) {
        ArvoreAVL t = new ArvoreAVL();        
        t.inserir(20);
        t.inserir(15);
        t.inserir(7);
        t.inserir(25);
        t.inserir(20);
        t.inserir(22);
                
        t.remover(7, t.raiz);
        t.remover(22, t.raiz);
        System.out.print("" + t.eBalanceada(t.raiz));
    }
}  