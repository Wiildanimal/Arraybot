package de.arraying.arraybot.startup.startups;

import de.arraying.arraybot.Arraybot;
import de.arraying.arraybot.listener.listeners.postload.GuildListener;
import de.arraying.arraybot.manager.BotManager;
import de.arraying.arraybot.startup.StartupTask;

import java.io.IOException;

/**
 * Copyright 2017 Arraying
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public final class StartupBot extends StartupTask {

    /**
     * Creates the bot startup task.
     */
    public StartupBot() {
        super("Startup-Bot");
    }

    /**
     * Runs the actual startup task.
     */
    @Override
    public void onTask() {
        logger.info("Creating the bot manager...");
        BotManager manager = new BotManager();
        Arraybot.INSTANCE.setBotManager(manager);
        logger.info("Starting the bot manager...");
        manager.start();
        logger.info("Finished starting the shards, they should be loading asynchronously if they are not loaded yet.");
        try {
            for(long guild : Arraybot.INSTANCE.getFileManager().getRemovalQueue()) {
                new GuildListener.Remover(guild, true).create();
            }
        } catch(IOException exception) {
            logger.error("Something went wrong loading in the Redis purge queue.", exception);
        }
    }

}
