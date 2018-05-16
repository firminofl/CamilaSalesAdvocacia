package camilasales.camilasalesadvocacia.model.entidades;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PessoaFisicaTest {

    @Test
    public void inicializa_Test(){
        PessoaFisica pessoaFisica = new PessoaFisica();

        pessoaFisica.setNome("Oi");
        assertEquals("Oi", pessoaFisica.getNome());
    }

}
