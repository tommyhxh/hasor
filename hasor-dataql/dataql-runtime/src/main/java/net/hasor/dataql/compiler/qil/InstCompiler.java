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
package net.hasor.dataql.compiler.qil;
import net.hasor.dataql.compiler.ast.Inst;

/**
 * 每一个 AST 树都会对应一个 InstCompiler
 * @author 赵永春 (zyc@hasor.net)
 * @version : 2017-03-23
 */
public interface InstCompiler<T extends Inst> extends Opcodes {
    /**
     * 生成指令序列
     * @param astInst 要编译的 Inst
     * @param queue 编译输出的指令序列
     * @param compilerContext 编译上下文
     */
    public abstract void doCompiler(T astInst, InstQueue queue, CompilerContext compilerContext);
}