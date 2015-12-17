/*
 *   Licensed to the Apache Software Foundation (ASF) under one
 *   or more contributor license agreements.  See the NOTICE file
 *   distributed with this work for additional information
 *   regarding copyright ownership.  The ASF licenses this file
 *   to you under the Apache License, Version 2.0 (the
 *   "License"); you may not use this file except in compliance
 *   with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */

package org.apache.james.managesieve.transcode;

import org.apache.james.managesieve.api.ManageSieveException;
import org.apache.james.managesieve.api.Session;
import org.apache.james.managesieve.api.SieveParser;
import org.apache.james.managesieve.core.CoreProcessor;
import org.apache.james.managesieve.jsieve.Parser;
import org.apache.james.sieverepository.api.SieveRepository;
import org.apache.james.sieverepository.api.exception.SieveRepositoryException;
import org.apache.james.user.api.UsersRepository;

public class ManageSieveProcessor {

    public static final String AUTHENTICATE = "AUTHENTICATE";
    public static final String CAPABILITY = "CAPABILITY";
    public static final String CHECKSCRIPT = "CHECKSCRIPT";
    public static final String DELETESCRIPT = "DELETESCRIPT";
    public static final String GETACTIVE = "GETACTIVE";
    public static final String GETSCRIPT = "GETSCRIPT";
    public static final String HAVESPACE = "HAVESPACE";
    public static final String LISTSCRIPTS = "LISTSCRIPTS";
    public static final String LOGOUT = "LOGOUT";
    public static final String NOOP = "NOOP";
    public static final String PUTSCRIPT = "PUTSCRIPT";
    public static final String RENAMESCRIPT = "RENAMESCRIPT";
    public static final String SETACTIVE = "SETACTIVE";
    public static final String STARTTLS = "STARTTLS";
    public static final String UNAUTHENTICATE = "UNAUTHENTICATE";

    private final LineToCoreToLine lineToCoreToLine;

    public ManageSieveProcessor(LineToCoreToLine lineToCoreToLine) {
        this.lineToCoreToLine = lineToCoreToLine;
    }

    public String handleRequest(Session session, String request) throws ManageSieveException, SieveRepositoryException {
        int firstWordEndIndex = request.indexOf(' ');
        String command = request.substring(0, firstWordEndIndex);
        String arguments = request.substring(firstWordEndIndex);
        if (command.equals(AUTHENTICATE)) {
            return "NO AUTHENTICATE command not yet implemented";
        } else if (command.equals(CAPABILITY)) {
            return lineToCoreToLine.capability(session, arguments);
        } else if (command.equals(CHECKSCRIPT)) {
            return lineToCoreToLine.checkScript(session, arguments);
        } else if (command.equals(DELETESCRIPT)) {
            return lineToCoreToLine.checkScript(session, arguments);
        } else if (command.equals(GETACTIVE)) {
            return lineToCoreToLine.getActive(session, arguments);
        } else if (command.equals(GETSCRIPT)) {
            return lineToCoreToLine.getScript(session, arguments);
        } else if (command.equals(HAVESPACE)) {
            return lineToCoreToLine.haveSpace(session, arguments);
        } else if (command.equals(LISTSCRIPTS)) {
            return lineToCoreToLine.listScripts(session, arguments);
        } else if (command.equals(LOGOUT)) {
            return "NO LOGOUT command not yet implemented";
        } else if (command.equals(NOOP)) {
            return "NO NOOP command not yet implemented";
        } else if (command.equals(PUTSCRIPT)) {
            return lineToCoreToLine.putScript(session, arguments);
        } else if (command.equals(RENAMESCRIPT)) {
            return lineToCoreToLine.renameScript(session, arguments);
        } else if (command.equals(SETACTIVE)) {
            return lineToCoreToLine.setActive(session, arguments);
        } else if (command.equals(STARTTLS)) {
            return "NO STARTTLS command not yet implemented";
        } else if (command.equals(UNAUTHENTICATE)) {
            return "NO UNAUTHENTICATE command not yet handled";
        } else {
            return "NO unknown " + command + " command";
        }
    }

}