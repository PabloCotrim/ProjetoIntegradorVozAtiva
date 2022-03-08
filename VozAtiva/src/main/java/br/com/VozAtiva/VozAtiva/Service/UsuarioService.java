package br.com.VozAtiva.VozAtiva.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.VozAtiva.VozAtiva.Repository.UsuarioRepository;
import br.com.VozAtiva.VozAtiva.model.UsuarioLogin;
import br.com.VozAtiva.VozAtiva.model.UsuarioModel;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioModel CadastroUsuario(UsuarioModel usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);

		return usuarioRepository.save(usuario);
	}

	public Optional<UsuarioLogin> Logar(Optional<UsuarioLogin> user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<UsuarioModel> usuario = usuarioRepository.findByUsuario(user.get().getUsuario());

		if (usuario.isPresent()) {
			if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

				String auth = user.get().getUsuario() + ":" + user.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodeAuth);

				//user.get().setToken(authHeader);
				//user.get().setNomeCompleto(usuario.get(). getNomeCompleto());

				user.get().setId(usuario.get().getId());
				user.get().setNomeCompleto(usuario.get().getNomeCompleto());
				user.get().setToken(authHeader);
				user.get().setSenha(usuario.get().getSenha());
				
				
				return user;

			}
		}

		return Optional.empty();

	}

	
}