import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;







public class Main {
    public static void main(String[] args) {
        ArrayList<Classificados> listaClassificados = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("classificados.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String titulo = fields[0];
                String descricao = fields[1];
                double preco = Double.parseDouble(fields[2]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dataPublicacao = LocalDate.parse(fields[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                String contato = fields[4];
                String categoria = fields[5];
                boolean status = Boolean.parseBoolean(fields[6]);
                LocalDate dataInicio = LocalDate.parse(fields[7], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate dataTermino = LocalDate.parse(fields[8], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Classificados classificado = new Classificados(titulo, descricao, preco, dataPublicacao, contato, categoria, status, dataInicio, dataTermino);
                listaClassificados.add(classificado);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        int opcao;
        do {
            opcao = Integer.parseInt(JOptionPane.showInputDialog("""
                    Escolha uma opção:
                    1 - Inserir um classificado
                    2 - Remover um classificado
                    3 - Imprimir todos os classificados
                    4 - Salvar os classificados em um arquivo txt
                    5 - Sair
                    """));


            switch (opcao) {
                case 1:
                    String titulo = JOptionPane.showInputDialog("Digite o título do classificado:");

                    String descricao = JOptionPane.showInputDialog("Digite a descrição do classificado:");
                    double preco = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do classificado:"));
                    String contato = JOptionPane.showInputDialog("Digite as informações de contato do classificado:");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate dataInicio = LocalDate.parse(JOptionPane.showInputDialog("Digite a Data de Inicio da Exibição (no formato dd/MM/yyyy)"), formatter);
                    LocalDate dataTermino = LocalDate.parse(JOptionPane.showInputDialog("Digite a Data de Término da Exibição (no formato dd/MM/yyyy)"), formatter);
                    String[] opcoesCategoria = {"VENDA", "ALUGUEL", "COMPRA", "SERVIÇO", "OUTRO", "EMPREGO"};
                    JComboBox<String> comboBoxCategoria = new JComboBox<>(opcoesCategoria);
                    comboBoxCategoria.setSelectedIndex(0); // seleciona a primeira opção por padrão
                    int resposta = JOptionPane.showConfirmDialog(null, comboBoxCategoria, "Selecione a categoria do classificado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (resposta == JOptionPane.OK_OPTION) {
                        String categoria = comboBoxCategoria.getSelectedItem() == null ? "" : comboBoxCategoria.getSelectedItem().toString();

                        boolean status = true;

                        LocalDate dataPublicacao = LocalDate.now();

                        Classificados novoClassificado = new Classificados(titulo, descricao, preco, dataPublicacao, contato, categoria, status, dataInicio, dataTermino);
                        listaClassificados.add(novoClassificado);
                        JOptionPane.showMessageDialog(null, "Classificado inserido com sucesso!");
                        break;
                    }
                case 2:
                    int indiceRemover = Integer.parseInt(JOptionPane.showInputDialog("Digite o índice do classificado a ser removido:"));
                    if (indiceRemover < 0 || indiceRemover >= listaClassificados.size()) {
                        JOptionPane.showMessageDialog(null, "Índice inválido!");
                    } else {
                        listaClassificados.remove(indiceRemover);
                        JOptionPane.showMessageDialog(null, "Classificado removido com sucesso!");
                    }
                    break;

                case 3:
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false);
                    for (int i = 0; i < listaClassificados.size(); i++) {
                        Classificados classificadoAtual = listaClassificados.get(i);
                        textArea.append("Índice: " + i + "\n");
                        textArea.append("Título: " + classificadoAtual.getTitulo() + "\n");
                        textArea.append("Descrição: " + classificadoAtual.getDescricao() + "\n");
                        textArea.append("Preço: " + classificadoAtual.getPreco() + "\n");
                        textArea.append("Data de publicação: " + classificadoAtual.getDataPublicacao() + "\n");
                        textArea.append("Contato: " + classificadoAtual.getContato() + "\n");
                        textArea.append("Categoria: " + classificadoAtual.getCategoria() + "\n");
                        textArea.append("Status: " + (classificadoAtual.isStatus() ? "Ativo" : "Inativo") + "\n");
                        textArea.append("Data de início: " + classificadoAtual.getDataInicio() + "\n");
                        textArea.append("Data de término: " + classificadoAtual.getDataTermino() + "\n\n");
                    }
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(800, 600));
                    JOptionPane.showMessageDialog(null, scrollPane);
                    break;


                case 4:
                    int opcao2;
                    do {
                        opcao2 = Integer.parseInt(JOptionPane.showInputDialog("""
                    Como deseja Salvar seu arquivo?
                    1 - TXT
                    2 - CSV
                    3- VOLTAR
                    """));

                        switch (opcao2) {
                            case 1:
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("classificados.txt"))) {
                        for (Classificados classificado : listaClassificados) {
                            writer.write(classificado.toString());
                            writer.newLine();
                        }
                        JOptionPane.showMessageDialog(null, "Classificados salvos com sucesso!");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar os classificados!");
                    }
                    break;
                            case 2:
                            try (BufferedWriter writer = new BufferedWriter(new FileWriter("classificados.csv"))) {
                                for (Classificados classificado : listaClassificados) {
                                    writer.write(classificado.toString());
                                    writer.newLine();
                                }
                                JOptionPane.showMessageDialog(null, "Classificados salvos com sucesso!");
                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Erro ao salvar os classificados!");
                            }
                            break;
                            case 3:

                                break;

                        }break;
                    } while (opcao != 3);

                case 5:

                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 5);
    }
}
