package com.oscar.view;

import com.oscar.DAO.*;
import com.oscar.DTO.CustomSearchParamDTO;
import com.oscar.model.*;
import com.oscar.util.ABMTextField;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal(){
        criaTela();
    }

    public static void main(String[] args){

        TelaPrincipal t = new TelaPrincipal();
        t.setVisible(true);

    }

    JPanel painelPrincipal = new JPanel(new BorderLayout());
    JPanel painelSuperiorTela = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JPanel painelInferiorTela = new JPanel(new BorderLayout());

    JPanel painelLateralFiltros = new JPanel();

    JPanel painelCentralTabela = new JPanel(new BorderLayout());
    JPanel painelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel painelDescricao = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelPreco = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelMarca = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelCor = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelTamanho = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel painelCategoria = new JPanel(new FlowLayout(FlowLayout.LEFT));

    JPanel painelControlesPagina = new JPanel();
    JPanel painelItensPagina = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JTextField txtPequisaDescricao = new JTextField();
    ABMTextField txtPesquisaPreco = new ABMTextField(new DecimalFormat("0.00"));

    JComboBox<MarcaCalcado> comboMarcaCalcado = new JComboBox<>();
    JComboBox<TamanhoCalcado> comboTamanhoCalcado = new JComboBox<>();
    JComboBox<CategoriaCalcado> comboCategoriaCalcado = new JComboBox<>();
    JComboBox<CorCalcado> comboCorCalcado = new JComboBox<>();

    JRadioButton rdPrecoGT = new JRadioButton("Maior que");
    JRadioButton rdPrecoLT = new JRadioButton("Menor que");
    JRadioButton rdPrecoEQ = new JRadioButton("Igual a");

    ButtonGroup gpRdPreco = new ButtonGroup();

    JToggleButton toggleFiltros = new JToggleButton(">");

    Date dataAtual = new Date(System.currentTimeMillis());

    DefaultTableModel tabelaCalcadosModel = new DefaultTableModel(null,
            new String[]{"ID","COD", "DESCRIÇÃO","TAMANHO","CATEGORIA","MARCA","COR","PREÇO", "DATA CAD.", ""}) {
        @Override
        public boolean isCellEditable(int rowIndex, int collIndex) {
            return false;
        }
        @Override
        public Class<?> getColumnClass(int colNum) {
            switch (colNum) {
                case 0:
                    return Integer.class;
                case 7:
                    return Double.class;
                case 8:
                    return LocalDateTime.class;
                case 9:
                    return Calcado.class;
                default:
                    return String.class;
            }
        }


    };
    JTable tabelaCalcados = new JTable(tabelaCalcadosModel);
    JTableHeader tabelaCalcadosHeader = tabelaCalcados.getTableHeader();
    JScrollPane scrollTabelaCalcados = new JScrollPane(tabelaCalcados, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    JButton btConfigs = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/imagesTelaPrincipal/settings.png"))));

    JButton btProximaPagina = new JButton("prox. ->");
    JButton btPaginaAnterior = new JButton("<- ant.");

    JLabel lbPaginaAtual = new JLabel("0");

    JLabel lbItensPagina = new JLabel("Itens por página:");

    JComboBox<Integer> comboItensPagina = new JComboBox<>();


    JButton btAddCalcado = new JButton("Cadastrar Novo Calçado");

    int paginaAtual = 0, totalPaginas = 0, itensPorPagina = 10;

    private void criaTela(){

        this.setSize(new Dimension(1024, 768));
        this.setTitle("API CRUD DE CALÇADOS");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.getContentPane().setLayout(new BorderLayout());
        painelSuperiorTela.setBackground(new Color(227, 87, 17));

        txtPequisaDescricao.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "DESCRIÇÃO"));
        txtPequisaDescricao.setPreferredSize(new Dimension(200,40));
        painelDescricao.add(txtPequisaDescricao);

        gpRdPreco.add(rdPrecoEQ);
        gpRdPreco.add(rdPrecoGT);
        gpRdPreco.add(rdPrecoLT);
        rdPrecoEQ.setSelected(true);

        txtPesquisaPreco.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "PREÇO"));
        txtPesquisaPreco.setPreferredSize(new Dimension(100,40));
        painelPreco.add(txtPesquisaPreco);
        painelPreco.add(rdPrecoEQ);
        painelPreco.add(rdPrecoGT);
        painelPreco.add(rdPrecoLT);

        painelCategoria.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "CATEGORIA"));
        comboCategoriaCalcado.setMinimumSize(new Dimension(150,40));
        painelCategoria.add(comboCategoriaCalcado);

        painelCor.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "COR"));
        comboCorCalcado.setMinimumSize(new Dimension(150,40));
        painelCor.add(comboCorCalcado);

        painelMarca.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "MARCA"));
        comboMarcaCalcado.setMinimumSize(new Dimension(150,40));
        painelMarca.add(comboMarcaCalcado);

        painelTamanho.setBorder(new TitledBorder(new LineBorder(new Color(80,80,80), 2), "TAMANHO"));
        comboTamanhoCalcado.setMinimumSize(new Dimension(150,40));
        painelTamanho.add(comboTamanhoCalcado);

        painelLateralFiltros.add(painelDescricao);
        painelLateralFiltros.add(painelPreco);
        painelLateralFiltros.add(painelTamanho);
        painelLateralFiltros.add(painelMarca);
        painelLateralFiltros.add(painelCategoria);
        painelLateralFiltros.add(painelCor);

        painelLateralFiltros.setLayout(new BoxLayout(painelLateralFiltros, BoxLayout.Y_AXIS));
        painelLateralFiltros.setVisible(false);

        toggleFiltros.setFont(new Font("ARIAL", Font.BOLD, 15));

        //Configurando tabela
        tabelaCalcadosHeader.setReorderingAllowed(false);
        tabelaCalcadosHeader.setUpdateTableInRealTime(true);
        tabelaCalcadosHeader.setOpaque(false);
        tabelaCalcadosHeader.setBackground(new Color(80, 80, 80));
        tabelaCalcadosHeader.setFont(new Font("Arial Black", Font.PLAIN, 15));
        tabelaCalcadosHeader.setForeground(Color.white);

        tabelaCalcados.setFont(new Font("Arial", Font.PLAIN, 15));
        tabelaCalcados.setRowSelectionAllowed(true);
        tabelaCalcados.setColumnSelectionAllowed(false);
        tabelaCalcados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaCalcados.setAutoCreateRowSorter(true);
        tabelaCalcados.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                if (isSelected) {
                    c.setBackground(new Color(35, 91, 175));
                }
                if (column == 8) {
                    setHorizontalAlignment(RIGHT);
                }

                return c;

            }

        });

        //Tamanho das colunas
        tabelaCalcados.getColumnModel().getColumn(9).setMaxWidth(0);
        tabelaCalcados.getColumnModel().getColumn(9).setMinWidth(0);
        tabelaCalcados.getColumnModel().getColumn(9).setPreferredWidth(0);

        btConfigs.setOpaque(false);
        btConfigs.setBorderPainted(false);
        btConfigs.setContentAreaFilled(false);
        btConfigs.setCursor(new Cursor(Cursor.HAND_CURSOR));

        toggleFiltros.setToolTipText("Filtros");

        painelSuperiorTela.add(btConfigs);

        painelPesquisa.add(toggleFiltros);
        painelPesquisa.add(btAddCalcado);

        painelControlesPagina.add(btPaginaAnterior);
        painelControlesPagina.add(lbPaginaAtual);
        painelControlesPagina.add(btProximaPagina);

        comboItensPagina.addItem(10);
        comboItensPagina.addItem(25);
        comboItensPagina.addItem(50);
        comboItensPagina.addItem(100);

        painelItensPagina.add(comboItensPagina);
        painelItensPagina.add(lbItensPagina);

        painelInferiorTela.add(painelControlesPagina, BorderLayout.CENTER);
        painelInferiorTela.add(painelItensPagina, BorderLayout.EAST);

        painelCentralTabela.add(painelPesquisa, BorderLayout.NORTH);
        painelCentralTabela.add(scrollTabelaCalcados, BorderLayout.CENTER);

        painelPrincipal.add(painelCentralTabela, BorderLayout.CENTER);
        painelPrincipal.add(painelLateralFiltros, BorderLayout.WEST);

        this.getContentPane().add(painelSuperiorTela, BorderLayout.NORTH);
        this.getContentPane().add(painelPrincipal, BorderLayout.CENTER);
        this.getContentPane().add(painelInferiorTela, BorderLayout.SOUTH);

        preencherTodosOsCombos();
        Action();
        preencherTabelaCalcados(null);

    }
    private void Action(){

        btProximaPagina.addActionListener((evt)->{

            if(paginaAtual < totalPaginas) {
                paginaAtual++;
                preencherTabelaCalcados(preencherParametros());
            }

        });

        btPaginaAnterior.addActionListener((evt)->{
            if(paginaAtual +1 > 1){
                paginaAtual--;
                preencherTabelaCalcados(preencherParametros());
            }
        });

        comboItensPagina.addItemListener((evt)->{
            itensPorPagina = (Integer) comboItensPagina.getModel().getSelectedItem();
            preencherTabelaCalcados(preencherParametros());
        });

        btConfigs.addActionListener((evt)->{
            JDialog miniConfig = new JDialog();
            miniConfig.getContentPane().add(new TelaMiniConfig(true));
            miniConfig.setSize(new Dimension(500,180));
            miniConfig.setLocationRelativeTo(null);
            miniConfig.setResizable(false);
            miniConfig.setTitle("Alterar Configs.");
            miniConfig.setModal(true);

            miniConfig.setVisible(true);

        });

        toggleFiltros.addItemListener((evt)->{

            if(toggleFiltros.isSelected()){
                painelLateralFiltros.setVisible(true);
                toggleFiltros.setText("<");
            }else{
                painelLateralFiltros.setVisible(false);
                toggleFiltros.setText(">");
            }

        });

        btAddCalcado.addActionListener((evt)->{

            TelaAddCalcado t = new TelaAddCalcado(false);
            t.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if(t.comboTamanhoCalcado.getItemCount() > comboTamanhoCalcado.getItemCount()){
                        preencheComboTamanho();
                    }if(t.comboMarcaCalcado.getItemCount() > comboMarcaCalcado.getItemCount()){
                        preencheComboMarca();
                    }if(t.comboCorCalcado.getItemCount() > comboCorCalcado.getItemCount()){
                        preencheComboCor();
                    }if(t.comboCategoriaCalcado.getItemCount() > comboCategoriaCalcado.getItemCount()){
                        preencheComboCategoria();
                    }
                }
            });
            t.setVisible(true);

        });

        ActionListener searchActionListener = e -> {
            preencherTabelaCalcados(preencherParametros());
        };

        ItemListener searchItemListener = e -> {
            preencherTabelaCalcados(preencherParametros());
        };
        KeyListener keyTxt = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) preencherTabelaCalcados(preencherParametros());
            }
        };

        PopupMenuListener datePickerListener = new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

            }
            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                preencherTabelaCalcados(preencherParametros());
            }
            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {}
        };

        txtPesquisaPreco.addKeyListener(keyTxt);
        txtPequisaDescricao.addKeyListener(keyTxt);

        rdPrecoEQ.addItemListener(searchItemListener);
        rdPrecoGT.addItemListener(searchItemListener);
        rdPrecoLT.addItemListener(searchItemListener);

        comboCorCalcado.addActionListener(searchActionListener);
        comboCategoriaCalcado.addActionListener(searchActionListener);
        comboTamanhoCalcado.addActionListener(searchActionListener);
        comboMarcaCalcado.addActionListener(searchActionListener);

        tabelaCalcados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2){

                    int row = tabelaCalcados.getSelectedRow();

                    if(row >= 0){

                        Calcado calcado = (Calcado) tabelaCalcados.getValueAt(row, 9);

                        TelaAddCalcado telaAddCalcado = new TelaAddCalcado(true);
                        telaAddCalcado.hiddenId = calcado.getIdCalcado();
                        telaAddCalcado.txtDescricaoCalcado.setText(calcado.getDescricaoCalcado());
                        telaAddCalcado.txtCodCalcado.setText(calcado.getCodCalcado());
                        telaAddCalcado.txtPrecoCalcado = new JTextField();
                        telaAddCalcado.txtPrecoCalcado.setText(new DecimalFormat("0.00").format(calcado.getPrecoCalcado()));
                        telaAddCalcado.txtQtdEstoque.setValue(calcado.getPrecoCalcado().intValue());


                        telaAddCalcado.comboCorCalcado.getModel().setSelectedItem(calcado.getCorCalcado());
                        telaAddCalcado.comboCategoriaCalcado.getModel().setSelectedItem(calcado.getCategoriaCalcado());
                        telaAddCalcado.comboTamanhoCalcado.getModel().setSelectedItem(calcado.getTamanhoCalcado());
                        telaAddCalcado.comboMarcaCalcado.getModel().setSelectedItem(calcado.getMarcaCalcado());

                        telaAddCalcado.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                if(telaAddCalcado.comboTamanhoCalcado.getItemCount() > comboTamanhoCalcado.getItemCount()){
                                    preencheComboTamanho();
                                }if(telaAddCalcado.comboMarcaCalcado.getItemCount() > comboMarcaCalcado.getItemCount()){
                                    preencheComboMarca();
                                }if(telaAddCalcado.comboCorCalcado.getItemCount() > comboCorCalcado.getItemCount()){
                                    preencheComboCor();
                                }if(telaAddCalcado.comboCategoriaCalcado.getItemCount() > comboCategoriaCalcado.getItemCount()){
                                    preencheComboCategoria();
                                }
                                preencherTabelaCalcados(preencherParametros());
                            }
                        });

                        telaAddCalcado.setVisible(true);

                    }

                }
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

    private void preencherTabelaCalcados(CustomSearchParamDTO param){

        try{
            List<Calcado>  calcadosResultList;
            itensPorPagina = 10;
            if(comboItensPagina.getSelectedItem() != null){
                itensPorPagina = (Integer) comboItensPagina.getSelectedItem();
            }
            if(param != null){
                calcadosResultList = new CalcadoDAO().buscarCalcadosPorFiltro(paginaAtual, itensPorPagina, param);
            }else{
                calcadosResultList = new CalcadoDAO().buscarTodosOsCalcados(paginaAtual, itensPorPagina);
            }
            tabelaCalcadosModel.setNumRows(0);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            for(Calcado calcado: calcadosResultList){

                paginaAtual = calcado.getCurPage();
                totalPaginas = calcado.getPageCount();

                btProximaPagina.setEnabled(!calcado.isLastPage());
                btPaginaAnterior.setEnabled(paginaAtual + 1 > 1);

                lbPaginaAtual.setText(paginaAtual +1 + "/"+ totalPaginas);

                tabelaCalcadosModel.addRow(new Object[]{
                        calcado.getIdCalcado(),
                        calcado.getCodCalcado(),
                        calcado.getDescricaoCalcado(),
                        calcado.getTamanhoCalcado().getDescricaoTamanhoCalcado(),
                        calcado.getCategoriaCalcado().getDescricaoCategoria(),
                        calcado.getMarcaCalcado().getDescricaoMarcaCalcado(),
                        calcado.getCorCalcado().getDescricaoCorCalcado(),
                        calcado.getPrecoCalcado(),
                        calcado.getDataCadastro().getDayOfMonth() + "/" +calcado.getDataCadastro().getMonth().getValue() + "/" +calcado.getDataCadastro().getYear(),
                        calcado
                });

            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao buscar dados para a tabela!\n", "Erro",0);
            e.printStackTrace();
        }

    }

    private CustomSearchParamDTO preencherParametros() {

        String descricao = null,
                tamanho = null,
                marca = null,
                categoria = null,
                cor = null;

        MarcaCalcado marcaCalcado = (MarcaCalcado) comboMarcaCalcado.getSelectedItem();
        TamanhoCalcado tamanhoCalcado = (TamanhoCalcado) comboTamanhoCalcado.getSelectedItem();
        CategoriaCalcado categoriaCalcado = (CategoriaCalcado) comboCategoriaCalcado.getSelectedItem();
        CorCalcado corCalcado = (CorCalcado) comboCorCalcado.getSelectedItem();

        if(!txtPequisaDescricao.getText().isEmpty()){
            descricao = txtPequisaDescricao.getText();
        }

        if (marcaCalcado != null) {
            if(marcaCalcado.getIdMarcaCalcado() != 0){
                marca = marcaCalcado.getDescricaoMarcaCalcado();
            }
        }

        if(tamanhoCalcado != null){
            if(tamanhoCalcado.getIdTamanhoCalcado() != 0){
                tamanho = tamanhoCalcado.getDescricaoTamanhoCalcado();
            }
        }

        if(categoriaCalcado != null){
            if(categoriaCalcado.getIdCategoriaCalcado() != 0){
                categoria = categoriaCalcado.getDescricaoCategoria();
            }
        }

        if(corCalcado != null){
            if(corCalcado.getIdCorCalcado() != 0){
                cor = corCalcado.getDescricaoCorCalcado();
            }
        }

        Double preco = null,
                precoGT = null,
                precoLT = null;

        if(Double.parseDouble(txtPesquisaPreco.getText().replace(",",".")) != 0.00){

            if(rdPrecoEQ.isSelected()){
                preco = Double.parseDouble(txtPesquisaPreco.getText().replace(",","."));
            }else if (rdPrecoGT.isSelected()){
                precoGT = Double.parseDouble(txtPesquisaPreco.getText().replace(",","."));
            } else if(rdPrecoLT.isSelected()){
                precoLT = Double.parseDouble(txtPesquisaPreco.getText().replace(",","."));
            }

        }

        return new CustomSearchParamDTO(descricao,
                tamanho,
                marca,
                categoria,
                cor,
                preco,
                precoGT,
                precoLT
        );


    }

}
