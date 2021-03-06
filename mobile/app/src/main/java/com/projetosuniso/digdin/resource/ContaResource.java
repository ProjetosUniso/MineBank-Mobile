package com.projetosuniso.digdin.resource;


import com.google.gson.Gson;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.requisicoes.conta.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class ContaResource {

    private final ClienteResource clienteResource = new ClienteResource();

    public ContaResource() {
    }


    public List<Conta> listarConta() throws JSONException, ExecutionException, InterruptedException {
        ListarConta listar = new ListarConta();
        ArrayList<Conta> listaCont = new ArrayList<>();

        JSONArray objs = listar.execute().get();

        for (int i = 0; i < objs.length(); i++) {

            JSONObject obj = objs.getJSONObject(i);

            Conta conta = convertJsonObjectToConta(obj);

            listaCont.add(conta);
        }

        return listaCont;
    }

    public Conta buscarPorID(int id) throws JSONException, ExecutionException, InterruptedException {
        ContaBuscaPorID buscaPorID = new ContaBuscaPorID(id);
        Conta conta;

        JSONObject object = buscaPorID.execute().get();

        conta = convertJsonObjectToConta(object);

        return conta;
    }

    public Conta buscarPorCPF(String cpf) throws JSONException, ExecutionException, InterruptedException {
        ContaBuscaPorCPF buscaPorCPF = new ContaBuscaPorCPF(cpf);
        Conta conta;

        JSONObject object = buscaPorCPF.execute().get();

        conta = convertJsonObjectToConta(object);

        return conta;
    }

    public boolean validarLogin (String cpf, String senha) throws ExecutionException, InterruptedException {
        String login = cpf + "&" + senha;
        ContaVerificarLogin verificar = new ContaVerificarLogin(login);
        return verificar.execute().get();
    }

    public String adicionar (Conta conta) throws JSONException, ExecutionException, InterruptedException {
        ContaAdiciona adiciona;
        String resul;

        JSONObject object = convertContaToJsonObj(conta);
        adiciona = new ContaAdiciona(object);

        resul = adiciona.execute().get();

        return resul;
    }

    public String atualiza (Conta conta, Long id) throws JSONException, ExecutionException, InterruptedException {
        ContaAtualiza atualiza;
        String resul ;

        JSONObject object = convertContaToJsonObj(conta);
        atualiza = new ContaAtualiza(object.toString(), id);

        resul = atualiza.execute().get();

        return resul;
    }

    public void atualizarSaldo (Long id, int saldo) throws ExecutionException, InterruptedException {
        String idSaldo = id + "&" + saldo;
        ContaAtualizaSaldo atualiza = new ContaAtualizaSaldo(idSaldo);
        atualiza.execute().get();
    }

    public String atualizarPoupanca (Long id, int poupanca) throws ExecutionException, InterruptedException {
        String idPoupanca = id + "&" + poupanca;
        ContaAtualizaPoupanca atualizaPoupanca = new ContaAtualizaPoupanca(idPoupanca);
        return atualizaPoupanca.execute().get();
    }


    public Conta convertJsonObjectToConta(JSONObject obj) throws JSONException {
        Conta conta = new Conta();
        JSONObject cli;
        Cliente cliente;

        conta.setId(obj.getLong("id"));
        conta.setNumero(obj.getInt("numero"));
        conta.setAgencia(obj.getInt("agencia"));
        conta.setSenha(obj.getInt("senha"));
        conta.setSaldo(obj.getDouble("saldo"));
        conta.setPoupanca(obj.getDouble("poupanca"));

        cli = obj.getJSONObject("cliente");

        cliente = clienteResource.convertJsonToCliente(cli);

        conta.setCliente(cliente);

        return conta;
    }

    private JSONObject convertContaToJsonObj(Conta conta) throws JSONException {
        JSONObject object;
        Gson g = new Gson();
        String jsonStr;

        jsonStr = g.toJson(conta);

        object = new JSONObject(jsonStr);

        return object;
    }
}