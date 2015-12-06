/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lazaro
 */
public class Transacao {
    private final SimpleIntegerProperty idMov = new SimpleIntegerProperty();    
    private final SimpleStringProperty dataMov = new SimpleStringProperty();
    private final SimpleIntegerProperty valorMov = new SimpleIntegerProperty();
    private final SimpleStringProperty tipoMov = new SimpleStringProperty();
    private final SimpleIntegerProperty numConta = new SimpleIntegerProperty();
    private final SimpleIntegerProperty saldoAnt = new SimpleIntegerProperty();

public Transacao (int idMov,int numConta, int valorMov,String tipoMov,String dataMov) {
        this.idMov.set(idMov);
        this.dataMov.set(dataMov);
        this.valorMov.set(valorMov);
        this.tipoMov.set(tipoMov);
        this.numConta.set(numConta);

    }
 
public Transacao (int idMov,int numConta, int valorMov,int saldoAnt, String tipoMov,String dataMov) {
        this.idMov.set(idMov);
        this.dataMov.set(dataMov);
        this.valorMov.set(valorMov);
        this.tipoMov.set(tipoMov);
        this.numConta.set(numConta);
        this.saldoAnt.set(saldoAnt);

    }
    public int getIdMov() {
        return idMov.get();
    }

    public void setIdMov(int IdMov) {
        this.idMov.set(IdMov);
    }

    public String getDataMov() {
        return dataMov.get();
    }

    public void setDataMov(String DataMov) {
        this.dataMov.set(DataMov);
    }

    public int getValorMov() {
        return valorMov.get();
    }

    public void setValorMov(int valorMov) {
        this.valorMov.set(valorMov);
    }

    public String getTipoMov() {
        return tipoMov.get();
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov.set(tipoMov);
    }
    
    public int getNumConta() {
        return numConta.get();
    }

    public void setNumConta(int numConta) {
        this.numConta.set(numConta);
    }
    
    public int getSaldoAnt() {
        return saldoAnt.get();
    }

    public void setSaldoAnt(int saldoAnt) {
        this.saldoAnt.set(saldoAnt);
    }
    
    public void imp(Transacao tr){
        System.out.println("ID: "+tr.getIdMov());
        System.out.println("Tipo: "+tr.getTipoMov());
        System.out.println("Número da Conta: "+tr.getNumConta());
        System.out.println("Valor: "+tr.getValorMov());
        System.out.println("Data: "+tr.getIdMov());
    }
   
}


