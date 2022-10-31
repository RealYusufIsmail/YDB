/*
 * Copyright 2022 RealYusufIsmail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.commands.moderation

import io.github.realyusufismail.backend.builder.slash.SlashCommandBuilder
import io.github.realyusufismail.backend.builder.slash.SlashCommandFinaliser
import io.github.realyusufismail.backend.extension.SlashCommandExtender
import io.github.ydwk.ydwk.entities.guild.Member
import io.github.ydwk.ydwk.entities.guild.enums.GuildPermission
import io.github.ydwk.ydwk.interaction.application.SlashCommand
import io.github.ydwk.ydwk.slash.SlashOption
import io.github.ydwk.ydwk.slash.SlashOptionType

class KickCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        val user: Member? = event.getOption("user")?.asMember

        val reason: String? = event.getOption("reason")?.asString

        val guild = event.guild

        if (guild == null) {
            event.reply("This command can only be used in a guild").get()
            return
        }

        if (reason == null) {
            event.reply("Please provide a reason").get()
            return
        }

        if (user == null) {
            event.reply("User is null").get()
            return
        }

        if (!event.member!!.hasPermission(GuildPermission.KICK_MEMBERS)) {
            event.reply("You don't have permission to kick members").get()
            return
        }

        if (!guild.botAsMember.hasPermission(GuildPermission.KICK_MEMBERS)) {
            event.reply("I don't have permission to kick members").get()
            return
        }

        guild.kickMember(user, reason).thenAccept {
            event.reply("Kicked ${user.name} for $reason").get()
        }
    }

    override fun build(): SlashCommandFinaliser {
        return SlashCommandBuilder("kick", "Used to kick a user", guildOnlyCommand = true)
            .addOption(SlashOption("user", "The user to kick", SlashOptionType.USER, true))
            .addOption(
                SlashOption("reason", "The reason for the kick", SlashOptionType.STRING, true))
            .build()
    }
}
