package br.com.srv;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BackEndSrvApplicationTest {

    @Test
    void contextLoads() {
        // Este teste apenas verifica se o contexto da aplicação é carregado corretamente
        BackEndSrvApplication.main(new String[] {});
    }
}
