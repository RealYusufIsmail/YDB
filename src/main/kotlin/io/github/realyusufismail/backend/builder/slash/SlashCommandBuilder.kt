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
package io.github.realyusufismail.backend.builder.slash

import io.github.ydwk.ydwk.slash.Slash
import io.github.ydwk.ydwk.slash.SlashOption

class SlashCommandBuilder(
    val name: String,
    val description: String,
    val guildOnly: Boolean = false
) {
    var options: MutableList<SlashOption> = mutableListOf()

    fun addOption(option: SlashOption): SlashCommandBuilder {
        options.add(option)
        return this
    }

    fun addOptions(options: List<SlashOption>): SlashCommandBuilder {
        this.options.addAll(options)
        return this
    }

    fun build(): SlashCommandFinaliser {
        if (options.isNotEmpty()) {
            require(options.size <= 25) { "Cannot have more than 25 options" }
        }

        val cm = Slash(name, description, guildOnly).addOptions(options)

        return SlashCommandFinaliser(cm)
    }
}
