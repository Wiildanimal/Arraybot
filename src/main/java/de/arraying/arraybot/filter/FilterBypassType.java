package de.arraying.arraybot.filter;

import de.arraying.arraybot.util.UFormatting;

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
public enum FilterBypassType {

    /**
     * When the bypass ID is a user.
     */
    USER,

    /**
     * When the bypass ID is a channel.
     */
    CHANNEL,

    /**
     * When the bypass ID is a role.
     */
    ROLE,

    /**
     * Unknown bypass type.
     */
    UNKNOWN;

    /**
     * Gets the filter bypass type from a string.
     * @param value The value of the type.
     * @return A bypass type or unknown.
     */
    public static FilterBypassType fromString(String value) {
        try {
            return FilterBypassType.valueOf(value.toUpperCase());
        } catch(IllegalArgumentException exception) {
            return UNKNOWN;
        }
    }

    /**
     * Gets all filter bypass types.
     * @return A comma seperated list.
     */
    public static String getTypes() {
        return UFormatting.formatToList(values());
    }

}
