/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.loganalysis.parser;

import com.android.loganalysis.item.MemInfoItem;
import com.android.loganalysis.util.ArrayUtil;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

/**
 * Unit tests for {@link MemInfoParser}
 */
public class MemInfoParserTest extends TestCase {

    /**
     * Test that normal input is parsed.
     */
    public void testMemInfoParser() {
        List<String> inputBlock = Arrays.asList(
                "MemTotal:         353332 kB",
                "MemFree:           65420 kB",
                "Buffers:           20800 kB",
                "Cached:            86204 kB",
                "SwapCached:            0 kB");

        MemInfoItem item = new MemInfoParser().parse(inputBlock);

        assertEquals(5, item.size());
        assertEquals((Integer)353332, item.get("MemTotal"));
        assertEquals((Integer)65420, item.get("MemFree"));
        assertEquals((Integer)20800, item.get("Buffers"));
        assertEquals((Integer)86204, item.get("Cached"));
        assertEquals((Integer)0, item.get("SwapCached"));
        assertEquals(ArrayUtil.join("\n", inputBlock), item.getText());
    }

    /**
     * Test that an empty input returns {@code null}.
     */
    public void testEmptyInput() {
        MemInfoItem item = new MemInfoParser().parse(Arrays.asList(""));
        assertNull(item);
    }
}
