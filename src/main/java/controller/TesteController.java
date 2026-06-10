package controller;

import usecase.SalvarLancamentoUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.LucroLivreItem;
import service.LucroLivreService;


@RestController
@RequestMapping("/teste-db")
public class TesteController {
    private final SalvarLancamentoUseCase salvarLancamentoUseCase;
    @Autowired
    private LucroLivreService service;

    TesteController(SalvarLancamentoUseCase salvarLancamentoUseCase) {
        this.salvarLancamentoUseCase = salvarLancamentoUseCase;
    }

    @PostMapping
    public String testarInsercao() {
        LucroLivreItem item = new LucroLivreItem();
        item.setPk("USER#123");
        item.setSk("METADATA");
        service.salvarLancamentoUseCase(item);
        return "Gravado com sucesso no DynamoDB!";
    }
}

