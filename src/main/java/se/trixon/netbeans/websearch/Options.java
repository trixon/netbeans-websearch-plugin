/*
 * Copyright 2023 Patrik Karlström <patrik@trixon.se>.
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

import java.util.prefs.Preferences;
import org.openide.util.NbPreferences;

/**
 *
 * @author Patrik Karlström <patrik@trixon.se>
 */
public class Options {

    public static final String DEFAULT_URL_1 = "https://duckduckgo.com/?t=ffab&q=%s";
    public static final String KEY_URL_1 = "url1";

    private final Preferences preferences = NbPreferences.forModule(WebSearchAction.class);

    public static Options getInstance() {
        return OptionsHolder.INSTANCE;
    }

    private Options() {
    }

    public Preferences getPreferences() {
        return preferences;
    }

    private static class OptionsHolder {

        private static final Options INSTANCE = new Options();
    }
}
