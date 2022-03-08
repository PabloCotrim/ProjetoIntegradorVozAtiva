package br.com.VozAtiva.VozAtiva.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.VozAtiva.VozAtiva.Repository.UsuarioRepository;
import br.com.VozAtiva.VozAtiva.Service.UsuarioService;
import br.com.VozAtiva.VozAtiva.model.UsuarioLogin;
import br.com.VozAtiva.VozAtiva.model.UsuarioModel;

@Controller
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> usuario) {
		return usuarioService.Logar(usuario).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());

	}

	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> Post(@RequestBody UsuarioModel usuario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.CadastroUsuario(usuario));
	}

	@GetMapping("/all")
	public ResponseEntity<List<UsuarioModel>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}

	//@PutMapping("/update")
	//public ResponseEntity<UsuarioModel> putUsuario(@Valid @RequestBody UsuarioModel usuario) {

		//return usuarioService.atualizarUsuario(usuario)
				//.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				//.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}


