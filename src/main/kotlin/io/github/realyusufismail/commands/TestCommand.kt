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
package io.github.realyusufismail.commands

import io.github.realyusufismail.backend.builder.slash.SlashCommandBuilder
import io.github.realyusufismail.backend.builder.slash.SlashCommandFinaliser
import io.github.realyusufismail.backend.extension.SlashCommandExtender
import io.github.ydwk.ydwk.interaction.application.SlashCommand

class TestCommand : SlashCommandExtender {
    override fun onSlashCommand(event: SlashCommand) {
        event.reply(event.toString()).reply()
    }

    override fun build(): SlashCommandFinaliser {
        return SlashCommandBuilder("test", "Used for testing").build()
    }
}
