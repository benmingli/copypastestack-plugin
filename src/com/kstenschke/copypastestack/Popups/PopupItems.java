/*
 * Copyright 2014-2015 Kay Stenschke
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
package com.kstenschke.copypastestack.Popups;

import com.kstenschke.copypastestack.ListCellRendererCopyPasteStack;
import com.kstenschke.copypastestack.resources.Icons;
import com.kstenschke.copypastestack.resources.StaticTexts;
import com.kstenschke.copypastestack.TagManager;
import com.kstenschke.copypastestack.ToolWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PopupItems extends PopupBase {

    final private JPopupMenu popup;
    final private JList jListItems;

    /**
     * Constructor
     */
    public PopupItems(final ToolWindow toolWindow) {
        this.jListItems = toolWindow.getItemsList();
        this.popup      = new JPopupMenu();

            // Paste
        JMenuItem menuItemPaste = getJMenuItem(StaticTexts.POPUP_ITEMS_PASTE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.pasteItems();
            }
        }, Icons.ICON_PASTE);
        this.popup.add(menuItemPaste);

            // Re-copy
        JMenuItem menuItemRecopy = getJMenuItem(StaticTexts.POPUP_ITEMS_RECOPY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.copySelectedItems();
            }
        }, Icons.ICON_COPY);
        this.popup.add(menuItemRecopy);

        this.popup.addSeparator();

            // Delete
        JMenuItem menuItemDelete = getJMenuItem(StaticTexts.POPUP_ITEMS_DELETE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolWindow.removeItemsFromStack(e);
            }
        }, Icons.ICON_DELETE);
        this.popup.add(menuItemDelete);

        this.popup.addSeparator();

            // Color tags
        JMenuItem menuItemTagYellow = getJMenuItem(StaticTexts.INFO_TAG_YELLOW, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TagManager.tagSelected(toolWindow.getForm(), ListCellRendererCopyPasteStack.idColorYellow);
            }
        }, Icons.ICON_TAG_YELLOW);
        this.popup.add(menuItemTagYellow);

        JMenuItem menuItemTagGreen = getJMenuItem(StaticTexts.INFO_TAG_GREEN, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TagManager.tagSelected(toolWindow.getForm(), ListCellRendererCopyPasteStack.idColorGreen);
            }
        }, Icons.ICON_TAG_GREEN);
        this.popup.add(menuItemTagGreen);

        JMenuItem menuItemTagRed = getJMenuItem(StaticTexts.INFO_TAG_RED, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TagManager.tagSelected(toolWindow.getForm(), ListCellRendererCopyPasteStack.idColorRed);
            }
        }, Icons.ICON_TAG_RED);
        this.popup.add(menuItemTagRed);

        JMenuItem menuItemTagRemove = getJMenuItem(StaticTexts.INFO_TAG_REMOVE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TagManager.tagSelected(toolWindow.getForm(), ListCellRendererCopyPasteStack.idColorNone);
            }
        }, Icons.ICON_TAG_DELETE);
        this.popup.add(menuItemTagRemove);

        this.popup.addSeparator();

        JMenuItem menuItemSelectAll = getJMenuItem(StaticTexts.POPUP_SELECT_ALL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JList list = toolWindow.getForm().listClipItems;
                int end = list.getModel().getSize() - 1;
                if (end >= 0) {
                    list.setSelectionInterval(0, end);
                }
            }
        }, null);
        this.popup.add(menuItemSelectAll);
    }

    /**
     * @return  PopupListener
     */
    public PopupListener getPopupListener() {
        return new PopupListener();
    }



    /**
     * PopupListener
     */
    class PopupListener extends MouseAdapter {
        /**
         * @param   e
         */
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        /**
         * @param   e
         */
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        /**
         * @param   e
         */
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger() ) {
                boolean isAnySelected  = getIndexSelected() > 0;
                if( isAnySelected ) {
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
    }

    /**
     * @return  Integer
     */
    Integer getIndexSelected() {
        return jListItems.getSelectedIndex();
    }

}