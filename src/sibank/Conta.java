/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.util.ArrayList;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Lazaro
 */
public class Conta {
    private final SimpleIntegerProperty nuConta = new SimpleIntegerProperty();    
    private final SimpleFloatProperty saConta = new SimpleFloatProperty();  
    private final SimpleIntegerProperty idCliente = new SimpleIntegerProperty(); 
    private final SimpleStringProperty daAbertura = new SimpleStringProperty();
    private final SimpleStringProperty nomeCliente = new SimpleStringProperty();
    private ArrayList <Conta> Contas;

    public Conta (int nuConta, float saConta,String nomeCliente,String daAbertura) {
        this.nuConta.set(nuConta);
        this.saConta.set(saConta);
        this.nomeCliente.set(nomeCliente);
        this.daAbertura.set(daAbertura);
        Contas = new ArrayList();

    }
    public Conta (float saConta,int idCliente,String daAbertura) {
        this.saConta.set(saConta);
        this.idCliente.set(idCliente);
        this.daAbertura.set(daAbertura);
        Contas = new ArrayList();
    }

    public int getNuConta() {
        return nuConta.get();
    }

    public void setNuConta(int nuConta) {
        this.nuConta.set(nuConta);
    }
    
    public float getSaConta() {
        return saConta.get();
    }

    public void setSaConta(float saConta) {
        this.saConta.set(saConta);
    }
    
    public int getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(int Idaluno) {
        this.idCliente.set(Idaluno);
    }

    public String getDaAbertura() {
        return daAbertura.get();
    }

    public void setDaAbertura(String daAbertura) {
        this.daAbertura.set(daAbertura);
    }
    
    public String getNomeCliente() {
        return nomeCliente.get();
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente.set(nomeCliente);
    }

    public void adL(Conta c){
        Contas.add(c);
    }
    public void imprime(Conta c){
        System.out.println("ID do Cliente: "+c.getIdCliente());
        System.out.println("Nome do Cliente: "+c.getNomeCliente());
        System.out.println("Número da Conta: "+c.getNuConta());
        System.out.println("Saldo da Conta: "+c.getSaConta());
        System.out.println("Data de Abertura: "+c.getDaAbertura());
    }
   
}
