package camilasales.camilasalesadvocacia.model;

import java.io.Serializable;

public class Processo implements Serializable {

    private String uid;
    private String tipoAcao;
    private String numeroAcao;
    private String formaPagamento;
    private String valorAcao;
    private String qtParcelasAcao;
    private String dataVencimentoAcao;
    private String prazoPagamentoAcao;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(String tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public String getNumeroAcao() {
        return numeroAcao;
    }

    public void setNumeroAcao(String numeroAcao) {
        this.numeroAcao = numeroAcao;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public String getValorAcao() {
        return valorAcao;
    }

    public void setValorAcao(String valorAcao) {
        this.valorAcao = valorAcao;
    }

    public String getQtParcelasAcao() {
        return qtParcelasAcao;
    }

    public void setQtParcelasAcao(String qtParcelasAcao) {
        this.qtParcelasAcao = qtParcelasAcao;
    }

    public String getDataVencimentoAcao() {
        return dataVencimentoAcao;
    }

    public void setDataVencimentoAcao(String dataVencimentoAcao) {
        this.dataVencimentoAcao = dataVencimentoAcao;
    }

    public String getPrazoPagamentoAcao() {
        return prazoPagamentoAcao;
    }

    public void setPrazoPagamentoAcao(String prazoPagamentoAcao) {
        this.prazoPagamentoAcao = prazoPagamentoAcao;
    }
}
