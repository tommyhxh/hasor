/*
 * Copyright 2008-2009 the original author or authors.
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
package net.hasor.dataql.runtime.inset;
import net.hasor.core.utils.StringUtils;
import net.hasor.dataql.InvokerProcessException;
import net.hasor.dataql.ProcessException;
import net.hasor.dataql.result.ListModel;
import net.hasor.dataql.runtime.mem.LocalData;
import net.hasor.dataql.runtime.mem.MemStack;
import net.hasor.dataql.runtime.process.InsetProcess;
import net.hasor.dataql.runtime.process.InstSequence;
import net.hasor.dataql.runtime.process.ProcessContet;

import java.util.Collection;
/**
 * NO，创建一个集合对象
 * @author 赵永春(zyc@hasor.net)
 * @version : 2017-07-19
 */
class NA implements InsetProcess {
    @Override
    public int getOpcode() {
        return NA;
    }
    @Override
    public void doWork(InstSequence sequence, MemStack memStack, LocalData local, ProcessContet context) throws ProcessException {
        String typeString = sequence.currentInst().getString(0);
        Class<?> listType = null;
        if (StringUtils.isNotBlank(typeString)) {
            listType = context.loadType(typeString);
        } else {
            listType = ListModel.class;
        }
        //
        if (!Collection.class.isAssignableFrom(listType)) {
            throw new InvokerProcessException(getOpcode(), "NA -> type " + listType + " is not Collection");
        }
        //
        try {
            memStack.push(listType.newInstance());
        } catch (Exception e) {
            throw new InvokerProcessException(getOpcode(), "NA -> " + e.getMessage(), e);
        }
    }
}