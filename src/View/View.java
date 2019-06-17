package View;

import Datenbank.*;
import Model.Model;
import java.util.Observable;
import java.util.Observer;


public class View extends javax.swing.JFrame implements Observer
{
    
    private Model model;
    private String lastString;
    
    public View(ConnectionFailureException e)
    {
        // <editor-fold defaultstate="collapsed" desc="Komponenten des Fehlerfensters"> 
        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textareastment = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        textareaping = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        textareaerror = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textarea.setEditable(false);
        textarea.setColumns(20);
        textarea.setFont(new java.awt.Font("DejaVu Sans Mono", 0, 13)); // NOI18N
        textarea.setRows(5);
        jScrollPane1.setViewportView(textarea);

        jLabel1.setText("Datenbank");

        jButton1.setText("erneut Versuchen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textareastment.setColumns(20);
        textareastment.setRows(5);
        textareastment.setToolTipText("Geben Sie eine SQL-Abfrage ein");
        jScrollPane2.setViewportView(textareastment);

        textareaping.setColumns(20);
        textareaping.setRows(5);
        jScrollPane3.setViewportView(textareaping);

        textareaerror.setColumns(20);
        textareaerror.setRows(5);
        jScrollPane4.setViewportView(textareaerror);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(142, 142, 142))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(200, 200, 200)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        // </editor-fold>
        this.textareaerror.setText(e.getMessage() + "\nVersuchen Sie es erneut");     
    }
    
    public View(Model model)
    {
        initComponents();
        this.model = model;
        this.jButton1.setVisible(false);
        this.lastString = "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textarea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textareastment = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        textareaping = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        textareaerror = new javax.swing.JTextArea();
        buutonAbfrage = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textarea.setEditable(false);
        textarea.setColumns(20);
        textarea.setFont(new java.awt.Font("Courier New", 0, 13)); // NOI18N
        textarea.setRows(5);
        jScrollPane1.setViewportView(textarea);

        jLabel1.setText("Datenbank");

        jButton1.setText("erneut Versuchen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textareastment.setColumns(20);
        textareastment.setRows(5);
        textareastment.setToolTipText("Geben Sie eine SQL-Abfrage ein");
        jScrollPane2.setViewportView(textareastment);

        textareaping.setEditable(false);
        textareaping.setColumns(20);
        textareaping.setRows(5);
        jScrollPane3.setViewportView(textareaping);

        textareaerror.setEditable(false);
        textareaerror.setColumns(20);
        textareaerror.setRows(5);
        jScrollPane4.setViewportView(textareaerror);

        buutonAbfrage.setText("Abfrage abschicken");
        buutonAbfrage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buutonAbfrageActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buutonAbfrage)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(142, 142, 142))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buutonAbfrage)
                        .addGap(171, 171, 171)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
        Mainklasse.main(new String[0]);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buutonAbfrageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buutonAbfrageActionPerformed
        try
        {
            this.model.verifyAndSendStatement(textareastment.getText());
        }
        catch(SQLStatementException e)
        {
            this.textareaerror.setText(e.getMessage());
        }
        catch(StatementDeniedException e)
        {
            this.textareaerror.setText("Die Abfrage wurde blockiert: " + e.getMessage());
        }
    }//GEN-LAST:event_buutonAbfrageActionPerformed

    @Override
    public void update(Observable o, Object arg) 
    {
        if(arg instanceof String)
        {
            String obj = (String)arg;
            if(obj.equals("/timeout/"))
                this.textareaping.setText("Zeitüberschreitung bei der Verbindung(3000 Millisekunden)");
        }
        else if(arg instanceof Long)
        {
            long obj = (long)arg;
            this.textareaping.setText("Ping zum Server: " + Long.toString(obj) + " Millisekunden\n(gemessen an der letzten Abfrage)");
        }
        else
        {
            this.lastString = this.model.toString();
            this.textarea.setText(this.lastString);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buutonAbfrage;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private java.util.List list1;
    private javax.swing.JTextArea textarea;
    private javax.swing.JTextArea textareaerror;
    private javax.swing.JTextArea textareaping;
    private javax.swing.JTextArea textareastment;
    // End of variables declaration//GEN-END:variables
}
