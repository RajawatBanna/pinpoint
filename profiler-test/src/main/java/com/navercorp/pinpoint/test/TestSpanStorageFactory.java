/*
 * Copyright 2018 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

package com.navercorp.pinpoint.test;

import com.google.inject.Inject;
import com.navercorp.pinpoint.common.profiler.message.DataSender;
import com.navercorp.pinpoint.profiler.context.SpanChunkFactory;
import com.navercorp.pinpoint.profiler.context.SpanType;
import com.navercorp.pinpoint.profiler.context.module.SpanDataSender;
import com.navercorp.pinpoint.profiler.context.storage.BufferedStorage;
import com.navercorp.pinpoint.profiler.context.storage.Storage;
import com.navercorp.pinpoint.profiler.context.storage.StorageFactory;

import java.util.Objects;


/**
 * @author hyungil.jeong
 * @author emeroad
 */
public class TestSpanStorageFactory implements StorageFactory {

    private final DataSender<SpanType> dataSender;

    @Inject
    public TestSpanStorageFactory(@SpanDataSender DataSender<SpanType> dataSender) {
        this.dataSender = Objects.requireNonNull(dataSender, "dataSender");
    }

    @Override
    public Storage createStorage(SpanChunkFactory spanChunkFactory) {
        return new BufferedStorage(spanChunkFactory, this.dataSender, 1);
    }

}
