package UI;

public class FrmGame extends javax.swing.JFrame {

    /**
     * Creates new form FrmGame
     */
    public FrmGame() {
        initComponents();
        pnGame.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnGame = new UI.Game();
        pnMenu = new UI.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnGame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pnGameKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pnGameLayout = new javax.swing.GroupLayout(pnGame);
        pnGame.setLayout(pnGameLayout);
        pnGameLayout.setHorizontalGroup(
            pnGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        pnGameLayout.setVerticalGroup(
            pnGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(pnGame, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 401, Short.MAX_VALUE)
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        getContentPane().add(pnMenu, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pnGameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pnGameKeyPressed
        pnGame.processKey(evt.getKeyCode());
    }//GEN-LAST:event_pnGameKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private UI.Game pnGame;
    private UI.Menu pnMenu;
    // End of variables declaration//GEN-END:variables
}
