/*
 * Copyright 2015 the original author or authors.
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

package org.gradle.tooling.events.test;

import org.gradle.api.Incubating;
import org.gradle.tooling.events.ProgressEvent;

/**
 * Root interface for all events that signal progress while executing a test operation.
 *
 * @since 2.4
 */
@Incubating
public interface TestProgressEvent extends ProgressEvent {
    /**
     * Returns the description of the underlying test operation for which progress is reported. For JVM-based tests,
     * the descriptor is of sub-type {@code org.gradle.tooling.events.test.JvmTestOperationDescriptor}.
     *
     * @return The description of the underlying test operation.
     */
    @Override
    TestOperationDescriptor getDescriptor();

}