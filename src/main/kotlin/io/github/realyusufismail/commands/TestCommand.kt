package io.github.realyusufismail.commands

import io.github.realyusufismail.backend.builder.slash.SlashCommandBuilder
import io.github.realyusufismail.backend.builder.slash.SlashCommandFinaliser
import io.github.realyusufismail.backend.extension.SlashCommandExtender
import io.github.ydwk.ydwk.interaction.application.SlashCommand

class TestCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        event.reply(event.toString())
    }

    override fun build(): SlashCommandFinaliser {
        return SlashCommandBuilder("test", "Used for testing").build()
    }
}
