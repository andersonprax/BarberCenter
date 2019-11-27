package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import selenium.*;
import repositorio.*;

@RunWith(Suite.class)
@SuiteClasses({
	TesteFunionalidadeCadastrarCliente.class,
	TesteFuncionalidadeAtualizarCliente.class,
	TesteFuncionalidadeRemoverCliente.class,
	ClienteRepositorioTest.class
})
public class SuiteTestesCliente {
	
}
