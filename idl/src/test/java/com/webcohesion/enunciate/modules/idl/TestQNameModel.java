/*
 * Copyright 2006-2008 Web Cohesion
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webcohesion.enunciate.modules.idl;

import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import junit.framework.TestCase;

import javax.xml.namespace.QName;
import java.util.HashMap;

/**
 * @author Ryan Heaton
 */
public class TestQNameModel extends TestCase {

  /**
   * Tests the string representation of the qname model.
   */
  public void testGetAsString() throws Exception {
    HashMap<String, String> namespacePrefixes = new HashMap<String, String>();
    namespacePrefixes.put("urn:testGetAsString", "tgas");
    QNameModel model = new QNameModel(new QName("urn:testGetAsString", "element"), new BeansWrapperBuilder(Configuration.getVersion()).build(), namespacePrefixes);
    assertEquals("tgas:element", model.getAsString());
  }

}
