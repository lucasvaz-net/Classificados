import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.JComboBox;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        ArrayList<Classificados> listaClassificados = new ArrayList<>();
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
                    LocalDate dataInicio = LocalDate.parse(JOptionPane.showInputDialog("Digite a Data de Inicio da Exibição (no formato YYYY-MM-DD)"));
                    LocalDate dataTermino = LocalDate.parse(JOptionPane.showInputDialog("Digite a Data de Inicio da Exibição (no formato YYYY-MM-DD)"));

                    String[] opcoesCategoria = {"VENDA", "ALUGUEL", "COMPRA", "SERVIÇO", "OUTRO", "EMPREGO"};
                    JComboBox<String> comboBoxCategoria = new JComboBox<>(opcoesCategoria);
                    comboBoxCategoria.setSelectedIndex(0); // seleciona a primeira opção por padrão
                    int resposta = JOptionPane.showConfirmDialog(null, comboBoxCategoria, "Selecione a categoria do classificado", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (resposta == JOptionPane.OK_OPTION) {
                        String categoria = comboBoxCategoria.getSelectedItem() == null ? "" : comboBoxCategoria.getSelectedItem().toString();

                        boolean status = true;


                        Classificados novoClassificado = new Classificados(titulo, descricao, preco, new Date(), contato, categoria, status, dataInicio, dataTermino);
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
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < listaClassificados.size(); i++) {
                        Classificados classificadoAtual = listaClassificados.get(i);
                        sb.append("Índice: ").append(i).append("\n");
                        sb.append("Título: ").append(classificadoAtual.getTitulo()).append("\n");
                        sb.append("Descrição: ").append(classificadoAtual.getDescricao()).append("\n");
                        sb.append("Preço: ").append(classificadoAtual.getPreco()).append("\n");
                        sb.append("Data de publicação: ").append(classificadoAtual.getDataPublicacao()).append("\n");
                        sb.append("Contato: ").append(classificadoAtual.getContato()).append("\n");
                        sb.append("Categoria: ").append(classificadoAtual.getCategoria()).append("\n");
                        sb.append("Status: ").append(classificadoAtual.isStatus() ? "Ativo" : "Inativo").append("\n");
                        sb.append("Data de início: ").append(classificadoAtual.getDataInicio()).append("\n");
                        sb.append("Data de término: ").append(classificadoAtual.getDataTermino()).append("\n\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;

                case 4:
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

                case 5:
                    JOptionPane.showMessageDialog(null, "Programa adherence!");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                    break;
            }
        } while (opcao != 5);
    }
}
