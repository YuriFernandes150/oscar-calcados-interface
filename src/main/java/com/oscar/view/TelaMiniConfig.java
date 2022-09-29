package com.oscar.view;

import com.google.gson.stream.JsonWriter;
import com.oscar.util.Config;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelaMiniConfig extends JPanel {

    public TelaMiniConfig(boolean alterar){

        criaTela();
        defineActions(alterar);

    }

    JPanel painelMiniConfig = new JPanel();

    JPanel painelTxtEndpoint = new JPanel(new FlowLayout());
    JPanel painelApiFile = new JPanel(new FlowLayout());

    JPanel painelBtsFinalizar = new JPanel(new FlowLayout());
    JTextField txtEndPoint = new JTextField();
    JTextField txtApiFilePath = new JTextField();

    JButton btBrowseAPIFile = new JButton("...");

    JButton btSalvarConfigs = new JButton("SALVAR");

    JCheckBox checkApiLocal = new JCheckBox("API Local");

    private void criaTela(){

        this.setSize(new Dimension(500, 200));

        txtEndPoint.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "ENDPOINT PADRÃO DA API"));
        txtEndPoint.setPreferredSize(new Dimension(400, 40));

        txtApiFilePath.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "ARQUIVO JAR DA API"));
        txtApiFilePath.setPreferredSize(new Dimension(350, 40));

        painelMiniConfig.setLayout(new BoxLayout(painelMiniConfig, BoxLayout.Y_AXIS));

        painelTxtEndpoint.add(txtEndPoint);
        painelTxtEndpoint.add(checkApiLocal);
        checkApiLocal.setSelected(true);
        checkApiLocal.setToolTipText(
                "<html>"+
                "Define se a API vai ser inicializada localmente. <br>" +
                "Selecione se quiser que o app inicialize o jar da API automaticamente <br>" +
                "ao iniciar a interface e finalize a API o finalizar a interface.<br><br>" +
                "OBS: Ainda é possível usar a API localmente se você digitar a URL no campo ao lado <br>" +
                "utilizando o endereço localhost, o app só não irá inicializar a API automaticamente." +
                "</html>"
        );
        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);

        painelApiFile.add(txtApiFilePath);
        painelApiFile.add(btBrowseAPIFile);

        painelBtsFinalizar.add(btSalvarConfigs);

        painelMiniConfig.add(painelTxtEndpoint);
        painelMiniConfig.add(painelApiFile);
        painelMiniConfig.add(painelBtsFinalizar);

        this.add(painelMiniConfig);

    }

    private void defineActions(boolean alterar){

        if(!alterar){
            try{
                JEditorPane painelMensagem = new JEditorPane("text/html","<html>Esse app requer uma API especial para funcionar.<br>" +
                        "Certifique-se de que você configurou a API corretamente antes de continuar.<br>" +
                        "A API pode ser encontrada <a href=\"https://github.com/YuriFernandes150/oscar-calcados-test-api\">aqui</a></html>");
                painelMensagem.addHyperlinkListener(e -> {
                    if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (IOException | URISyntaxException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                painelMensagem.setEditable(false);
                painelMensagem.setBackground(new JLabel().getBackground());

                JOptionPane.showMessageDialog(null,
                        painelMensagem,
                        "API Necessária",
                        JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesConfig/github-sign.png"))));

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            txtApiFilePath.setText(Config.apiJarLocation.getPath());
            txtEndPoint.setText(Config.baseEndpointUrl);
            checkApiLocal.setSelected(Config.localApi);
        }

        checkApiLocal.addItemListener((evt)->{

            if(checkApiLocal.isSelected()){
                txtApiFilePath.setEnabled(true);
                btBrowseAPIFile.setEnabled(true);
            }else{
                txtApiFilePath.setText("");
                txtApiFilePath.setEnabled(false);
                btBrowseAPIFile.setEnabled(false);
            }

        });

        btBrowseAPIFile.addActionListener((evt) ->{

            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileFilter() {
                @Override
                public String getDescription() {
                    return "Arquivos JAR";
                }

                @Override
                public boolean accept(File f) {
                    if (f.isDirectory()) {
                        return true;
                    } else {
                        return f.getName().toLowerCase().endsWith(".jar");
                    }
                }
            });
            chooser.setCurrentDirectory(Config.rootDir);
            chooser.showOpenDialog(null);

            File selectedFile = chooser.getSelectedFile();

            if(selectedFile != null){
                txtApiFilePath.setText(selectedFile.getPath());
            }

        });
        btSalvarConfigs.addActionListener((evt)->{

            try{
                if(isValid(txtEndPoint.getText(), "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")){

                    if(!new File(txtApiFilePath.getText()).exists() && checkApiLocal.isSelected()){
                        JOptionPane.showMessageDialog(null, "Caminho para a API inválido!\n" +
                                "Especifique um caminho válido ou\n" +
                                "desmarque a caixa de seleção (API Local).",
                                "ERRO!",
                                0);
                        return;
                    }

                    File configFile = new File(Config.rootDir.getPath() + File.separator + "config.json");
                    if(configFile.exists()){
                        configFile.delete();
                    }
                    JsonWriter gw = new JsonWriter(new PrintWriter(configFile));
                    gw.beginObject();
                    gw.name("base-endpoint-url").value(txtEndPoint.getText());
                    gw.name("local-api").value(checkApiLocal.isSelected());
                    gw.name("api-jar-location").value(txtApiFilePath.getText());
                    gw.endObject();
                    gw.flush();
                    JOptionPane.showMessageDialog(null, "Config salva!");

                    if(!alterar){
                        Config.baseEndpointUrl = txtEndPoint.getText();
                        Config.apiJarLocation = new File(txtApiFilePath.getText());
                        Config.localApi = checkApiLocal.isSelected();
                        if(checkApiLocal.isSelected()){
                            JOptionPane.showMessageDialog(null, "Alteração bem-sucedida! Reinicie para aplicar!", "ERRO",0);
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "URL fornecida inválida!", "ERRO",0);
                }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO!", 0);
            }

        });

    }

    private boolean isValid(String s, String regex) throws Exception{
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);

        return matcher.matches();
    }


}