/*
 * Copyright 2013 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.resource.converter;

import java.io.File;

import javax.inject.Inject;

import org.jboss.forge.container.services.Exported;
import org.jboss.forge.convert.BaseConverter;
import org.jboss.forge.resource.FileResource;
import org.jboss.forge.resource.Resource;
import org.jboss.forge.resource.ResourceFactory;

/**
 * Converts a {@link File} object to a {@link Resource}
 * 
 * @author <a href="mailto:ggastald@redhat.com">George Gastaldi</a>
 * 
 */
@Exported
@SuppressWarnings("rawtypes")
public class FileResourceConverter extends BaseConverter<File, FileResource>
{
   private final ResourceFactory resourceFactory;

   @Inject
   public FileResourceConverter(ResourceFactory resourceFactory)
   {
      super(File.class, FileResource.class);
      this.resourceFactory = resourceFactory;
   }

   @Override
   public FileResource<?> convert(File source)
   {
      return (FileResource<?>) resourceFactory.create(source);
   }
}