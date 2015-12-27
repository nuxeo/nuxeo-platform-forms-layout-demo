/*
 * (C) Copyright 2015 Nuxeo SA (http://nuxeo.com/) and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     Anahide Tchertchian
 */
package org.nuxeo.ftest.formsLayoutDemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.nuxeo.functionaltests.AbstractTest;
import org.nuxeo.functionaltests.Locator;
import org.nuxeo.functionaltests.formsLayoutDemo.page.HomePage;
import org.nuxeo.functionaltests.formsLayoutDemo.page.Page;
import org.openqa.selenium.By;

/**
 * @since 7.4
 */
public class ITAggregateWidgetTest extends AbstractTest {

    protected void navigateTo(String pageId) {
        driver.get(HomePage.URL);
        get(HomePage.URL + pageId, Page.class);
    }

    protected void checkNoError() {
        Locator.waitForTextNotPresent(driver.findElement(By.xpath("//html")), "ERROR");
    }

    protected void checkLabel(String forValue, String label) {
        assertEquals(label, driver.findElement(By.xpath("//label[@for='" + forValue + "']")).getText());
    }

    @Test
    public void testSelectManyCheckboxDirectoryAggregateWidget() {
        navigateTo("selectManyCheckboxDirectoryAggregateWidget");
        checkNoError();
        String prefix1 = "selectManyCheckboxDirectoryAggregate_edit_form:nxl_selectManyCheckboxDirectoryAggregate:nxw_widget:";
        checkLabel(prefix1 + "0", "Eric Cartman (10)");
        checkLabel(prefix1 + "1", "Stan Marsh (5)");
        checkLabel(prefix1 + "2", "Kyle Broflovski (2)");
        String prefix2 = "selectManyCheckboxDirectoryAggregateLocalized_edit_form:nxl_selectManyCheckboxDirectoryAggregateLocalized:nxw_widget_1:";
        checkLabel(prefix2 + "0", "Australia/Oceania (10)");
        checkLabel(prefix2 + "1", "Antarctica (5)");
        checkLabel(prefix2 + "2", "Europe (2)");
        String prefix3 = "selectManyCheckboxDirectoryAggregateL10N_edit_form:nxl_selectManyCheckboxDirectoryAggregateL10N:nxw_widget_2:";
        checkLabel(prefix3 + "0", "Oceania (10)");
        checkLabel(prefix3 + "1", "Antarctica (5)");
        checkLabel(prefix3 + "2", "Europe (2)");
    }

    @Test
    public void testSelectManyListboxDirectoryAggregateWidget() {
        navigateTo("selectManyListboxDirectoryAggregateWidget");
        checkNoError();
        assertEquals(
                "Eric Cartman (10)\nStan Marsh (5)\nKyle Broflovski (2)",
                driver.findElement(
                        By.xpath("//form[@id='selectManyListboxDirectoryAggregate_edit_form']//table//tr/td[2]")).getText());
        assertEquals(
                "Australia/Oceania (10)\nAntarctica (5)\nEurope (2)",
                driver.findElement(
                        By.xpath("//form[@id='selectManyListboxDirectoryAggregateLocalized_edit_form']//table//tr/td[2]")).getText());
        assertEquals(
                "Oceania/Australia (10)\nAntarctica (5)\nEurope/France (2)",
                driver.findElement(
                        By.xpath("//form[@id='selectManyListboxDirectoryAggregateL10N_edit_form']//table//tr/td[2]")).getText());
    }

    @Test
    public void testSelectManyCheckboxAggregateWidget() {
        navigateTo("selectManyCheckboxAggregateWidget");
        checkNoError();
        String prefix1 = "selectManyCheckboxAggregate_edit_form:nxl_selectManyCheckboxAggregate:nxw_widget:";
        checkLabel(prefix1 + "0", "eric (10)");
        checkLabel(prefix1 + "1", "stan (5)");
        checkLabel(prefix1 + "2", "kyle (2)");
        String prefix2 = "selectManyCheckboxAggregateOptions_edit_form:nxl_selectManyCheckboxAggregateOptions:nxw_widget_1:";
        checkLabel(prefix2 + "0", "Eric Cartman (10)");
        checkLabel(prefix2 + "1", "Stan Marsh (5)");
        checkLabel(prefix2 + "2", "Kyle Broflovski (2)");
    }

    @Test
    public void testSelectManyListboxAggregateWidget() {
        navigateTo("selectManyListboxAggregateWidget");
        checkNoError();
        assertEquals(
                "eric (10)\nstan (5)\nkyle (2)",
                driver.findElement(By.xpath("//form[@id='selectManyListboxAggregate_edit_form']//table//tr/td[2]")).getText());
    }

}
