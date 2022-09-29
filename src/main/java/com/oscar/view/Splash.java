package com.oscar.view;

import com.oscar.util.Config;
import com.oscar.util.LocalApiManagement;
import com.oscar.util.SingleTon;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Splash extends JWindow {

    private static JPanel painelBotãoFechar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private static JLabel lbSplash;
    private static final JProgressBar progressBar = new JProgressBar(0, 100);
    private static JButton btFechar = new JButton(" X ");

    public static void main(String[]args){

        Splash s = new Splash();
        s.setVisible(true);

    }

    public Splash(){

        criaTela();
        startUp();

    }

    private void criaTela(){
        //Setando Look And Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Splash.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setSize(710, 350);
        this.setLocationRelativeTo(null);
        this.setBackground(new Color(0, 255, 0, 0));

        this.getContentPane().setLayout(new BorderLayout());

        lbSplash = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imageSplash/splash.png"))));

        btFechar.setFont(new Font("Arial Black", Font.PLAIN, 12));
        btFechar.setOpaque(true);
        btFechar.setContentAreaFilled(false);
        btFechar.setBackground(new Color(224, 89, 17));
        btFechar.setBorder(new LineBorder(Color.WHITE, 2, true));
        btFechar.setForeground(Color.WHITE);
        btFechar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        progressBar.setStringPainted(true);

        btFechar.addActionListener((evt) -> {

            int resp = JOptionPane.showConfirmDialog(null, "Deseja fechar o app?", "Confirmar Ação", 0, 3);
            if (resp == 0) {
                System.exit(0);
            }

        });

        painelBotãoFechar.setOpaque(false);
        painelBotãoFechar.add(btFechar);
        this.getContentPane().add(painelBotãoFechar, BorderLayout.NORTH);
        this.getContentPane().add(lbSplash, BorderLayout.CENTER);
        this.getContentPane().add(progressBar, BorderLayout.SOUTH);

    }

    private void startUp(){

        Thread mainLoading = new Thread(() -> {

            try{
                progressBar.setValue(0);
                progressBar.setString("Iniciando configs...");

                if(!Config.setupConfig()){
                    int created = JOptionPane.showConfirmDialog(null, new TelaMiniConfig(false), "Configurar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if(created == JOptionPane.OK_OPTION){
                        if(!Config.setupConfig()){
                            JOptionPane.showMessageDialog(null, "O app não pode ser iniciado sem configuração.", "Fechando app", 0);
                            System.exit(0);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "O app não pode ser iniciado sem configuração.", "Fechando app", 0);
                        System.exit(0);
                    }

                }

                progressBar.setValue(40);
                progressBar.setString("Trancando instancia...");

                SingleTon.lock();

                progressBar.setValue(80);
                progressBar.setString("Verificando status da API...");

                if(Config.localApi){
                    progressBar.setIndeterminate(true);
                    progressBar.setString("Tentando iniciar API local...");
                    LocalApiManagement.startApi();
                }

                progressBar.setString("Tudo certo! Iniciando...");
                progressBar.setValue(100);

                TelaPrincipal t = new TelaPrincipal();
                t.setVisible(true);

                dispose();

            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Ocorreu um erro e o app precisa ser fechado:\n" + e.getMessage(), "ERRO", 0);
                System.exit(0);
            }

        });
        mainLoading.start();

    }

}
