/*
 * Copyright 2015 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.gwtplatform.dispatch.rest.processors.bindings.gin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.google.common.base.Optional;
import com.gwtplatform.dispatch.rest.processors.definitions.HasImports;
import com.gwtplatform.dispatch.rest.processors.definitions.TypeDefinition;

public class GinBinding implements HasImports {
    private final TypeDefinition implementer;
    private final Optional<TypeDefinition> implemented;
    private final Optional<TypeDefinition> scope;
    private final boolean eagerSingleton;

    public GinBinding(
            TypeDefinition implementer,
            TypeDefinition implemented,
            TypeDefinition scope,
            boolean eagerSingleton) {
        this.implementer = implementer;
        this.implemented = Optional.fromNullable(eagerSingleton ? null : implemented);
        this.scope = Optional.fromNullable(eagerSingleton ? null : scope);
        this.eagerSingleton = eagerSingleton;
    }

    public TypeDefinition getImplementer() {
        return implementer;
    }

    public Optional<TypeDefinition> getImplemented() {
        return implemented;
    }

    public Optional<TypeDefinition> getScope() {
        return scope;
    }

    public boolean isEagerSingleton() {
        return eagerSingleton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GinBinding that = (GinBinding) o;
        return Objects.equals(eagerSingleton, that.eagerSingleton) &&
                Objects.equals(implementer, that.implementer) &&
                Objects.equals(implemented, that.implemented) &&
                Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(implementer, implemented, scope, eagerSingleton);
    }

    @Override
    public Collection<String> getImports() {
        List<String> imports = new ArrayList<>(implementer.getImports());

        if (implemented.isPresent()) {
            imports.addAll(implemented.get().getImports());
        }
        if (scope.isPresent()) {
            imports.addAll(scope.get().getImports());
        }

        return imports;
    }
}
