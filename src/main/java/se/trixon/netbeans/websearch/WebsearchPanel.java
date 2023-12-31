/*
 * Copyright 2023 Patrik Karlström.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.trixon.netbeans.websearch;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
final class WebsearchPanel extends javax.swing.JPanel {

    private final WebsearchOptionsPanelController controller;

    WebsearchPanel(WebsearchOptionsPanelController controller) {
        this.controller = controller;
        initComponents();

        url1TextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                controller.changed();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.changed();
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header1Label = new javax.swing.JLabel();
        url1TextField = new javax.swing.JTextField();

        org.openide.awt.Mnemonics.setLocalizedText(header1Label, org.openide.util.NbBundle.getMessage(WebsearchPanel.class, "WebsearchPanel.header1Label.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(header1Label)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(url1TextField))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header1Label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(url1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    void load() {
        url1TextField.setText(Options.getInstance().getPreferences().get(Options.KEY_URL_1, Options.DEFAULT_URL_1));
    }

    void store() {
        Options.getInstance().getPreferences().put(Options.KEY_URL_1, url1TextField.getText());
    }

    boolean valid() {
        var url = url1TextField.getText();

        return url.indexOf("%s") == url.lastIndexOf("%s") && url.indexOf("%s") > 0;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel header1Label;
    private javax.swing.JTextField url1TextField;
    // End of variables declaration//GEN-END:variables
}
