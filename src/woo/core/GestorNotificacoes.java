package woo.core;

import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class GestorNotificacoes{
    
    HashMap<String, List<Observer>> _observersQuantidades = new HashMap<>();
    HashMap<String, List<Observer>> _observersPreco = new HashMap<>();

    public int getPreco(Produto p){
        return p.getPreco();
    }

    public int getQuantidade(Produto p){
        return p.getQuantidade();
    }
    public void adicionarObserversQ(Observer o){

    }

    public void adicionarObserversP(Observer o){

    }

    public void removerObserversP(Observer o){
        _observersPreco.remove(o);
    }

    public void removerObserversQ(Observer o){
        _observersQuantidades.remove(o);
    }

    private void notifyObserversQ() {

    }
    private void notifyObserversP() {

    }

    public void NotificaPreco(Produto p){
        int preco = getPreco(p);
        notifyObserversP();
    }
    public void NotificaQuantidade(int q){
        notifyObserversQ();
    }
}