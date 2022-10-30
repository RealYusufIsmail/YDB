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

import io.github.realyusufismail.backend.type.CommandType
import io.github.ydwk.ydwk.slash.Slash

class SlashCommandFinaliser(val slash: Slash) {
    var isOwnerOnly = false
        private set
    private var commandType: CommandType? = null

    fun setToOwnerOnly(): SlashCommandFinaliser {
        isOwnerOnly = true
        return this
    }

    fun setCommandType(commandType: CommandType?): SlashCommandFinaliser {
        this.commandType = commandType
        return this
    }

    fun getCommandType(): CommandType? {
        return commandType
    }
}
