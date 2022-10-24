package otaviofavoni.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import otaviofavoni.models.Aluno;
import otaviofavoni.repositories.AlunosRepository;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
    @Autowired
    private AlunosRepository alunosRepo;

    @RequestMapping("list")
    public String list(Model model) {
        model.addAttribute("alunos", this.alunosRepo.findAll());
        return "list";
    }

    @RequestMapping("insert")
    public String insert() {
        return "insert";
    }

    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public String insert(@RequestParam("nome") String nome, @RequestParam("idade") int idade) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setIdade(idade);
        alunosRepo.save(aluno);
        return "redirect:/alunos/list";
    }

    @RequestMapping("update/{id}")
    public String update(Model model, @PathVariable int id) {
        Optional<Aluno> aluno = alunosRepo.findById(id);
        model.addAttribute("aluno", aluno.get());
        return "/alunos/update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String saveUpdate(
        @RequestParam("nome") String nome,
        @RequestParam("idade") int idade,
        @RequestParam("id") int id) {
            Optional<Aluno> aluno = alunosRepo.findById(id);
            aluno.get().setNome(nome);
            aluno.get().setIdade(idade);
            alunosRepo.save(aluno.get());
            return "redirect:/alunos/list";
    }
    
    @RequestMapping("delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Optional<Aluno> aluno = alunosRepo.findById(id);
        model.addAttribute("aluno", aluno.get());
        return "/alunos/delete";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String saveDelete(@RequestParam("id") int id) {
        alunosRepo.deleteById(id);
        return "redirect:/alunos/list";
    }
}