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
import io.github.ydwk.ydwk.entities.User
import io.github.ydwk.ydwk.entities.guild.enums.GuildPermission
import io.github.ydwk.ydwk.interaction.application.SlashCommand
import io.github.ydwk.ydwk.slash.SlashOption
import io.github.ydwk.ydwk.slash.SlashOptionType
import kotlin.time.Duration

class BanCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        val user: User =
            event.getOption("user")?.asUser ?: throw IllegalArgumentException("User is null")
        val reason: String =
            event.getOption("reason")?.asString ?: throw IllegalArgumentException("Reason is null")
        val guild = event.guild

        if (guild == null) {
            event.reply("This command can only be used in a guild").get()
            return
        }

        if (!event.member!!.hasPermission(GuildPermission.BAN_MEMBERS)) {
            event.reply("You don't have permission to ban members").get()
            return
        }

        if (!guild.botAsMember.hasPermission(GuildPermission.BAN_MEMBERS)) {
            event.reply("I don't have permission to ban members").get()
            return
        }

        guild.banUser(user, Duration.ZERO, reason).thenAccept {
            event.reply("Banned ${user.name} for $reason").get()
        }
    }

    override fun build(): SlashCommandFinaliser {
        return SlashCommandBuilder("ban", "Used to ban a user", guildOnlyCommand = true)
            .addOption(SlashOption("user", "The user to ban", SlashOptionType.USER, true))
            .addOption(
                SlashOption("reason", "The reason for the ban", SlashOptionType.STRING, true))
            .build()
    }
}
