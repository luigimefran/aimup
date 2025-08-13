package com.aimup.service;

import com.aimup.model.Mensagem;
import com.aimup.model.Usuario;
import com.aimup.repository.MensagemRepository;
import com.aimup.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private MensagemRepository mensagemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvarMensagem(String emailAutor, String conteudo) {
        Usuario usuario = usuarioRepository.findByEmail(emailAutor);
        Mensagem m = new Mensagem();
        m.setAutor(usuario.getNomeUsuario());
        m.setConteudo(conteudo);
        mensagemRepository.save(m);
    }

    public List<Mensagem> getMensagensRecentes() {
        return mensagemRepository.findTop30ByOrderByIdDesc();
    }

    public long getQtdUsuarios() {
        return usuarioRepository.count();
    }
}