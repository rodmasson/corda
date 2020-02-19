package com.template.flows;


public class PropriedadeBean {
    private  String propriedadeEndereco;
    private  int propriedadeId;
    private  String proprietario;
    private  int compradorId;
    private  int vendedorId;
    private  String dataTime;
    private  String isHipoteca;
    private  String isAprovacaoEngenheiro;
    private  int propriedadepreco;

    public PropriedadeBean(String propriedadeEndereco, int propriedadeId, String proprietario, int compradorId, int vendedorId, String dataTime, String isHipoteca, String isAprovacaoEngenheiro, int propriedadepreco) {
        this.propriedadeEndereco = propriedadeEndereco;
        this.propriedadeId = propriedadeId;
        this.proprietario = proprietario;
        this.compradorId = compradorId;
        this.vendedorId = vendedorId;
        this.dataTime = dataTime;
        this.isHipoteca = isHipoteca;
        this.isAprovacaoEngenheiro = isAprovacaoEngenheiro;
        this.propriedadepreco = propriedadepreco;
    }

    public String getPropriedadeEndereco() {
        return propriedadeEndereco;
    }

    public void setPropriedadeEndereco(String propriedadeEndereco) {
        this.propriedadeEndereco = propriedadeEndereco;
    }

    public int getPropriedadeId() {
        return propriedadeId;
    }

    public void setPropriedadeId(int propriedadeId) {
        this.propriedadeId = propriedadeId;
    }

    public String getProprietario() {
        return proprietario;
    }

    public void setProprietario(String proprietario) {
        this.proprietario = proprietario;
    }

    public int getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(int compradorId) {
        this.compradorId = compradorId;
    }

    public int getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(int vendedorId) {
        this.vendedorId = vendedorId;
    }

    public String getDataTime() {
        return dataTime;
    }

    public void setDataTime(String dataTime) {
        this.dataTime = dataTime;
    }

    public String getIsHipoteca() {
        return isHipoteca;
    }

    public void setIsHipoteca(String isHipoteca) {
        this.isHipoteca = isHipoteca;
    }

    public String getIsAprovacaoEngenheiro() {
        return isAprovacaoEngenheiro;
    }

    public void setIsAprovacaoEngenheiro(String isAprovacaoEngenheiro) {
        this.isAprovacaoEngenheiro = isAprovacaoEngenheiro;
    }

    public int getPropriedadepreco() {
        return propriedadepreco;
    }

    public void setPropriedadepreco(int propriedadepreco) {
        this.propriedadepreco = propriedadepreco;
    }
}
