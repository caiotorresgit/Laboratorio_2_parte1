package interfaces;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interface extends JFrame {
    private JButton cadastrarButton;
    private JButton listarButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private List<Aluno> alunos;
    private JButton atualizarButton;
    private JButton deletarButton;

    private List<Aluno> obterAlunos() {
        return alunos;
    }

    public Interface() {
        super("Minha Interface");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 100);
        setLocationRelativeTo(null);

        cadastrarButton = new JButton("Cadastrar");
        listarButton = new JButton("Listar");
        atualizarButton = new JButton("Atualizar");
        deletarButton = new JButton("Deletar");

        String[] colunas = {"Nome", "CPF", "Matrícula", "Vertente"};
        tableModel = new DefaultTableModel(colunas, 0);
        table = new JTable(tableModel);

        alunos = new ArrayList<>();

        setLayout(new BorderLayout());

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(cadastrarButton);
        buttonsPanel.add(listarButton);
        buttonsPanel.add(atualizarButton);
        buttonsPanel.add(deletarButton);
        add(buttonsPanel, BorderLayout.NORTH);

        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField nomeField = new JTextField(10);
                JTextField cpfField = new JTextField(10);
                JTextField matriculaField = new JTextField(10);
                JTextField vertenteField = new JTextField(10);

                JPanel panel = new JPanel(new GridLayout(4, 2));
                panel.add(new JLabel("Nome:"));
                panel.add(nomeField);
                panel.add(new JLabel("CPF:"));
                panel.add(cpfField);
                panel.add(new JLabel("Matrícula:"));
                panel.add(matriculaField);
                panel.add(new JLabel("Vertente:"));
                panel.add(vertenteField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Cadastro do Aluno",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String nome = nomeField.getText();
                    String cpf = cpfField.getText();
                    String matricula = matriculaField.getText();
                    String vertente = vertenteField.getText();
                    
                if (nome.isEmpty() || cpf.isEmpty() || matricula.isEmpty() || vertente.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                    Aluno aluno = new Aluno(nome, cpf, matricula, vertente);
                    alunos.add(aluno);
                    
                    String mensagem = "Aluno cadastrado com sucesso!";
                    JOptionPane.showMessageDialog(null, mensagem);
                }
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (alunos.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Nenhum aluno cadastrado.");
                    return;
                }

                String[] colunas = {"Nome", "CPF", "Matrícula", "Vertente"};
                tableModel = new DefaultTableModel(colunas, 0);
                table.setModel(tableModel);

                for (Aluno aluno : alunos) {
                    String[] alunoData = {
                            aluno.getNome(),
                            aluno.getCPF(),
                            aluno.getMatricula(),
                            aluno.getVertente()
                    };
                    tableModel.addRow(alunoData);
                }

                JOptionPane.showMessageDialog(null, new JScrollPane(table), "Alunos Cadastrados", JOptionPane.PLAIN_MESSAGE);
            }
        });

        atualizarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField matriculaField = new JTextField(10);

                JPanel panel = new JPanel(new GridLayout(1, 2));
                panel.add(new JLabel("Matrícula:"));
                panel.add(matriculaField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Atualizar Vertente",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String matricula = matriculaField.getText();

                    Aluno alunoEncontrado = null;

                    int index = -1;
                    for (int i = 0; i < alunos.size(); i++) {
                        Aluno aluno = alunos.get(i);
                        if (aluno.getMatricula().equals(matricula)) {
                            alunoEncontrado = aluno;
                            index = i;
                        }
                    }

                    if (alunoEncontrado == null) {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                        return;
                    }

                    JTextField vertenteField = new JTextField(10);

                    JPanel panel2 = new JPanel(new GridLayout(1, 2));
                    panel2.add(new JLabel("Nova Vertente:"));
                    panel2.add(vertenteField);

                    int result2 = JOptionPane.showConfirmDialog(null, panel2, "Atualizar Vertente",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result2 == JOptionPane.OK_OPTION) {
                        String novaVertente = vertenteField.getText();
                        alunoEncontrado.setVertente(novaVertente);
                        alunos.set(index, alunoEncontrado);
                        JOptionPane.showMessageDialog(null, "Vertente atualizada com sucesso.");
                    }
                }
            }
        });

        deletarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextField matriculaField = new JTextField(10);

                JPanel panel = new JPanel(new GridLayout(1, 2));
                panel.add(new JLabel("Matrícula:"));
                panel.add(matriculaField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Excluir Aluno",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String matricula = matriculaField.getText();

                    boolean alunoEncontrado = false;
                    int index = -1;

                    int i = 0;
                    while (i < alunos.size() && !alunoEncontrado) {
                        Aluno aluno = alunos.get(i);
                        if (aluno.getMatricula().equals(matricula)) {
                            alunoEncontrado = true;
                            index = i;
                        }
                        i++;
                    }

                    if (alunoEncontrado) {
                        alunos.remove(index);
                        JOptionPane.showMessageDialog(null, "Aluno removido com sucesso.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Interface gui = new Interface();
                gui.setVisible(true);
            }
        });
    }
}
