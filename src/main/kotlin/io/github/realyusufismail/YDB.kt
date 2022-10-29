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
package io.github.realyusufismail

import io.github.realyusufismail.jconfig.classes.JConfigBuilder
import io.github.realyusufismail.jconfig.classes.JConfigException
import io.github.ydwk.ydwk.BotBuilder
import io.github.ydwk.ydwk.YDWK
import io.github.ydwk.ydwk.evm.backend.event.on
import io.github.ydwk.ydwk.evm.event.events.gateway.ReadyEvent
import io.github.ydwk.ydwk.impl.YDWKImpl
import org.slf4j.LoggerFactory

val config = JConfigBuilder().build()

fun main() {
    val ydkw: YDWK =
        BotBuilder.createDefaultBot(
                config["token"]?.asString ?: throw JConfigException("Token not found"))
            .build()

    val logger = (ydkw as YDWKImpl).logger

    ydkw.on<ReadyEvent> {
        logger.info("Logged in as ${it.ydwk.bot?.name}")
    }
}
