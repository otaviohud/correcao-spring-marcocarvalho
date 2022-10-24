package otaviofavoni.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import otaviofavoni.models.Professores;
import otaviofavoni.repositories.ProfessoresRepository;

@Controller
@RequestMapping("/Professores")
public class ProfessoresController {
    @Autowired
    private ProfessosresRepository professoresRepo;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("professores", this.professoresRepo.findAll());
        return "list";
    }

    @RequestMapping("insert")
    public String insert() {
        return "insert";
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome, @RequestParam("componente") int componente) {
        Professores professores = new Professores();
        professores.setNome(nome);
        professores.setComponente(componente);
        professores.save(professores);
        return "redirect:/professores/list";
    }

    @RequestMapping("update/{id}")
    public String update(Model model, @PathVariable int id) {
        Optional<Professores> professores = professoresRepo.findById(id);
        model.addAttribute("professores", professores.get());
        return "/professores/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String saveUpdate(
        @RequestParam("nome") String nome,
        @RequestParam("componente") int componente,
        @RequestParam("id") int id) {
            Optional<Professores> professores = professoresRepo.findById(id);
            professores.get().setNome(nome);
            professores.get().setComponentes(componente);
            professoresRepo.save(professores.get());
            return "redirect:/professores/list";
    }
    
    @RequestMapping("delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Optional<Professores> professores = professoresRepo.findById(id);
        model.addAttribute("professores", professores.get());
        return "/professores/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String saveDelete(@RequestParam("id") int id) {
        professoresRepo.deleteById(id);
        return "redirect:/professores/list";
    }
}