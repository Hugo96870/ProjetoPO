package woo.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;

public class GestorNotificacoes implements Serializable {

    HashMap<String, List<Observer>> _observers = new HashMap<>();

    public int getPreco(Produto p){
        return p.getPreco();
    }

    public int getQuantidade(Produto p){
        return p.getQuantidade();
    }


    public void adicionarObservers(String id, Collection<Cliente> clientes){
        List<Observer> observers = new LinkedList<>();
        for(Cliente cl: clientes)
            observers.add(cl);
        _observers.put(id, observers);
    }

    public HashMap<String, List<Observer>> getObservers(){
        return _observers;
    }

    public void removerObserversP(Observer o){
        _observers.remove(o);
    }

    public void removerObserversQ(Observer o){
        _observers.remove(o);
    }

    private void notifyObserversQ() {

    }
    private void notifyObserversP() {

    }
    public void notificacaoNew(String id, int preco){
        List<Observer> observers = _observers.get(id);
        for(Observer obs: observers){
            Cliente c = (Cliente)obs;
            c.adicionarNotificacao("NEW" + "|" + id + "|" + preco);
        }
    }
    public void notificacaoBargain(String id, int preco){
        List<Observer> observers = _observers.get(id);
        for(Observer obs: observers){
            Cliente c = (Cliente)obs;
            c.adicionarNotificacao("BARGAIN" + "|" + id + "|" + preco);
        }
    }

    public void adicionarCliente(Cliente cl){
        for(String id: _observers.keySet()){
            List<Observer> observers = _observers.get(id);
            observers.add(cl);
        }
    }

    public void NotificaPreco(Produto p){
        int preco = getPreco(p);
        notifyObserversP();
    }
    public void NotificaQuantidade(int q){
        notifyObserversQ();
    }
}