package br.edu.ifms.empresa.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import br.edu.ifms.empresa.excecao.EmpresaNotFoundException;
import br.edu.ifms.empresa.modelo.Empresa;
import br.edu.ifms.empresa.servico.EmpresaServico;
import jakarta.validation.Valid;

@Controller
public class EmpresaControle {
	@Autowired
	private EmpresaServico empresaServico;
	
	@GetMapping("/")
	public String listarEmpresa(Model model) {
		List<Empresa> empresa = empresaServico.buscarTodasEmpresas();
		model.addAttribute("listaEmpresa", empresa);
		return "/lista-empresas";
	}
	
	@GetMapping("/novo")
	public String novoEmpresa(Model model) {
		Empresa empresa = new Empresa();
		model.addAttribute("objetoEmpresa", empresa);
		return "/novo-empresa";
	}
	
	@PostMapping("/gravar")
	public String gravarEstudante(@ModelAttribute("objetoEmpresa")  @Valid Empresa empresa,
		BindingResult erros){		
		if(erros.hasErrors()) {
			return "/novo-empresa";
		}
		empresaServico.gravar(empresa);  
		return "redirect:/";
	}
	@GetMapping("/excluir/{id}")
	public String apagarEmpresa(@PathVariable("id") long id) {
		try {
			empresaServico.apagarEmpresa(id);
		} catch (EmpresaNotFoundException e) {
			
		}
		return "redirect:/";
	}
	
	@GetMapping("/editar/{id}")
	public String formEmpresa(@PathVariable("id") long id, Model model) {
		try {
			Empresa empresa = empresaServico.buscarEmpresaPorId(id);
			model.addAttribute("objetoEmpresa", empresa);
		} catch (EmpresaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/editar-empresa";
	}
	
	@PostMapping("/editar/{id}")
	public String editarEmpresa(@PathVariable("id") long id, @ModelAttribute("objetoEmpresa")  @Valid Empresa empresa,
			BindingResult erros) {
		if(erros.hasErrors()) {
			empresa.setId(id);
			return "/editar-empresa";
		}
		empresaServico.editar(empresa);
		return "redirect:/";
	}
	@PostMapping("/buscar")
	public String buscarEmpresaPorNome(@Param("nome") String nome, Model model) {
		if(nome == null) {
			return "redirect:/";
		}
		List<Empresa> empresa = empresaServico.buscaEmpresaPorNome(nome);		
		model.addAttribute("listaEmpresa", empresa);
		return "/lista-empresas";
	}
}
