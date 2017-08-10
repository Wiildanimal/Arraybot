package de.arraying.arraybot.core.punishment.types

import de.arraying.arraybot.core.iface.IPunishment
import net.dv8tion.jda.core.entities.Guild
import net.dv8tion.jda.core.entities.Member
import net.dv8tion.jda.core.exceptions.PermissionException

/**
 * Copyright 2017 Arraying
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class PunishmentTypeSoftBan:
        IPunishment {

    /**
     * Invokes the punishment.
     */
    override fun invoke(guild: Guild, id: Long, member: Member, reason: String): Boolean {
        var success = false
        try {
            guild.controller.ban(member.user.id, 1, reason).queue {
                guild.controller.unban(member.user.id).queue {
                    success = true
                }
            }
        } catch(exception: IllegalArgumentException) {
        } catch(exception: PermissionException) {}
        return success
    }

}