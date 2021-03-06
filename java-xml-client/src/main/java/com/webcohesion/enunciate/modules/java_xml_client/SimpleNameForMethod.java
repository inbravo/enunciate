package com.webcohesion.enunciate.modules.java_xml_client;

import com.webcohesion.enunciate.api.datatype.DataTypeReference;
import com.webcohesion.enunciate.api.resources.Entity;
import com.webcohesion.enunciate.api.resources.MediaTypeDescriptor;
import com.webcohesion.enunciate.modules.jaxb.EnunciateJaxbContext;
import com.webcohesion.enunciate.modules.jaxb.api.impl.DataTypeReferenceImpl;
import com.webcohesion.enunciate.modules.jaxb.model.types.XmlClassType;
import com.webcohesion.enunciate.modules.jaxb.model.types.XmlType;
import com.webcohesion.enunciate.util.freemarker.ClientClassnameForMethod;
import com.webcohesion.enunciate.util.freemarker.SimpleNameWithParamsMethod;
import freemarker.template.TemplateModelException;

import java.util.List;

/**
 * @author Ryan Heaton
 */
public class SimpleNameForMethod extends SimpleNameWithParamsMethod {

  public SimpleNameForMethod(ClientClassnameForMethod typeConversion) {
    super(typeConversion);
  }

  @Override
  public String simpleNameFor(Object unwrapped, boolean noParams) throws TemplateModelException {
    if (unwrapped instanceof Entity) {
      List<? extends MediaTypeDescriptor> mediaTypes = ((Entity) unwrapped).getMediaTypes();
      for (MediaTypeDescriptor mediaType : mediaTypes) {
        if (EnunciateJaxbContext.SYNTAX_LABEL.equals(mediaType.getSyntax())) {
          DataTypeReference dataType = mediaType.getDataType();
          if (dataType instanceof DataTypeReferenceImpl) {
            XmlType xmlType = ((DataTypeReferenceImpl) dataType).getXmlType();
            if (xmlType instanceof XmlClassType) {
              unwrapped = ((XmlClassType) xmlType).getTypeDefinition();
            }
          }
        }
      }
    }

    if (unwrapped instanceof Entity) {
      return "Object";
    }

    return super.simpleNameFor(unwrapped, noParams);
  }
}
