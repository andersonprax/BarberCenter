package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import selenium.*;
import repositorio.*;

//Suite de classes de testes a serem exuctados
@RunWith(Suite.class)
@SuiteClasses({
	TesteFunionalidadeCadastrarCliente.class,
	TesteFuncionalidadeAtualizarCliente.class,
	TesteFuncionalidadeRemoverCliente.class,
	ClienteRepositorioTest.class
})
/**
 * 
 * @author pedro.silva
 *
 */
public class SuiteTestesCliente{}
