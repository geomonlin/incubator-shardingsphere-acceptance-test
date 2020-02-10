/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.example.core.api.senario;


import org.apache.shardingsphere.example.core.api.service.TransactionService;

public abstract class AbstractTransactionScenario {
    
    private final TransactionService transactionService;
    
    public AbstractTransactionScenario(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    public TransactionService getTransactionService() {
        return transactionService;
    }
    
    protected final void doInTransactionWithSuccess() {
        try {
            transactionService.processSuccessWithLocal();
            transactionService.processSuccessWithXA();
            transactionService.processSuccessWithBase();
        } catch (final Exception ignore) {
        }
    }
    
    protected final void doInTransactionWithFailure() {
        try {
            transactionService.processFailureWithLocal();
        } catch (final Exception ex) {
            printData();
        }
        try {
            transactionService.processFailureWithXA();
        } catch (final Exception ex) {
            printData();
        }
        try {
            transactionService.processFailureWithBase();
        } catch (final Exception ex) {
            printData();
        }
    }
    
    private void printData() {
        try {
            transactionService.printData();
        } catch (final Exception ignore) {
        }
    }
    
//    protected final void processFailure(final TransactionType type) {
//        try {
//            switch (type) {
//                case LOCAL:
//                    transactionService.processFailureWithLocal();
//                    break;
//                case XA:
//                    transactionService.processFailureWithXA();
//                    break;
//                case BASE:
//                    transactionService.processFailureWithBase();
//                    break;
//                default:
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            transactionService.printData();
//        }
//    }
}
