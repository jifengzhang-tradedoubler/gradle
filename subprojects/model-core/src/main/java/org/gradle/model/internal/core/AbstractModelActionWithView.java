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

package org.gradle.model.internal.core;

import org.gradle.model.internal.core.rule.describe.ModelRuleDescriptor;
import org.gradle.model.internal.type.ModelType;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractModelActionWithView<T> extends AbstractModelAction<T> {
    protected AbstractModelActionWithView(ModelReference<T> subject, ModelRuleDescriptor descriptor, ModelReference<?>... inputs) {
        this(subject, descriptor, Arrays.asList(inputs));
    }

    protected AbstractModelActionWithView(ModelReference<T> subject, ModelRuleDescriptor descriptor, List<? extends ModelReference<?>> inputs) {
        super(subject, descriptor, inputs);
    }

    @Override
    final public void execute(MutableModelNode node, List<ModelView<?>> inputs) {
        if (!node.isAtLeast(ModelNode.State.Created)) {
            throw new IllegalStateException("Cannot get view for node " + node.getPath() + " in state " + node.getState());
        }
        ModelType<T> type = getSubject().getType();
        ModelView<? extends T> view = node.asMutable(type, getDescriptor());
        try {
            execute(node, view.getInstance(), inputs);
        } finally {
            view.close();
        }
    }

    protected abstract void execute(MutableModelNode modelNode, T view, List<ModelView<?>> inputs);
}
