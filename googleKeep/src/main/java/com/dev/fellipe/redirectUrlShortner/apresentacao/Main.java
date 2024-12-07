package com.dev.fellipe.redirectUrlShortner.apresentacao;

import com.dev.fellipe.redirectUrlShortner.negocio.Anotacao;
import com.dev.fellipe.redirectUrlShortner.persistencia.AnotacaoDAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);

        AnotacaoDAO anotacaoDAO = new AnotacaoDAO();

        int opcao;

        do {
            System.out.println("Google Keep Tabajara");
            System.out.println("===============");
            System.out.println("1: CRIAR NOVA ANOTAÇÃO");
            System.out.println("2: DELETAR UMA ANOTAÇÃO");
            System.out.println("3: ALTERAR UMA ANOTAÇÃO");
            System.out.println("4: COPIAR UMA ANOTAÇÃO EXISTENTE");
            System.out.println("5: LISTAR ANOTAÇÕES POR DATA E HORA");
            System.out.println("Para sair: DIGITE 0");
            System.out.println("===============");

            System.out.println("DIGITE A SUA OPÇÃO");
            opcao = in.nextInt();
            in.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Informe o título:");
                    String titulo = in.nextLine();
                    System.out.println("Informe a descrição:");
                    String descricao = in.nextLine();
                    System.out.println("Informe a cor:");
                    String cor = in.nextLine();

                    System.out.println("Informe o caminho da foto (deixe em branco se não tiver foto):");
                    String caminhoFoto = in.nextLine();
                    in.nextLine();

                    byte[] foto = null;
                    if (!caminhoFoto.trim().isEmpty()) {
                        File fotoFile = new File(caminhoFoto);
                        if (fotoFile.exists()) {
                            try {
                                foto = lerFoto(fotoFile);
                            } catch (IOException e) {
                                System.out.println("Erro ao ler a foto: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Caminho da foto não encontrado.");
                        }
                    }

                    Anotacao anotacao = new Anotacao(0, titulo, null, descricao, cor, foto);
                    anotacaoDAO.cadastrar(anotacao);
                    break;
                case 2:
                    System.out.println("informe o id que deseja deletar:");
                    int id = in.nextInt();
                    try {
                        anotacaoDAO.deletar(id);
                    } catch (SQLException e) {
                        System.out.println("Erro ao deletar anotação: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Informe o ID da anotação para atualizar:");
                    int idAtualizar = in.nextInt();
                    in.nextLine();

                    System.out.println("Informe o novo título:");
                    String novoTitulo = in.nextLine();
                    System.out.println("Informe a nova descrição:");
                    String novaDescricao = in.nextLine();
                    System.out.println("Informe a nova cor:");
                    String novaCor = in.nextLine();

                    System.out.println("Informe o caminho da nova foto (deixe em branco se não quiser alterar):");
                    String caminhoNovaFoto = in.nextLine();

                    byte[] novaFoto = null;
                    if (!caminhoNovaFoto.trim().isEmpty()) {
                        try {
                            novaFoto = lerFoto(new File(caminhoNovaFoto));
                        } catch (IOException e) {
                            System.out.println("Erro ao ler a foto: " + e.getMessage());
                        }
                    }

                    Anotacao anotacaoAtualizada = new Anotacao(idAtualizar, novoTitulo, null, novaDescricao, novaCor, novaFoto);
                    anotacaoDAO.atualizar(anotacaoAtualizada);
                    break;
                case 4:
                    System.out.println("Informe o ID da anotação para copiar:");
                    int idCopiar = in.nextInt();
                    in.nextLine();
                    anotacaoDAO.copiar(idCopiar);
                    break;
                case 5:
                    List<Anotacao> anotacoes = anotacaoDAO.listarPorDataHora();
                    for (Anotacao a : anotacoes) {
                        System.out.println(a);
                    }
                    break;
                default:
                    System.out.println("ENCERRANDO");
            }
        } while (opcao != 0);

    }

    private static byte[] lerFoto(File fotoFile) throws IOException {
        if (!fotoFile.exists()) {
            throw new IOException("O arquivo não existe: " + fotoFile.getAbsolutePath());
        }

        try (FileInputStream fis = new FileInputStream(fotoFile)) {
            byte[] fotoBytes = new byte[(int) fotoFile.length()];
            int bytesLidos = fis.read(fotoBytes);

            if (bytesLidos == -1) {
                throw new IOException("Erro ao ler o arquivo de imagem: " + fotoFile.getAbsolutePath());
            }

            return fotoBytes;
        }
    }
}