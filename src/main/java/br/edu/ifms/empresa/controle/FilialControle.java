package br.edu.ifms.empresa.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.empresa.excecao.EmpresaNotFoundException;
import br.edu.ifms.empresa.modelo.Empresa;
import br.edu.ifms.empresa.modelo.Filial;
import br.edu.ifms.empresa.servico.EmpresaServico;
import br.edu.ifms.empresa.servico.FilialServico;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/filial")
public class FilialControle {

	@Autowired
	private EmpresaServico empresaServico;

	@Autowired
	private FilialServico filialServico;

	@GetMapping("/buscar-filial/{id}")
	public String novaFilial(@PathVariable("id") long id, Model model) {
		String pagina = "";
		try {
			Empresa empresa = empresaServico.buscarEmpresaPorId(id);
			if (empresa.getFilial() == null) {
				Filial filial = new Filial();
				filial.setEmpresa(empresa);
				model.addAttribute("item", filial);
				pagina = "/nova-filial";
			} else {
				model.addAttribute("item", empresa.getFilial());
				pagina = "/alterar-filial";
			}

		} catch (EmpresaNotFoundException e) {
			System.out.println(e.getMessage());
			return "redirect:/";
		}
		return pagina;
	}

	@PostMapping("/gravar-filial/{idEmpresa}")
	public String gravarFilial(@PathVariable("idEmpresa") long idEmpresa,
			@ModelAttribute("item") @Valid Filial filial, BindingResult result, RedirectAttributes attributes) {
		try {
			Empresa empresa = empresaServico.buscarEmpresaPorId(idEmpresa);
			filial.setEmpresa(empresa);
		} catch (EmpresaNotFoundException e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {
			return "/nova-filial";
		}
		filialServico.criarFilial(filial);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/";
	}

	@PostMapping("/alterar-filial/{idEmpresa}/{idFilial}")
	public String alterarFilial(@PathVariable("idEmpresa") long idEmpresa,
			@PathVariable("idFilial") long idFilial, @ModelAttribute("item") @Valid Filial filial,
			BindingResult result, RedirectAttributes attributes) {
		try {
			Empresa empresa = empresaServico.buscarEmpresaPorId(idEmpresa);
			filial.setEmpresa(empresa);
			filial.setId(idFilial);			
		} catch (EmpresaNotFoundException e) {
			e.printStackTrace();
		}

		if (result.hasErrors()) {			
			return "/alterar-filial";
		}
		filialServico.alterarFilial(filial);
		attributes.addFlashAttribute("mensagem", "Gravado com sucesso");
		return "redirect:/";
	}


}
