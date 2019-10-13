package br.com.fiap.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.fiap.entity.Cliente;
import br.com.fiap.model.ClienteJson;
import br.com.fiap.model.EnderecoJson;
import br.com.fiap.repository.ClienteRepository;

@RestController
@RequestMapping(path = "/cliente")
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> add(@Valid @RequestBody Map<String, Object> payload) {

		try {

			ObjectMapper mapper = new ObjectMapper();
			ClienteJson clienteJson = mapper.convertValue(payload, ClienteJson.class);
			Cliente cliente = new Cliente();

			cliente.setCpf(clienteJson.getCpf());
			cliente.setNome(clienteJson.getNome());
			cliente.setUuid(clienteJson.getUuid());
			cliente.setEmail(clienteJson.getEmail());
			cliente.setDataNascimento(clienteJson.getDataNascimento());
			cliente.setRua(clienteJson.getEndereco().getRua());
			cliente.setBairro(clienteJson.getEndereco().getBairro());
			cliente.setNumero(clienteJson.getEndereco().getNumero());
			cliente.setCidade(clienteJson.getEndereco().getCidade());
			cliente.setEstado(clienteJson.getEndereco().getEstado());
			cliente.setCep(clienteJson.getEndereco().getCep());
			cliente.setPais(clienteJson.getEndereco().getPais());

			clienteRepository.save(cliente);

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Cliente adicionado com sucesso\"}";

			return new ResponseEntity<>(body, headers, HttpStatus.CREATED);

		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Ocorreu um erro\", \"Exceção\":" + e.getMessage() + "}";

			return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
		}

	}

	@Transactional
	@RequestMapping(path = "/update/{cpf}", method = RequestMethod.PATCH)
	@ResponseBody
	public ResponseEntity<String> updateClienteById(@RequestBody Map<String, Object> payload,
			@PathVariable("cpf") String cpf) {

		try {

			List<Cliente> clienteList = clienteRepository.findByDocument(cpf);

			ObjectMapper mapper = new ObjectMapper();
			ClienteJson clienteJson = mapper.convertValue(payload, ClienteJson.class);

			clienteList.forEach(cliente -> {
				cliente.setCpf(cpf);
				cliente.setUuid(cliente.getUuid());
				cliente.setNome(clienteJson.getNome() == null || clienteJson.getNome().isEmpty()
						? cliente.getNome()
						: clienteJson.getNome());
				cliente.setEmail(clienteJson.getEmail() == null || clienteJson.getEmail().isEmpty()
						? cliente.getEmail()
						: clienteJson.getEmail());
				cliente.setDataNascimento(clienteJson.getDataNascimento() == null || clienteJson.getDataNascimento().isEmpty()
						? cliente.getDataNascimento()
						: clienteJson.getDataNascimento());
				if(clienteJson.getEndereco() != null) {
					cliente.setRua(clienteJson.getEndereco().getRua() == null || clienteJson.getEndereco().getRua().isEmpty()
							? cliente.getRua()
							: clienteJson.getEndereco().getRua());
					cliente.setBairro(clienteJson.getEndereco().getBairro() == null || clienteJson.getEndereco().getBairro().isEmpty()
							? cliente.getBairro()
							: clienteJson.getEndereco().getBairro());
					cliente.setNumero(clienteJson.getEndereco().getNumero() == null || clienteJson.getEndereco().getNumero().isEmpty()
							? cliente.getNumero()
							: clienteJson.getEndereco().getNumero());
					cliente.setCidade(clienteJson.getEndereco().getCidade() == null || clienteJson.getEndereco().getCidade().isEmpty()
							? cliente.getCidade()
							: clienteJson.getEndereco().getCidade());
					cliente.setEstado(clienteJson.getEndereco().getEstado() == null || clienteJson.getEndereco().getEstado().isEmpty()
							? cliente.getEstado()
							: clienteJson.getEndereco().getEstado());
					cliente.setCep(clienteJson.getEndereco().getCep() == null || clienteJson.getEndereco().getCep().isEmpty()
							? cliente.getCep()
							: clienteJson.getEndereco().getCep());
					cliente.setPais(clienteJson.getEndereco().getPais() == null || clienteJson.getEndereco().getPais().isEmpty()
							? cliente.getPais()
							: clienteJson.getEndereco().getPais());
				}
				
				clienteRepository.save(cliente);
			});

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Cliente atualizado com sucesso\"}";

			return new ResponseEntity<>(body, headers, HttpStatus.OK);

		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Ocorreu um erro\", \"Exceção\":" + e.getMessage() + "}";

			return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
		}

	}

	@Transactional
	@RequestMapping(path = "/delete/{cpf}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<String> deleteByCpf(@PathVariable String cpf) {

		try {

			List<Cliente> clienteList = clienteRepository.findByDocument(cpf);

			clienteList.forEach(cliente -> {
				clienteRepository.deleteById(cliente.getClienteId());
			});

			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Cliente excluido com sucesso\"}";

			return new ResponseEntity<>(body, headers, HttpStatus.OK);

		} catch (Exception e) {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
			String body = "{\"Mensagem\":\"Ocorreu um erro\", \"Exceção\":" + e.getMessage() + "}";

			return new ResponseEntity<>(body, headers, HttpStatus.BAD_REQUEST);
		}

	}

	@Transactional(readOnly = true)
	@RequestMapping(path = "/all", method = RequestMethod.GET)
	@ResponseBody
	public Iterable<ClienteJson> getAllUsers() {
		
		List<ClienteJson> clientesJson = new ArrayList<>();
		
		clienteRepository.findAll().forEach(cliente -> {
			ClienteJson clienteJson = new ClienteJson();
			EnderecoJson enderecoJson = new EnderecoJson();
			
			clienteJson.setCpf(cliente.getCpf());
			clienteJson.setDataNascimento(cliente.getDataNascimento());
			clienteJson.setEmail(cliente.getEmail());
			clienteJson.setNome(cliente.getNome());
			clienteJson.setUuid(cliente.getUuid());
			
			enderecoJson.setBairro(cliente.getBairro());
			enderecoJson.setCep(cliente.getCep());
			enderecoJson.setCidade(cliente.getCidade());
			enderecoJson.setEstado(cliente.getEstado());
			enderecoJson.setNumero(cliente.getNumero());
			enderecoJson.setPais(cliente.getPais());
			enderecoJson.setRua(cliente.getRua());
			clienteJson.setEndereco(enderecoJson);
			
			clientesJson.add(clienteJson);	
		});
		
		return clientesJson;
		
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	@ResponseBody
	public List<ClienteJson> findByName(@PathVariable String nome) {
		
		List<ClienteJson> clientesJson = new ArrayList<>();
		
		clienteRepository.findByName(nome).forEach(cliente -> {
			ClienteJson clienteJson = new ClienteJson();
			EnderecoJson enderecoJson = new EnderecoJson();
			
			clienteJson.setCpf(cliente.getCpf());
			clienteJson.setDataNascimento(cliente.getDataNascimento());
			clienteJson.setEmail(cliente.getEmail());
			clienteJson.setNome(cliente.getNome());
			clienteJson.setUuid(cliente.getUuid());
			
			enderecoJson.setBairro(cliente.getBairro());
			enderecoJson.setCep(cliente.getCep());
			enderecoJson.setCidade(cliente.getCidade());
			enderecoJson.setEstado(cliente.getEstado());
			enderecoJson.setNumero(cliente.getNumero());
			enderecoJson.setPais(cliente.getPais());
			enderecoJson.setRua(cliente.getRua());
			clienteJson.setEndereco(enderecoJson);
			
			clientesJson.add(clienteJson);	
		});
		
		return clientesJson;
	}
	
	@Transactional(readOnly = true)
	@RequestMapping(value = "/uuid/{uuid}", method = RequestMethod.GET)
	@ResponseBody
	public List<ClienteJson> findByUuid(@PathVariable String uuid) {
		
		List<ClienteJson> clientesJson = new ArrayList<>();
		
		clienteRepository.findByUuid(uuid).forEach(cliente -> {
			ClienteJson clienteJson = new ClienteJson();
			EnderecoJson enderecoJson = new EnderecoJson();
			
			clienteJson.setCpf(cliente.getCpf());
			clienteJson.setDataNascimento(cliente.getDataNascimento());
			clienteJson.setEmail(cliente.getEmail());
			clienteJson.setNome(cliente.getNome());
			clienteJson.setUuid(cliente.getUuid());
			
			enderecoJson.setBairro(cliente.getBairro());
			enderecoJson.setCep(cliente.getCep());
			enderecoJson.setCidade(cliente.getCidade());
			enderecoJson.setEstado(cliente.getEstado());
			enderecoJson.setNumero(cliente.getNumero());
			enderecoJson.setPais(cliente.getPais());
			enderecoJson.setRua(cliente.getRua());
			clienteJson.setEndereco(enderecoJson);
			
			clientesJson.add(clienteJson);	
		});
		
		return clientesJson;
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/cpf/{cpf}", method = RequestMethod.GET)
	@ResponseBody
	public List<ClienteJson> findByDocument(@PathVariable String cpf) {
		
		List<ClienteJson> clientesJson = new ArrayList<>();
		
		
		clienteRepository.findByDocument(cpf).forEach(cliente -> {
			ClienteJson clienteJson = new ClienteJson();
			EnderecoJson enderecoJson = new EnderecoJson();
			
			clienteJson.setCpf(cliente.getCpf());
			clienteJson.setDataNascimento(cliente.getDataNascimento());
			clienteJson.setEmail(cliente.getEmail());
			clienteJson.setNome(cliente.getNome());
			clienteJson.setUuid(cliente.getUuid());
			
			enderecoJson.setBairro(cliente.getBairro());
			enderecoJson.setCep(cliente.getCep());
			enderecoJson.setCidade(cliente.getCidade());
			enderecoJson.setEstado(cliente.getEstado());
			enderecoJson.setNumero(cliente.getNumero());
			enderecoJson.setPais(cliente.getPais());
			enderecoJson.setRua(cliente.getRua());
			clienteJson.setEndereco(enderecoJson);
			
			clientesJson.add(clienteJson);	
		});
		
		return clientesJson;
	}

}
