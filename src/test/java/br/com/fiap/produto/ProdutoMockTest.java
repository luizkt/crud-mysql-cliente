package br.com.fiap.produto;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.ProdutoRepository;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoMockTest {

    @Mock
    private ProdutoRepository produtoRepo;

    @Test
	public void shouldFindCarByColor() {

    	/*
    	 * atributos de produto
    	 */
		String codigo = "123";

		String desc = "asd";

		// objeto de produto

		Produto produto = new Produto();

		// setters de produto

		produto.setCodigo(codigo);
		produto.setDesc(desc);
		
		// cria lista vazia de produtos
		List<Produto> produtos = Arrays.asList(produto);


		// quando buscar um produto pelo codigo
		// retornar os produtos com o mesmo codigo, no caso, retorna apenas um

		Mockito.when(produtoRepo.findByCode(codigo)).thenReturn(produtos);


		// popula a lista de produtos com o resultado

		List<Produto> prodResponse = produtoRepo.findByCode(codigo);

		// pega apenas o primeiro indice da lista e realiza a comparação

		assertEquals(prodResponse.get(0).getCodigo(), codigo);

		assertEquals(prodResponse.get(0).getDesc(), desc);

	}

}
