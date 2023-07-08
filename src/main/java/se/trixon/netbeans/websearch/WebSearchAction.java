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

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import javax.swing.ImageIcon;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.awt.Actions;
import org.openide.awt.HtmlBrowser;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "se.trixon.netbeans.websearch.WebSearchAction"
)
@ActionRegistration(
        displayName = "#CTL_WebSearchAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Tools", position = 99),
    @ActionReference(path = "Shortcuts", name = "DO-F"), //    @ActionReference(path = "UI/ToolActions/Files", position = 2050)
})
@Messages("CTL_WebSearchAction=Web search...")
/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public final class WebSearchAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //System.out.println(LocalTime.now().toString());
        var clipboad = Toolkit.getDefaultToolkit().getSystemClipboard();

        try {
            var originalTransferable = clipboad.getContents(null);

            Actions.forID("Edit", "org.openide.actions.CopyAction").actionPerformed(null);
            var transferable = clipboad.getContents(null);

            try {
                if (transferable.getTransferData(DataFlavor.stringFlavor) instanceof String searchString && searchString.length() > 0) {
                    var urlbase = Options.getInstance().getPreferences().get(Options.KEY_URL_1, Options.DEFAULT_URL_1);
                    try {
                        var encodedSearchString = URLEncoder.encode(searchString.trim(), "utf-8");
                        var uri = new URI(urlbase.formatted(encodedSearchString));
                        HtmlBrowser.URLDisplayer.getDefault().showURL(uri.toURL());
                    } catch (IllegalArgumentException ex) {
                        NotificationDisplayer.getDefault().notify(
                                "Invalid URL: " + urlbase,
                                new ImageIcon(),
                                "Check your settings in Tools/Options/Miscellaneous/Web search",
                                null,
                                NotificationDisplayer.Priority.HIGH
                        );
                    } catch (MalformedURLException | URISyntaxException ex) {
                        Exceptions.printStackTrace(ex);
                    }
                }
            } catch (UnsupportedFlavorException | IOException ex) {
                Exceptions.printStackTrace(ex);
            }

            clipboad.setContents(originalTransferable, null);
        } catch (IllegalStateException ex) {
            Exceptions.printStackTrace(ex);
        }
    }
}
