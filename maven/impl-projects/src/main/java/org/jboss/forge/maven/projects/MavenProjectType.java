/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.maven.projects;

import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.maven.projects.facets.MavenDependencyFacet;
import org.jboss.forge.maven.projects.facets.MavenMetadataFacet;
import org.jboss.forge.maven.projects.facets.MavenPackagingFacet;
import org.jboss.forge.maven.projects.facets.MavenResourceFacet;
import org.jboss.forge.projects.ProjectFacet;
import org.jboss.forge.projects.ProjectType;
import org.jboss.forge.ui.wizard.UIWizardStep;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 * 
 */
public class MavenProjectType implements ProjectType
{
   @Override
   public String getType()
   {
      return "Maven";
   }

   @Override
   public Class<? extends UIWizardStep> getSetupFlow()
   {
      return null;
   }

   @Override
   public Iterable<Class<? extends ProjectFacet>> getRequiredFacets()
   {
      List<Class<? extends ProjectFacet>> result = new ArrayList<Class<? extends ProjectFacet>>();
      result.add(MavenFacetImpl.class);
      result.add(MavenMetadataFacet.class);
      result.add(MavenPackagingFacet.class);
      result.add(MavenDependencyFacet.class);
      result.add(MavenResourceFacet.class);
      return result;
   }
}