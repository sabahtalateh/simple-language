package org.jetbrains.plugins.lang

import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.application.options.TabbedLanguageCodeStylePanel
import com.intellij.psi.codeStyle.CodeStyleConfigurable
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider
import com.intellij.psi.codeStyle.CustomCodeStyleSettings


class SimpleCodeStyleSettingsProvider : CodeStyleSettingsProvider() {
    override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings =
        SimpleCodeStyleSettings(settings)

    override fun getConfigurableDisplayName(): String = "Simple"

    override fun createConfigurable(
        settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings
    ): CodeStyleConfigurable {
        return object : CodeStyleAbstractConfigurable(settings, modelSettings, this.configurableDisplayName) {
            override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel {
                return SimpleCodeStyleMainPanel(currentSettings, settings)
            }
        }
    }

    private class SimpleCodeStyleMainPanel(currentSettings: CodeStyleSettings, settings: CodeStyleSettings) :
        TabbedLanguageCodeStylePanel(SimpleLanguage.INSTANCE, currentSettings, settings)
}