package com.oscar.view;

import com.oscar.DAO.*;
import com.oscar.DTO.CustomSearchParamDTO;
import com.oscar.model.*;
import com.oscar.util.ABMTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class TelaAddCalcado extends JDialog {

    public TelaAddCalcado(boolean alterar){
        criaTela(alterar);
        defineActions(alterar);
    }

    JPanel painelPrincipalCalcado = new JPanel();
    JPanel painelInferiorBt = new JPanel();

    JPanel painelCalcadoDireita = new JPanel();
    JPanel painelCalcadoEsquerda = new JPanel();

    JPanel painelCodCalcado = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelDescricao = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelPreco = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelQtdEstoque = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelMarca = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelCor = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelTamanho = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JTextField txtCodCalcado = new JTextField();
    JTextField txtDescricaoCalcado = new JTextField();
    JTextField txtPrecoCalcado = new ABMTextField(new DecimalFormat("0.00"));
    JSpinner txtQtdEstoque = new JSpinner(new SpinnerNumberModel(1,1,Integer.MAX_VALUE,1));

    public JComboBox<MarcaCalcado> comboMarcaCalcado = new JComboBox<>();
    public JComboBox<TamanhoCalcado> comboTamanhoCalcado = new JComboBox<>();
    public JComboBox<CategoriaCalcado> comboCategoriaCalcado = new JComboBox<>();
    public JComboBox<CorCalcado> comboCorCalcado = new JComboBox<>();

    JButton btAddMarca = new JButton("+");
    JButton btAddCategoria = new JButton("+");
    JButton btAddCor = new JButton("+");
    JButton btAddTamanho = new JButton("+");

    JButton btEditarTamanho = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/pencil.png"))));
    JButton btEditarMarca = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/pencil.png"))));
    JButton btEditarCategoria = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/pencil.png"))));
    JButton btEditarCor = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/pencil.png"))));

    JButton btDeletarCor = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/trash-can.png"))));
    JButton btDeletarTamanho = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/trash-can.png"))));
    JButton btDeletarMarca = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/trash-can.png"))));
    JButton btDeletarCategoria = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/trash-can.png"))));


    JButton btSalvar = new JButton("Salvar");

    JButton btCancelar = new JButton("Cancelar");

    int hiddenId = 0;

    private void criaTela(boolean alterar){

        this.setSize(new Dimension(650,400));
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        if(alterar){
            this.setTitle("Alterar Calçado");
        }else{
            this.setTitle("Cadastrar Calçado");
        }

        painelPrincipalCalcado.setLayout(new BoxLayout(painelPrincipalCalcado,BoxLayout.X_AXIS));

        painelCalcadoDireita.setLayout(new BoxLayout(painelCalcadoDireita, BoxLayout.Y_AXIS));
        painelCalcadoEsquerda.setLayout(new BoxLayout(painelCalcadoEsquerda, BoxLayout.Y_AXIS));

        txtDescricaoCalcado.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "DESCRIÇÃO"));
        txtDescricaoCalcado.setPreferredSize(new Dimension(300,40));
        painelDescricao.add(txtDescricaoCalcado);

        txtPrecoCalcado.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "PREÇO"));
        txtPrecoCalcado.setPreferredSize(new Dimension(150,40));
        painelPreco.add(txtPrecoCalcado);

        txtCodCalcado.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "CODIGO"));
        txtCodCalcado.setPreferredSize(new Dimension(100,40));
        painelCodCalcado.add(txtCodCalcado);

        txtQtdEstoque.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "QTD. ESTOQUE"));
        txtQtdEstoque.setPreferredSize(new Dimension(150,40));
        painelQtdEstoque.add(txtQtdEstoque);

        comboCategoriaCalcado.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "CATEGORIA"));
        comboCategoriaCalcado.setPreferredSize(new Dimension(150,40));
        painelCategoria.add(comboCategoriaCalcado);
        painelCategoria.add(btAddCategoria);
        painelCategoria.add(btEditarCategoria);
        painelCategoria.add(btDeletarCategoria);

        comboMarcaCalcado.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "MARCA"));
        comboMarcaCalcado.setPreferredSize(new Dimension(150,40));
        painelMarca.add(comboMarcaCalcado);
        painelMarca.add(btAddMarca);
        painelMarca.add(btEditarMarca);
        painelMarca.add(btDeletarMarca);

        comboTamanhoCalcado.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "TAMANHO"));
        comboTamanhoCalcado.setPreferredSize(new Dimension(150,40));
        painelTamanho.add(comboTamanhoCalcado);
        painelTamanho.add(btAddTamanho);
        painelTamanho.add(btEditarTamanho);
        painelTamanho.add(btDeletarTamanho);

        comboCorCalcado.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "COR"));
        comboCorCalcado.setPreferredSize(new Dimension(150,40));
        painelCor.add(comboCorCalcado);
        painelCor.add(btAddCor);
        painelCor.add(btEditarCor);
        painelCor.add(btDeletarCor);

        painelCalcadoDireita.add(painelCodCalcado);
        painelCalcadoDireita.add(painelDescricao);
        painelCalcadoDireita.add(painelQtdEstoque);
        painelCalcadoDireita.add(painelPreco);

        painelCalcadoEsquerda.add(painelMarca);
        painelCalcadoEsquerda.add(painelTamanho);
        painelCalcadoEsquerda.add(painelCategoria);
        painelCalcadoEsquerda.add(painelCor);

        painelPrincipalCalcado.add(painelCalcadoDireita);
        painelPrincipalCalcado.add(painelCalcadoEsquerda);


        painelInferiorBt.add(btSalvar);
        painelInferiorBt.add(btCancelar);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(painelPrincipalCalcado, BorderLayout.CENTER);
        this.getContentPane().add(painelInferiorBt, BorderLayout.SOUTH);
        preencherTodosOsCombos();

    }

    private void defineActions(boolean alterar){

        btAddCategoria.addActionListener((evt)->{
            try {
                String descricaoCategoria = JOptionPane.showInputDialog(null,
                        "Digite o nome da nova categoria:",
                        "Adicionar Categoria",
                        JOptionPane.PLAIN_MESSAGE);
                CategoriaCalcado categoriaCalcado = new CategoriaCalcado(0, descricaoCategoria.toUpperCase(), LocalDateTime.now());
                new CategoriaDAO().salvarCategoria(categoriaCalcado);
                preencheComboCategoria();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao buscar dados!\n", "Erro",0);
                e.printStackTrace();
            }
        });

        btAddCor.addActionListener((evt)->{
            try {
                String descricaoCor = JOptionPane.showInputDialog(null,
                        "Digite o nome da nova cor:",
                        "Adicionar Cor",
                        JOptionPane.PLAIN_MESSAGE);
                CorCalcado corCalcado = new CorCalcado(0, descricaoCor.toUpperCase(), LocalDateTime.now());
                new CorDAO().salvarCor(corCalcado);
                preencheComboCor();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao buscar dados!\n", "Erro",0);
                e.printStackTrace();
            }
        });

        btAddMarca.addActionListener((evt)->{
            try {
                String descricaoMarca = JOptionPane.showInputDialog(null,
                        "Digite o nome da nova marca:",
                        "Adicionar Marca",
                        JOptionPane.PLAIN_MESSAGE);
                MarcaCalcado marcaCalcado = new MarcaCalcado(0, descricaoMarca.toUpperCase(), LocalDateTime.now());
                new MarcaDAO().salvarMarca(marcaCalcado);
                preencheComboMarca();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao buscar dados!\n", "Erro",0);
                e.printStackTrace();
            }
        });

        btAddTamanho.addActionListener((evt)->{
            try {
                String descricaoTamanho = JOptionPane.showInputDialog(null,
                        "Digite o nome da nova tamanho:",
                        "Adicionar Tamanho",
                        JOptionPane.PLAIN_MESSAGE);
                TamanhoCalcado tamanhoCalcado = new TamanhoCalcado(0, descricaoTamanho.toUpperCase(), LocalDateTime.now());
                new TamanhoDAO().SalvarTamanho(tamanhoCalcado);
                preencheComboTamanho();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "Erro ao buscar dados!\n", "Erro",0);
                e.printStackTrace();
            }
        });

        btEditarMarca.addActionListener((evt)->{
            try{
                MarcaCalcado marcaCalcado = (MarcaCalcado) comboMarcaCalcado.getSelectedItem();
                if(Objects.requireNonNull(marcaCalcado).getIdMarcaCalcado() != 0){
                    String novaDescricao = JOptionPane.showInputDialog(null,
                            "Digite a nova descrição para a marca",
                            marcaCalcado.getDescricaoMarcaCalcado());
                    if(novaDescricao != null){
                        marcaCalcado.setDescricaoMarcaCalcado(novaDescricao.toUpperCase());
                        new MarcaDAO().atualizarMarca(marcaCalcado);
                        preencheComboMarca();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btEditarCategoria.addActionListener((evt)->{
                try{
                    CategoriaCalcado categoriaCalcado = (CategoriaCalcado) comboCategoriaCalcado.getSelectedItem();
                    if(Objects.requireNonNull(categoriaCalcado).getIdCategoriaCalcado() != 0){
                        String novaDescricao = JOptionPane.showInputDialog(null,
                                "Digite a nova descrição para a categoria",
                                categoriaCalcado.getDescricaoCategoria());
                        if(novaDescricao != null){
                            categoriaCalcado.setDescricaoCategoria(novaDescricao.toUpperCase());
                            new CategoriaDAO().atualizarCategoria(categoriaCalcado);
                            preencheComboCategoria();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
        });

        btEditarCor.addActionListener((evt)->{
            try{
                CorCalcado corCalcado = (CorCalcado) comboCorCalcado.getSelectedItem();
                if(Objects.requireNonNull(corCalcado).getIdCorCalcado() != 0){
                    String novaDescricao = JOptionPane.showInputDialog(null,
                            "Digite a nova descrição para a cor",
                            corCalcado.getDescricaoCorCalcado());
                    if(novaDescricao != null){
                        corCalcado.setDescricaoCorCalcado(novaDescricao.toUpperCase());
                        new CorDAO().atualizarCor(corCalcado);
                        preencheComboCor();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btEditarTamanho.addActionListener((evt)->{
            try{
                TamanhoCalcado tamanhoCalcado = (TamanhoCalcado) comboTamanhoCalcado.getSelectedItem();
                if(Objects.requireNonNull(tamanhoCalcado).getIdTamanhoCalcado() != 0){
                    String novaDescricao = JOptionPane.showInputDialog(null,
                            "Digite a nova descrição para o tamanho",
                            tamanhoCalcado.getDescricaoTamanhoCalcado());
                    if(novaDescricao != null){
                        tamanhoCalcado.setDescricaoTamanhoCalcado(novaDescricao.toUpperCase());
                        new TamanhoDAO().atualizarTamanhoCalcado(tamanhoCalcado);
                        preencheComboTamanho();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btSalvar.addActionListener((evt)->{

            try{
                MarcaCalcado marcaCalcado = (MarcaCalcado) comboMarcaCalcado.getSelectedItem();
                TamanhoCalcado tamanhoCalcado = (TamanhoCalcado) comboTamanhoCalcado.getSelectedItem();
                CategoriaCalcado categoriaCalcado = (CategoriaCalcado) comboCategoriaCalcado.getSelectedItem();
                CorCalcado corCalcado = (CorCalcado) comboCorCalcado.getSelectedItem();

                if(corCalcado != null){
                    if(corCalcado.getIdCorCalcado() == 0){
                        JOptionPane.showMessageDialog(null,
                                "Verifique o campo de cor e tente novamente.",
                                "Cor não especificada",
                                3);
                        return;
                    }
                }

                if(marcaCalcado != null){
                    if(marcaCalcado.getIdMarcaCalcado() == 0){
                        JOptionPane.showMessageDialog(null,
                                "Verifique o campo de marca e tente novamente.",
                                "Marca não especificada",
                                3);
                        return;
                    }
                }


                if(tamanhoCalcado != null){
                    if(tamanhoCalcado.getIdTamanhoCalcado() == 0){
                        JOptionPane.showMessageDialog(null,
                                "Verifique o campo de tamanho e tente novamente.",
                                "Tamanho não especificado",
                                3);
                        return;
                    }
                }
                if(categoriaCalcado != null){
                    if(categoriaCalcado.getIdCategoriaCalcado() == 0){
                        JOptionPane.showMessageDialog(null,
                                "Verifique o campo de categoria e tente novamente.",
                                "Categoria não especificada",
                                3);
                        return;
                    }
                }

                Calcado calcado = new Calcado();
                calcado.setCodCalcado(txtCodCalcado.getText().trim());
                calcado.setDescricaoCalcado(txtDescricaoCalcado.getText().trim());
                calcado.setPrecoCalcado(Double.parseDouble(txtPrecoCalcado.getText().replace(",",".").trim()));
                calcado.setQtdEstoque(Double.parseDouble(txtQtdEstoque.getValue().toString().trim()));
                calcado.setCategoriaCalcado(categoriaCalcado);
                calcado.setMarcaCalcado(marcaCalcado);
                calcado.setCorCalcado(corCalcado);
                calcado.setTamanhoCalcado(tamanhoCalcado);

                if(alterar){
                    calcado.setIdCalcado(hiddenId);
                    new CalcadoDAO().atualizarCalcado(calcado);
                    dispose();
                }else{
                    new CalcadoDAO().salvarCalcado(calcado);
                    dispose();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        comboCorCalcado.addActionListener((evt)->{
            try{
                if(((JComboBox) evt.getSource()).getItemCount() <= 0) return;
                CorCalcado corCalcado = (CorCalcado) comboCorCalcado.getModel().getSelectedItem();
                if(corCalcado.getIdCorCalcado() != 0){
                    CustomSearchParamDTO param = new CustomSearchParamDTO();
                    param.setCorCalcado(corCalcado.getDescricaoCorCalcado());
                    List<Calcado> calcadosUsados = new CalcadoDAO().buscarCalcadosPorFiltro(0,10,param);
                    if(!calcadosUsados.isEmpty()){
                        btDeletarCor.setEnabled(false);
                        btDeletarCor.setToolTipText("Esta cor esta sendo utilizada por um ou mais calçados.");
                        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
                    }else{
                        btDeletarCor.setEnabled(true);
                        btDeletarCor.setToolTipText("");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        });

        comboCategoriaCalcado.addActionListener((evt)->{
            if(((JComboBox) evt.getSource()).getItemCount() <= 0) return;
            try {

                CategoriaCalcado categoriaCalcado = (CategoriaCalcado) comboCategoriaCalcado.getModel().getSelectedItem();
                if(categoriaCalcado.getIdCategoriaCalcado() != 0){
                    CustomSearchParamDTO param = new CustomSearchParamDTO();
                    param.setCategoriaCalcado(categoriaCalcado.getDescricaoCategoria());
                    List<Calcado> calcadosUsados = new CalcadoDAO().buscarCalcadosPorFiltro(0,10,param);
                    if(!calcadosUsados.isEmpty()){
                        btDeletarCategoria.setEnabled(false);
                        btDeletarCategoria.setToolTipText("Esta categoria esta sendo utilizada por um ou mais calçados.");
                        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
                    }else{
                        btDeletarCategoria.setEnabled(true);
                        btDeletarCategoria.setToolTipText("");
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        });

        comboMarcaCalcado.addActionListener((evt)->{
            if(((JComboBox) evt.getSource()).getItemCount() <= 0) return;
            try {
                MarcaCalcado marcaCalcado = (MarcaCalcado) comboMarcaCalcado.getModel().getSelectedItem();
                if (marcaCalcado.getIdMarcaCalcado() != 0) {
                    CustomSearchParamDTO param = new CustomSearchParamDTO();
                    param.setMarcaCalcado(marcaCalcado.getDescricaoMarcaCalcado());
                    List<Calcado> calcadosUsados = new CalcadoDAO().buscarCalcadosPorFiltro(0,10,param);
                    if(!calcadosUsados.isEmpty()){
                        btDeletarMarca.setEnabled(false);
                        btDeletarMarca.setToolTipText("Esta marca esta sendo utilizada por um ou mais calçados.");
                        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
                    }else{
                        btDeletarMarca.setEnabled(true);
                        btDeletarMarca.setToolTipText("");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        comboTamanhoCalcado.addActionListener((evt)->{
            if(((JComboBox) evt.getSource()).getItemCount() <= 0) return;
            try{
                TamanhoCalcado tamanhoCalcado = (TamanhoCalcado) comboTamanhoCalcado.getModel().getSelectedItem();
                if(tamanhoCalcado.getIdTamanhoCalcado() != 0){

                    CustomSearchParamDTO param = new CustomSearchParamDTO();
                    param.setTamanhoCalcado(tamanhoCalcado.getDescricaoTamanhoCalcado());
                    List<Calcado> calcadosUsados = new CalcadoDAO().buscarCalcadosPorFiltro(0,10,param);
                    if(!calcadosUsados.isEmpty()){
                        btDeletarTamanho.setEnabled(false);
                        btDeletarTamanho.setToolTipText("Este tamanho esta sendo utilizado por um ou mais calçados.");
                        ToolTipManager.sharedInstance().setDismissDelay(Integer.MAX_VALUE);
                    }else{
                        btDeletarTamanho.setEnabled(true);
                        btDeletarTamanho.setToolTipText("");
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }



        });

        btDeletarCor.addActionListener((evt)->{
            try {
                CorCalcado corCalcado = (CorCalcado) comboCorCalcado.getModel().getSelectedItem();
                if(corCalcado.getIdCorCalcado() != 0){
                        int resp = JOptionPane.showConfirmDialog(null,
                                "Tem certeza?",
                                "Deletar",
                                JOptionPane.YES_NO_OPTION,
                                -1);
                        if(resp == JOptionPane.YES_OPTION){
                            new CorDAO().deletarCor(corCalcado);
                            preencheComboCor();
                        }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btDeletarCategoria.addActionListener((evt)->{
            try {
                CategoriaCalcado categoriaCalcado = (CategoriaCalcado) comboCategoriaCalcado.getModel().getSelectedItem();
                if(categoriaCalcado.getIdCategoriaCalcado() != 0){
                    int resp = JOptionPane.showConfirmDialog(null,
                                "Tem certeza?",
                                "Deletar",
                                JOptionPane.YES_NO_OPTION,
                                -1);
                        if(resp == JOptionPane.YES_OPTION){
                            new CategoriaDAO().deletarCategoria(categoriaCalcado);
                            preencheComboCategoria();
                        }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btDeletarMarca.addActionListener((evt)->{
            try {
                MarcaCalcado marcaCalcado = (MarcaCalcado) comboMarcaCalcado.getModel().getSelectedItem();
                if(marcaCalcado.getIdMarcaCalcado() != 0){
                        int resp = JOptionPane.showConfirmDialog(null,
                                "Tem certeza?",
                                "Deletar",
                                JOptionPane.YES_NO_OPTION,
                                -1);
                        if(resp == JOptionPane.YES_OPTION){
                            new MarcaDAO().deletarMarca(marcaCalcado);
                            preencheComboMarca();
                        }
                    }

            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btDeletarTamanho.addActionListener((evt)->{
            try {
                TamanhoCalcado tamanhoCalcado = (TamanhoCalcado) comboTamanhoCalcado.getModel().getSelectedItem();
                if(tamanhoCalcado.getIdTamanhoCalcado() != 0){
                        int resp = JOptionPane.showConfirmDialog(null,
                                "Tem certeza?",
                                "Deletar",
                                JOptionPane.YES_NO_OPTION,
                                -1);
                        if(resp == JOptionPane.YES_OPTION){
                            new TamanhoDAO().deletarTamanhoCalcado(tamanhoCalcado);
                            preencheComboTamanho();
                        }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        btCancelar.addActionListener((evt) ->{

            int resp = JOptionPane.showConfirmDialog(null,
                    "Tem certeza?",
                    "CANCELAR",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if(resp == JOptionPane.YES_OPTION){
                dispose();
            }

        });

    }

    private void preencherTodosOsCombos(){
        preencheComboCategoria();
        preencheComboMarca();
        preencheComboCor();
        preencheComboTamanho();
    }

    private void preencheComboMarca(){
        try{
            comboMarcaCalcado.removeAllItems();
            comboMarcaCalcado.addItem(new MarcaCalcado(0,"NENHUM",null));
            for(MarcaCalcado marcaCalcado: new MarcaDAO().buscarTodasAsMarcas()){
                comboMarcaCalcado.addItem(marcaCalcado);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "Ocorreu um erro ao preencher o combo de Marca!\n" + e.getMessage(),
                    "ERRO",
                    0);
            e.printStackTrace();
        }
    }

    private void preencheComboTamanho(){
        try{
            comboTamanhoCalcado.removeAllItems();
            comboTamanhoCalcado.addItem(new TamanhoCalcado(0,"NENHUM",null));
            for(TamanhoCalcado tamanhoCalcado: new TamanhoDAO().buscarTodosOsTamanhos()){
                comboTamanhoCalcado.addItem(tamanhoCalcado);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "Ocorreu um erro ao preencher o combo de Tamanho!\n" + e.getMessage(),
                    "ERRO",
                    0);
            e.printStackTrace();
        }
    }

    private void preencheComboCor(){
        try{
            comboCorCalcado.removeAllItems();
            comboCorCalcado.addItem(new CorCalcado(0,"NENHUM",null));
            for(CorCalcado corCalcado: new CorDAO().buscarTodasAsCores()){
                comboCorCalcado.addItem(corCalcado);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "Ocorreu um erro ao preencher o combo de Cor!\n" + e.getMessage(),
                    "ERRO",
                    0);
            e.printStackTrace();
        }
    }

    private void preencheComboCategoria(){
        try{

            comboCategoriaCalcado.removeAllItems();
            comboCategoriaCalcado.addItem(new CategoriaCalcado(0,"NENHUM",null));

            for(CategoriaCalcado categoriaCalcado: new CategoriaDAO().buscarTodasAsCategorias()){
                comboCategoriaCalcado.addItem(categoriaCalcado);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
                    "Ocorreu um erro ao preencher o combo de Categoria!\n" + e.getMessage(),
                    "ERRO",
                    0);
            e.printStackTrace();
        }
    }

}
