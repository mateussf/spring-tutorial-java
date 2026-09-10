package br.unesp.rc.springtutorial.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.unesp.rc.springtutorial.entity.Fisica;
import br.unesp.rc.springtutorial.utils.InstanceGenerator;

/**
 * @author Prof. Dr. Frank J. Affonso
 */
@SpringBootTest
public class FisicaServiceTest {

    private Fisica entity;

    @Autowired
    private FisicaService fs;

    @Disabled
    @Test
    @DisplayName("FisicaService.save(Fisica)")
    void testSave() {
        entity = InstanceGenerator.getPessoaFisica("222.333.444-55", "user2");
        Fisica f = fs.save(entity);
        assertEquals(entity, f);
    }

    @Disabled
    @Test
    @DisplayName("FisicaService.findByCpf(cpf)")
    void testFindByCpf() {
        String cpf = "222.333.444-55";
        Fisica f = fs.findByCpf(cpf);
        System.out.println(f);
        assertNotEquals(null, f);
    }

    @Test
    @DisplayName("FisicaService.delete(Fisica)")
    public void testDelete() {
        // 1. Salva uma pessoa nova para ter algo para deletar
        entity = InstanceGenerator.getPessoaFisica("444.555.666-77", "user4");
        Fisica saved = fs.save(entity);

        // 2. Deleta
        fs.delete(saved);

        // 3. Tenta buscar de novo - deve vir null
        Fisica result = fs.findByCpf("444.555.666-77");
        assertEquals(null, result);
    }

    @Test
    @DisplayName("FisicaService.update(Fisica)")
    public void testUpdate() {
        // 1. Salva uma pessoa nova para ter algo para atualizar
        entity = InstanceGenerator.getPessoaFisica("333.444.555-66", "user3");
        Fisica saved = fs.save(entity);

        // 2. Altera um campo
        saved.setNome("Nome Atualizado");

        // 3. Chama o update
        Fisica updated = fs.update(saved);

        // 4. Confirma que o nome realmente mudou
        assertEquals("Nome Atualizado", updated.getNome());
    }

    @Disabled
    @Test
    @DisplayName("FisicaService.findAll()")
    public void testFindAll() {
        List<Fisica> result = fs.findAll();
        for (Fisica f : result) {
            System.out.println("FISICA: " + f);
        }
        assertNotEquals(null, result);
    }
}