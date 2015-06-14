package UI;

import Core.Board;
import static UI.Menu.btLoad;
import static UI.Menu.btQuit;
import static UI.Menu.btSave;
import static UI.Menu.btStart;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends JFrame implements Runnable {

    private static Board board;
    private Menu menu;
    //Numero de vezes que o jogo vai ser atualizado por segundo
    private static int fps = 30;

    public Game() {
        //Define o tamanho da frame de forma a que tenha o tamanho minimo para
        //apresentar os seus componentes
        pack();
        //Define o titulo da frame
        setTitle("BomberMan");
        //Tamanho da frame
        setSize(20 * 20 + 3, 20 * 21 + 6 + 90);
        //Termina o exe quando o user fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Nao pode redimensionar a frame
        setResizable(false);
        //Define boxlayout nesta frame
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //Centra a frame no ecra
        setLocationRelativeTo(null);
        //Nova board
        board = new Board();
        board.setBorder(BorderFactory.createEmptyBorder(0, 0, 20 * 20 + 3, 20 * 21 + 6));
        //Adiciona um keylistener para ficar a escuta do carregamento de teclas
        board.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                boardKeyPressed(evt);
            }
        });
        //Adiciona a board a frame
        getContentPane().add(board);
        //Torna-a vizivel
        board.setVisible(true);
        //Da o foco a board para o utilizador poder controlar o jogador
        board.requestFocus();

        //Novo menu
        menu = new Menu();
        menu.setLocation(0, board.getHeight());
        menu.setBorder(BorderFactory.createEmptyBorder(0, 0, 90, 0));

        //Adiciona um ActionListener para ficar a escuta de eventos neste botao
        btStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                board.cleanBoard();
                board.buildLevel(0);
                board.start();
                board.requestFocus();
            }
        });

        btQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });

        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveGame();
                board.requestFocus();
            }
        });

        btLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                loadGame();
                
            }
        });

        //Adiciona o menu a Frame
        getContentPane().add(menu);
        menu.setVisible(true);

        //Reseta o layoutmanager
        this.revalidate();
        //Volta a pintar os componentes
        this.repaint();
        //Adiciona uma thread e inicia-a
        new Thread(this).start();
    }

    private void boardKeyPressed(java.awt.event.KeyEvent evt) {
        board.processKey(evt.getKeyCode());
    }

    //Metodo que irá gravar o estado atual do jogo
    public void saveGame() {
        try {
            //Data atual no seguinte formato que irá ser utilizada para dar nome
            //aos estados gravados
            //Foi feito com o intuito de impedir a gravacao de estados em cima 
            //de outros
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            FileOutputStream output = new FileOutputStream(timeStamp + ".dat");
            ObjectOutputStream objSave = new ObjectOutputStream(output);
            //Chama o memtodo saveMatrixBoard para colocar todas os dados
            //necessarios para a gravacao dentro de um array
            board.saveMatrixBoard();
            //escreve o objeto no ficheiro
            objSave.writeObject(board.getMatrixBoard());
            //Fecha o outputstream
            objSave.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo que ira repor o estado do jogo de acordo com o estado gravado
    public void loadGame() {
        try {
            String path = new String();
            //Cria um filechooser para o utilizador selecionar o save
            JFileChooser chooser = new JFileChooser();

            //Diretorio de abertura por defeito
            chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Select save to load");

            //Se o utilizador selecionar o save
            if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION) {
                //Vai buscar o camingo absoluto do ficheiro
                path = chooser.getSelectedFile().getAbsolutePath();
                
                FileInputStream input = new FileInputStream(path);
                ObjectInputStream inputSave = new ObjectInputStream(input);
                
                //adiciona a matrix do save a matrix do jogo
                board.setMatrixBoard((Object[]) inputSave.readObject());
                //fecha o stream
                inputSave.close();
                //limpa a board
                board.cleanBoard();
                //Repoe todos os objetos da matrix na board
                board.loadBoard();
                //Inicia a thread de jogo
                board.start();
            }
            //
            board.requestFocus();
        } catch (Exception e) {
            //Caso haja um erro ou o utilizador nao escolha um save
            JOptionPane.showMessageDialog(this, "Error loading", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        //Novo jogo
        Game game = new Game();
        //Coloca-o visivel
        game.setVisible(true);
    }

    //Atualiza o estado do jogo
    public void update() {
        //Atualiza as informacoes do menu. Pontos,vidas,etc.
        menu.label_points.setText("Points: " + board.getPoints());
        menu.label_lifes.setText("Lifes: " + board.getPlayer().getLifes());
        menu.label_numBombs.setText("Bombs: " + board.getPlayer().getNumBombs());
        menu.label_level.setText("Level: " + board.getNumLevel());
        menu.repaint();

        //Se o utilizador nao tiver vidas
        if (board.getPlayer().getLifes() == 0 && board.isRunning()) {
            //Para o jogo
            board.stop();
            //Mostra mensagem de derrota com os pontos
            JOptionPane.showMessageDialog(this, "Shame. You lost!\nYour score was " + board.getPoints() + " points",
                    "You lost!", JOptionPane.PLAIN_MESSAGE);
        }
        //Se o utilizador tiver partido todos os blocos
        if (board.getBricksRemaining() == 0 && board.isRunning() && board.getNumLevel() == 1) {
            board.stop();
            //Mostra a mensagem de vitoria com os pontos
            JOptionPane.showMessageDialog(this, "Congratulations. You won!\nYour score was " + board.getPoints() + " points",
                    "You won!", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //Corre a thread
    @Override
    public void run() {
        while (true) {
            //Atualiza o jogo ate a thread ser parada
            update();
            try {
                //Suspende a thread durante x milisegundos
                Thread.sleep(1000 / board.fps);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
