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
package io.github.realyusufismail.reg

import io.github.realyusufismail.backend.extension.SlashCommandExtender
import io.github.realyusufismail.backend.handler.SlashHandler
import io.github.realyusufismail.commands.UptimeCommand
import io.github.realyusufismail.commands.moderation.BanCommand
import io.github.ydwk.ydwk.YDWK

class SlashCommandReg(ydwk: YDWK) : SlashHandler(ydwk) {

    init {
        val slashList: MutableList<SlashCommandExtender> = ArrayList()

        slashList.add(UptimeCommand())
        slashList.add(BanCommand())

        registerSlashCommands(slashList)
    }
}
