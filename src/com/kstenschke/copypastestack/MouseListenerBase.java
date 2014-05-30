/*
* Copyright 2011-2014 Kay Stenschke
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
package com.kstenschke.copypastestack;

import com.kstenschke.copypastestack.Utils.UtilsEnvironment;
import org.jetbrains.annotations.Nullable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MouseListenerBase implements MouseListener {

        // Info text to be shown in status bar on hover
    private String description = null;

    /**
     * Constructor
     */
    public MouseListenerBase(@Nullable String description) {
        this.description = description;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if( description != null ) {
            UtilsEnvironment.setStatusBarInfo(description);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if( description != null ) {
            UtilsEnvironment.setStatusBarInfo(null);
        }
    }

}
