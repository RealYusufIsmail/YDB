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
package io.github.realyusufismail.backend.type

import java.util.*

enum class CommandType {
    /** This command is a moderation command. */
    MODERATION,

    /** This command is a fun command. */
    FUN,

    /** This command is a utility command. */
    UTILITY,

    /** This command is a music command. */
    MUSIC,

    /** This command is owner only. */
    OWNER_ONLY,

    /** This command is used to provide info. */
    INFO,

    /** This command is used to provide support */
    SUPPORT,

    /** This command is used to set up something */
    SETUP,

    /** This command is under development. */
    DEVELOPMENT,

    /** This command is an example command. */
    EXAMPLE,

    /** A normal command. */
    NORMAL,

    /** No yet determined. */
    UNKNOWN;

    companion object {
        /**
         * This method is used to get the command type from a string.
         *
         * @param type the string to get the command type from.
         */
        fun getCommandTypeFromString(string: String): CommandType {
            return when (string.lowercase(Locale.ROOT)) {
                "moderation" -> MODERATION
                "fun" -> FUN
                "utility" -> UTILITY
                "music" -> MUSIC
                "owner_only" -> OWNER_ONLY
                "info" -> INFO
                "support" -> SUPPORT
                "setup" -> SETUP
                "development" -> DEVELOPMENT
                "example" -> EXAMPLE
                "normal" -> NORMAL
                else -> UNKNOWN
            }
        }
    }
}
