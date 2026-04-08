package br.com.bancodedadosfuctura;

import br.com.bancodedadosfuctura.entity.Pessoa;
import br.com.bancodedadosfuctura.service.PessoaService;
import br.com.bancodedadosfuctura.util.JpaUtil;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        PessoaService service = new PessoaService();
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Criar pessoa");
            System.out.println("2 - Buscar por ID");
            System.out.println("3 - Listar todos");
            System.out.println("4 - Editar pessoa");
            System.out.println("5 - Excluir pessoa");
            System.out.println("6 - Povoar banco (use só uma vez)");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");

            if (!scanner.hasNextInt()) {
                // Entrada inválida (letra, símbolo, etc.)
                System.out.println("Opção inválida. Digite um número.");
                scanner.nextLine(); // descarta a linha ruim
                continue;
            }

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa o \n

            switch (opcao) {
            case 1:
                criarPessoa(scanner, service);
                break;
            case 2:
                buscarPorId(scanner, service);
                break;
            case 3:
                listarTodos(service);
                break;
            case 4:
                editarPessoa(scanner, service);
                break;
            case 5:
                excluirPessoa(scanner, service);
                break;
            case 6:
                povoar(service);
                break;
            case 0:
                System.out.println("Encerrando...");
                break;
            default:
                System.out.println("Opção inválida.");
        }
        }

        scanner.close();
        JpaUtil.close();
    }

    private static void criarPessoa(Scanner scanner, PessoaService service) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF (só números): ");
        String cpf = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        Pessoa nova = new Pessoa();
        nova.setNome(nome);
        nova.setCpf(cpf);
        nova.setEmail(email);

        service.criar(nova);
        System.out.println("✔ Criado com ID: " + nova.getId());
    }

    private static void buscarPorId(Scanner scanner, PessoaService service) {
        System.out.print("ID: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido.");
            scanner.nextLine();
            return;
        }
        
        int idBusca = scanner.nextInt();
        scanner.nextLine();

        Pessoa encontrada = service.buscarUm(idBusca);
        if (encontrada != null) {
            System.out.println("✔ Encontrado: " + encontrada);
        } else {
            System.out.println("✘ Nenhuma pessoa com ID " + idBusca);
        }
    }

    private static void listarTodos(PessoaService service) {
        List<Pessoa> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            System.out.println("Banco vazio.");
        } else {
            System.out.println("\n--- Pessoas cadastradas ---");
            lista.forEach(System.out::println);
            System.out.println("Total: " + lista.size());
        }
    }

    
    private static void editarPessoa(Scanner scanner, PessoaService service) {
        System.out.print("ID da pessoa a editar: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido.");
            scanner.nextLine();
            return;
        }
        int idEdit = scanner.nextInt();
        scanner.nextLine();

        Pessoa editar = service.buscarUm(idEdit);
        if (editar == null) {
            System.out.println("✘ ID não encontrado.");
            return;
        }

        System.out.println("Dados atuais: " + editar);

        System.out.print("Novo nome (enter para manter): ");
        String nome = scanner.nextLine();
        if (!nome.isBlank()) {
            editar.setNome(nome);
        }

        System.out.print("Novo CPF (enter para manter): ");
        String cpf = scanner.nextLine();
        if (!cpf.isBlank()) {
            editar.setCpf(cpf);
        }

        System.out.print("Novo email (enter para manter): ");
        String email = scanner.nextLine();
        if (!email.isBlank()) {
            editar.setEmail(email);
        }

        service.editar(editar);
        System.out.println("✔ Editado com sucesso.");
    }

    private static void excluirPessoa(Scanner scanner, PessoaService service) {
        System.out.print("ID da pessoa a excluir: ");
        if (!scanner.hasNextInt()) {
            System.out.println("ID inválido.");
            scanner.nextLine();
            return;
        }
        int idExcluir = scanner.nextInt();
        scanner.nextLine();

        Pessoa alvo = service.buscarUm(idExcluir);
        if (alvo == null) {
            System.out.println("✘ ID não encontrado. Exclusão abortada.");
            return;
        }

        System.out.println("Encontrado: " + alvo);
        System.out.print("Confirmar exclusão? (s/N): ");
        String confirmacao = scanner.nextLine().trim().toLowerCase();

        if (!confirmacao.equals("s")) {
            System.out.println("Operação cancelada.");
            return;
        }

        service.excluir(idExcluir);
        System.out.println("✔ Excluído: " + alvo.getNome());
    }

    // ─────────────────────────────────────
    // POVOAR O BANCO (Para testar o CRUD)
    // ─────────────────────────────────────
    private static void povoar(PessoaService service) {
        String[][] dados = {
            {"Carlos Silva",  "11111111111", "carlos@email.com"},
            {"Ana Paula",     "22222222222", "ana@email.com"},
            {"João Pedro",    "33333333333", "joao@email.com"},
            {"Mariana Costa", "44444444444", "mariana@email.com"},
            {"Ricardo Alves", "55555555555", "ricardo@email.com"},
        };

        System.out.println("\nPopulando banco...");
        for (String[] d : dados) {
            Pessoa p = new Pessoa();
            p.setNome(d[0]);
            p.setCpf(d[1]);
            p.setEmail(d[2]);
            service.criar(p);
            System.out.println("Inserido: " + p);
        }
        System.out.println("--- Banco populado ---");
    }
}