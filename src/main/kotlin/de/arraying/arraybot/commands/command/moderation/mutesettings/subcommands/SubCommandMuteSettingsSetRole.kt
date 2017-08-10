package de.arraying.arraybot.commands.command.moderation.mutesettings.subcommands

import de.arraying.arraybot.commands.other.CommandEnvironment
import de.arraying.arraybot.core.iface.ISubCommand
import de.arraying.arraybot.core.language.Messages
import de.arraying.arraybot.utils.UPermission

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
class SubCommandMuteSettingsSetRole:
        ISubCommand {

    /**
     * Gets the name.
     */
    override fun getName(): String {
        return "setrole"
    }

    /**
     * Gets the aliases.
     */
    override fun getAliases(): Array<String> {
        return arrayOf( "role")
    }

    /**
     * Invokes the subcommand.
     */
    override fun onSubCommand(environment: CommandEnvironment, args: Array<String>) {
        val channel = environment.channel
        if(args.size < 3) {
            Messages.COMMAND_MUTESETTINGS_SETROLE_PROVIDE.send(channel).queue()
            return
        }
        val roleId = args[2]
        if(!environment.guild.roles.any {
            it.id == roleId
        }) {
            Messages.COMMAND_MUTESETTINGS_SETROLE_INVALID.send(channel).queue()
            return
        }
        environment.cache!!.mod!!.muteRole = roleId.toLong()
        Messages.COMMAND_MUTESETTINGS_SETROLE_UPDATE.send(channel).queue()
    }

}