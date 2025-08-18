package com.aimup.controller;

import com.aimup.model.Grupo;
import com.aimup.model.GrupoMembro;
import com.aimup.model.Mensagem;
import com.aimup.model.Usuario;
import com.aimup.repository.GrupoMembrosRepository;
import com.aimup.repository.GrupoRepository;
import com.aimup.service.ChatService;
import com.aimup.service.GrupoService;
import com.aimup.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;
    private final UsuarioService usuarioService;
    private final GrupoRepository grupoRepo;
    private final GrupoMembrosRepository membroRepo;
    private final GrupoService grupoService;

    public ChatController(ChatService chatService,
                          UsuarioService usuarioService,
                          GrupoRepository grupoRepo,
                          GrupoMembrosRepository membroRepo,
                          GrupoService grupoService) {
        this.chatService = chatService;
        this.usuarioService = usuarioService;
        this.grupoRepo = grupoRepo;
        this.membroRepo = membroRepo;
        this.grupoService = grupoService;
    }

    // Landing para escolher o grupo do chat
    @GetMapping("/chat")
    public String escolherGrupo(Model model, Principal principal) {
        Usuario u = usuarioService.buscarPorEmail(principal.getName());
        List<GrupoMembro> meus = membroRepo.findByUsuarioId(u.getId());
        List<Grupo> grupos = meus.stream().map(GrupoMembro::getGrupo).toList();

        model.addAttribute("usuario", u);
        model.addAttribute("grupos", grupos);
        return "chat/escolher";
    }

    // Chat de um grupo específico
    @GetMapping("/grupos/{grupoId}/chat")
    public String chatDoGrupo(@PathVariable Long grupoId, Principal principal, Model model) {
        Usuario u = usuarioService.buscarPorEmail(principal.getName());
        grupoService.garantirMembro(grupoId, u.getId());

        Grupo g = grupoRepo.findById(grupoId).orElseThrow();
        List<Mensagem> mensagens = chatService.ultimasMensagensAsc(grupoId);

        model.addAttribute("usuario", u);
        model.addAttribute("grupo", g);
        model.addAttribute("mensagens", mensagens);
        model.addAttribute("qtdMembros", chatService.qtdMembrosDoGrupo(grupoId));
        return "chat/chat";
    }

    // Enviar mensagem
    @PostMapping("/grupos/{grupoId}/chat/enviar")
    public String enviarMensagem(@PathVariable Long grupoId,
                                 @RequestParam String mensagem,
                                 Principal principal,
                                 RedirectAttributes ra) {
        try {
            chatService.salvarMensagem(grupoId, principal.getName(), mensagem);
        } catch (Exception e) {
            ra.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/grupos/" + grupoId + "/chat";
    }
}
