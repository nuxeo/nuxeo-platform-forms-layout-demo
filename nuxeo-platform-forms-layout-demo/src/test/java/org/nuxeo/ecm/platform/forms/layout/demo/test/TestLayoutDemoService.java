/*
 * (C) Copyright 2010 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Anahide Tchertchian
 */
package org.nuxeo.ecm.platform.forms.layout.demo.test;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import org.nuxeo.ecm.platform.forms.layout.demo.service.DemoLayout;
import org.nuxeo.ecm.platform.forms.layout.demo.service.DemoWidgetType;
import org.nuxeo.ecm.platform.forms.layout.demo.service.LayoutDemoManager;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.runtime.test.NXRuntimeTestCase;

/**
 * @author Anahide Tchertchian
 */
public class TestLayoutDemoService extends NXRuntimeTestCase {

    protected LayoutDemoManager service;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        deployContrib("org.nuxeo.ecm.platform.forms.layout.demo", "OSGI-INF/layout-demo-framework.xml");
        deployContrib("org.nuxeo.ecm.platform.forms.layout.demo", "OSGI-INF/layout-demo-contrib.xml");

        service = Framework.getService(LayoutDemoManager.class);
    }

    public void testService() throws Exception {
        assertNotNull(service);
    }

    @After
    public void tearDown() throws Exception {
        service = null;

        undeployContrib("org.nuxeo.ecm.platform.forms.layout.demo", "OSGI-INF/layout-demo-contrib.xml");
        undeployContrib("org.nuxeo.ecm.platform.forms.layout.demo", "OSGI-INF/layout-demo-framework.xml");

        super.tearDown();
    }

    @Test
    public void testRegistration() throws Exception {
        DemoWidgetType textWidget = service.getWidgetType("text");
        assertEquals("text", textWidget.getName());
        assertEquals("Text", textWidget.getLabel());
        assertEquals("textWidget", textWidget.getViewId());
        assertEquals(LayoutDemoManager.APPLICATION_PATH + "textWidget", textWidget.getUrl());
        assertEquals("standard", textWidget.getCategory());
        List<String> fields = textWidget.getFields();
        assertNotNull(fields);
        assertEquals(1, fields.size());
        assertEquals("lds:textField", fields.get(0));
        Map<String, Serializable> defaultProps = textWidget.getDefaultProperties();
        assertNotNull(defaultProps);
        assertEquals(1, defaultProps.size());
        assertEquals("true", defaultProps.get("rendered"));
        List<DemoLayout> demoLayouts = textWidget.getDemoLayouts();
        assertNotNull(demoLayouts);
        assertEquals(2, demoLayouts.size());
        assertEquals("textWidgetLayout", demoLayouts.get(0).getName());
        assertFalse(demoLayouts.get(0).isListing());
        assertEquals(LayoutDemoManager.APPLICATION_PATH + "sources/OSGI-INF/demo/layout-demo-text-widget.xml",
                demoLayouts.get(0).getSourcePath());
        assertEquals("textWidgetListingLayout", demoLayouts.get(1).getName());
        assertTrue(demoLayouts.get(1).isListing());
        assertEquals(LayoutDemoManager.APPLICATION_PATH + "sources/OSGI-INF/demo/layout-demo-text-listing-widget.xml",
                demoLayouts.get(1).getSourcePath());

        DemoWidgetType textareaWidget = service.getWidgetType("textarea");
        assertEquals("textarea", textareaWidget.getName());
        assertEquals("Textarea", textareaWidget.getLabel());
        assertEquals("textareaWidget", textareaWidget.getViewId());
        assertEquals(LayoutDemoManager.APPLICATION_PATH + "textareaWidget", textareaWidget.getUrl());
        assertEquals("standard", textareaWidget.getCategory());
        demoLayouts = textareaWidget.getDemoLayouts();
        assertNotNull(demoLayouts);
        assertEquals(2, demoLayouts.size());
        assertEquals("textareaWidgetLayout", demoLayouts.get(0).getName());
        assertFalse(demoLayouts.get(0).isListing());
        assertEquals(LayoutDemoManager.APPLICATION_PATH + "sources/OSGI-INF/demo/layout-demo-textarea-widget.xml",
                demoLayouts.get(0).getSourcePath());
        assertEquals("textareaWidgetListingLayout", demoLayouts.get(1).getName());
        assertTrue(demoLayouts.get(1).isListing());
        assertEquals(LayoutDemoManager.APPLICATION_PATH
                + "sources/OSGI-INF/demo/layout-demo-textarea-listing-widget.xml", demoLayouts.get(1).getSourcePath());
    }

    @Test
    public void testGetWidgetTypeByViewId() throws Exception {
        DemoWidgetType textWidget = service.getWidgetTypeByViewId("textWidget");
        assertEquals("text", textWidget.getName());
        DemoWidgetType textareaWidget = service.getWidgetTypeByViewId("textareaWidget");
        assertEquals("textarea", textareaWidget.getName());
    }

    @Test
    public void testGetWidgetTypesByCategory() throws Exception {
        List<DemoWidgetType> widgets = service.getWidgetTypes("standard");
        assertNotNull(widgets);
        assertTrue(widgets.size() >= 2);
        DemoWidgetType textWidget = widgets.get(0);
        assertEquals("text", textWidget.getName());
        DemoWidgetType textareaWidget = widgets.get(1);
        assertEquals("textarea", textareaWidget.getName());
    }

}
