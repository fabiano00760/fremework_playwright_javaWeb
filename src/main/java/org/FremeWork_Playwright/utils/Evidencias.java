package org.FremeWork_Playwright.utils;

import com.microsoft.playwright.Page;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Evidencias {

    private String pastaEvidencias;

    /**
     * Construtor da classe que cria a pasta de evidências com base na data.
     */
    public Evidencias(String nomeTeste) {
        criarPastaEvidencias(nomeTeste);
    }

    /**
     * Método para criar a pasta de evidências diária.
     * @param nomeTeste Nome do teste para identificar a subpasta dentro da pasta do dia.
     */
    private void criarPastaEvidencias(String nomeTeste) {
        String dataAtual = new SimpleDateFormat("yyyyMMdd").format(new Date()); // Pasta baseada na data
        String subPastaTeste = nomeTeste + "_" + new SimpleDateFormat("HHmmss").format(new Date()); // Subpasta baseada no nome do teste e horário
        this.pastaEvidencias = "evidencias/" + dataAtual + "/" + subPastaTeste;

        try {
            Files.createDirectories(Paths.get(this.pastaEvidencias));
            System.out.println("Pasta de evidências criada: " + this.pastaEvidencias);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar a pasta de evidências: " + e.getMessage(), e);
        }
    }

    /**
     * Método para capturar uma evidência (screenshot) de uma etapa do teste.
     * @param page Instância da página do Playwright.
     * @param nomeEtapa Nome ou descrição da etapa do teste.
     */
    public void capturarEvidencia(Page page, String nomeEtapa) {
        if (page == null) {
            throw new RuntimeException("A instância da página (page) está nula. Certifique-se de que a página foi inicializada.");
        }

        String nomeArquivo = nomeEtapa.replaceAll("[^a-zA-Z0-9_-]", "_") + ".png";
        Path caminhoArquivo = Paths.get(this.pastaEvidencias, nomeArquivo);

        try {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(caminhoArquivo)
                    .setFullPage(true));
            System.out.println("Screenshot capturado: " + caminhoArquivo);
        } catch (Exception e) {
            System.err.println("Erro ao capturar o screenshot para " + nomeEtapa + ": " + e.getMessage());
        }
    }
}
