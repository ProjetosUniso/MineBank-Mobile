package com.projetosuniso.digdin.service;

import com.projetosuniso.digdin.model.Endereco;
import com.projetosuniso.digdin.resource.EnderecoResource;

public class EnderecoService {

    private final EnderecoResource enderecoResource;

    public EnderecoService(){
        this.enderecoResource = new EnderecoResource();
    }

    public Endereco getID(int id){
        try {
            return enderecoResource.enderecoPodID(id);
        } catch (Exception e){
            return null;
        }
    }
    public String atualizar(int id, Endereco endereco){
        try {
            return enderecoResource.enderecoAtualizar(id, endereco);
        } catch (Exception e){
            return null;
        }
    }
    public Endereco getCEP(String cep){
        try {
            return enderecoResource.enderecoCEP(cep);
        } catch (Exception e){
            return null;
        }
    }
}
