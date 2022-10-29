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

import net.dv8tion.jda.api.interactions.commands.OptionType

class SlashCommandBuilder
/**
 * Creates a new SlashCommandBuilder
 *
 * @param name The name of the command
 * @param description The description of the command
 */
(val name: String, val description: String) {
    private var options: Array<OptionData?>? = null
    private var subcommands: Array<SubcommandData>? = null
    private var subcommandGroups: Array<SubcommandGroupData>? = null
    fun getSubcommands(): List<SubcommandData> {
        return if (subcommands == null) java.util.List.of<SubcommandData>()
        else java.util.List.of(subcommands)
    }

    fun getSubcommandGroups(): List<SubcommandGroupData> {
        return if (subcommandGroups == null) java.util.List.of<SubcommandGroupData>()
        else java.util.List.of(subcommandGroups)
    }

    fun getOptions(): List<OptionData> {
        return if (options == null) java.util.List.of<OptionData>() else java.util.List.of(options)
    }

    fun addOptions(@Nonnull vararg options: OptionData?): SlashCommandBuilder {
        this.options = options
        return this
    }

    fun addOptions(@Nonnull options: Collection<OptionData?>): SlashCommandBuilder {
        this.options = options.toArray(arrayOfNulls<OptionData>(0))
        return this
    }

    fun addOption(type: OptionType, name: String, description: String): SlashCommandBuilder {
        options = arrayOf<OptionData?>(OptionData(type, name, description))
        return this
    }

    fun addOption(
        type: OptionType,
        name: String,
        description: String,
        required: Boolean
    ): SlashCommandBuilder {
        options = arrayOf<OptionData?>(OptionData(type, name, description, required))
        return this
    }

    fun addOption(
        @Nonnull type: OptionType?,
        @Nonnull name: String?,
        @Nonnull description: String?,
        required: Boolean,
        autoComplete: Boolean
    ): SlashCommandBuilder {
        options = arrayOf<OptionData?>(OptionData(type, name, description, required, autoComplete))
        return this
    }

    fun addSubcommands(vararg subcommands: SubcommandData): SlashCommandBuilder {
        this.subcommands = subcommands
        return this
    }

    fun addSubcommands(subcommands: Collection<SubcommandData?>): SlashCommandBuilder {
        this.subcommands = subcommands.toArray(arrayOfNulls<SubcommandData>(0))
        return this
    }

    fun addSubcommandGroups(vararg groups: SubcommandGroupData): SlashCommandBuilder {
        subcommandGroups = groups
        return this
    }

    fun addSubcommandGroups(groups: Collection<SubcommandGroupData?>): SlashCommandBuilder {
        subcommandGroups = groups.toArray(arrayOfNulls<SubcommandGroupData>(0))
        return this
    }

    fun build(): SlashCommand {
        require(!(name == null || name.isEmpty())) { "Name cannot be null or empty" }
        require(!(description == null || description.isEmpty())) {
            "Description cannot be null or empty"
        }
        if (options == null) {
            options = arrayOfNulls<OptionData>(0)
        }
        if (subcommands == null) {
            subcommands = arrayOfNulls<SubcommandData>(0)
        }
        if (subcommandGroups == null) {
            subcommandGroups = arrayOfNulls<SubcommandGroupData>(0)
        }
        val cm: Unit =
            Commands.slash(name, description)
                .addOptions(options)
                .addSubcommands(subcommands)
                .addSubcommandGroups(subcommandGroups)
        return SlashCommand(cm)
    }
}
