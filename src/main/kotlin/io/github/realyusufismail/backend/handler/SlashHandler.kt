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
package io.github.realyusufismail.backend.handler

import io.github.realyusufismail.backend.extension.SlashCommandExtender
import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.event.events.interaction.SlashCommandEvent
import io.github.ydwk.ydwk.slash.Slash

open class SlashHandler(private val ydwk: YDWK) : BaseHandler() {
    private val slashCommand: MutableMap<String, SlashCommandExtender> = HashMap()
    private val slashMutableList: MutableList<Slash> = ArrayList()

    init {
        ydwk.slashBuilder.getSlashCommands()
    }

    private fun addSlashCommands(command: SlashCommandExtender) {
        slashCommand[command.build().slash.name] = command
        if (command.build().isGuildOnly) {
            slashMutableList.add(
                Slash(command.build().slash.name, command.build().slash.description, true))
        } else {
            slashMutableList.add(
                Slash(command.build().slash.name, command.build().slash.description, false))
        }
    }

    fun registerSlashCommands(slashCommands: Collection<SlashCommandExtender>) {
        slashCommands.forEach { addSlashCommands(it) }
        onFirstSlash()
    }

    private fun onFirstSlash() {
        slashMutableList.forEach { ydwk.slashBuilder.addSlashCommand(it).build() }
    }

    override fun botOwnerId(): Long {
        return 0
    }

    override fun onSlashCommand(event: SlashCommandEvent) {
        val cmd: SlashCommandExtender = slashCommand[event.slash.name] ?: return
        cmd.onSlashCommand(event)
    }
}
