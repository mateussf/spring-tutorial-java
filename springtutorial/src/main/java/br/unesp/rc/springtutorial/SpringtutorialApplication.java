package br.unesp.rc.springtutorial;

import br.unesp.rc.springtutorial.entity.Fisica;
import br.unesp.rc.springtutorial.service.FisicaService;
import br.unesp.rc.springtutorial.utils.InstanceGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Prof. Dr. Frank J. Affonso
 */
@SpringBootApplication
public class SpringtutorialApplication implements CommandLineRunner {

    @Autowired
    private FisicaService fs;

    public static void main(String[] args) {
        SpringApplication.run(SpringtutorialApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Fisica entity = InstanceGenerator.getPessoaFisica("111.222.333-44", "user1");
        Fisica saved = fs.save(entity);
        System.out.println("\nPessoa salva no banco:\n" + saved + "\n");
    }
}