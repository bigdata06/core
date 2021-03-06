/**
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.addon.javaee.cdi.ui;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import javax.enterprise.context.NormalScope;
import javax.inject.Inject;
import javax.inject.Scope;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.forge.addon.javaee.ProjectHelper;
import org.jboss.forge.addon.parser.java.facets.JavaSourceFacet;
import org.jboss.forge.addon.parser.java.resources.JavaResource;
import org.jboss.forge.addon.projects.Project;
import org.jboss.forge.addon.ui.controller.CommandController;
import org.jboss.forge.addon.ui.result.Failed;
import org.jboss.forge.addon.ui.result.Result;
import org.jboss.forge.addon.ui.test.UITestHarness;
import org.jboss.forge.arquillian.AddonDependency;
import org.jboss.forge.arquillian.Dependencies;
import org.jboss.forge.arquillian.archive.ForgeArchive;
import org.jboss.forge.furnace.repositories.AddonDependencyEntry;
import org.jboss.forge.parser.java.JavaAnnotation;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * 
 * @author <a href="ggastald@redhat.com">George Gastaldi</a>
 */
@RunWith(Arquillian.class)
public class NewScopeCommandTest
{
   @Deployment
   @Dependencies({
            @AddonDependency(name = "org.jboss.forge.addon:ui"),
            @AddonDependency(name = "org.jboss.forge.addon:ui-test-harness"),
            @AddonDependency(name = "org.jboss.forge.addon:javaee"),
            @AddonDependency(name = "org.jboss.forge.addon:maven")
   })
   public static ForgeArchive getDeployment()
   {
      return ShrinkWrap
               .create(ForgeArchive.class)
               .addClass(ProjectHelper.class)
               .addBeansXML()
               .addAsAddonDependencies(
                        AddonDependencyEntry.create("org.jboss.forge.furnace.container:cdi"),
                        AddonDependencyEntry.create("org.jboss.forge.addon:projects"),
                        AddonDependencyEntry.create("org.jboss.forge.addon:javaee"),
                        AddonDependencyEntry.create("org.jboss.forge.addon:maven"),
                        AddonDependencyEntry.create("org.jboss.forge.addon:ui"),
                        AddonDependencyEntry.create("org.jboss.forge.addon:ui-test-harness")
               );
   }

   @Inject
   private UITestHarness testHarness;

   @Inject
   private ProjectHelper projectHelper;

   @Test
   public void testCreateNewScope() throws Exception
   {
      Project project = projectHelper.createJavaLibraryProject();
      CommandController controller = testHarness.createCommandController(NewScopeCommand.class,
               project.getRootDirectory());
      controller.initialize();
      controller.setValueFor("named", "MyScope");
      controller.setValueFor("targetPackage", "org.jboss.forge.test");
      Assert.assertTrue(controller.isValid());
      Assert.assertTrue(controller.canExecute());
      Result result = controller.execute();
      Assert.assertThat(result, is(not(instanceOf(Failed.class))));

      JavaSourceFacet facet = project.getFacet(JavaSourceFacet.class);
      JavaResource javaResource = facet.getJavaResource("org.jboss.forge.test.MyScope");
      Assert.assertNotNull(javaResource);
      Assert.assertThat(javaResource.getJavaSource(), is(instanceOf(JavaAnnotation.class)));
      JavaAnnotation ann = (JavaAnnotation) javaResource.getJavaSource();
      Assert.assertTrue(ann.hasAnnotation(NormalScope.class));
      Assert.assertFalse(ann.hasAnnotation(Scope.class));
   }

   @Test
   public void testCreateNewPassivatingScope() throws Exception
   {
      Project project = projectHelper.createJavaLibraryProject();
      CommandController controller = testHarness.createCommandController(NewScopeCommand.class,
               project.getRootDirectory());
      controller.initialize();
      controller.setValueFor("named", "MyPassivatingScope");
      controller.setValueFor("targetPackage", "org.jboss.forge.test");
      controller.setValueFor("passivating", Boolean.TRUE);
      Assert.assertTrue(controller.isValid());
      Assert.assertTrue(controller.canExecute());
      Result result = controller.execute();
      Assert.assertThat(result, is(not(instanceOf(Failed.class))));

      JavaSourceFacet facet = project.getFacet(JavaSourceFacet.class);
      JavaResource javaResource = facet.getJavaResource("org.jboss.forge.test.MyPassivatingScope");
      Assert.assertNotNull(javaResource);
      Assert.assertThat(javaResource.getJavaSource(), is(instanceOf(JavaAnnotation.class)));
      JavaAnnotation ann = (JavaAnnotation) javaResource.getJavaSource();
      Assert.assertTrue(ann.hasAnnotation(NormalScope.class));
      Assert.assertFalse(ann.hasAnnotation(Scope.class));
      Assert.assertEquals("true", ann.getAnnotation(NormalScope.class).getLiteralValue("passivating"));
   }

   @Test
   public void testCreateNewPseudoScope() throws Exception
   {
      Project project = projectHelper.createJavaLibraryProject();
      CommandController controller = testHarness.createCommandController(NewScopeCommand.class,
               project.getRootDirectory());
      controller.initialize();
      controller.setValueFor("named", "MyPseudoScope");
      controller.setValueFor("targetPackage", "org.jboss.forge.test");
      controller.setValueFor("pseudo", Boolean.TRUE);
      Assert.assertTrue(controller.isValid());
      Assert.assertTrue(controller.canExecute());
      Result result = controller.execute();
      Assert.assertThat(result, is(not(instanceOf(Failed.class))));

      JavaSourceFacet facet = project.getFacet(JavaSourceFacet.class);
      JavaResource javaResource = facet.getJavaResource("org.jboss.forge.test.MyPseudoScope");
      Assert.assertNotNull(javaResource);
      Assert.assertThat(javaResource.getJavaSource(), is(instanceOf(JavaAnnotation.class)));
      JavaAnnotation ann = (JavaAnnotation) javaResource.getJavaSource();
      Assert.assertTrue(ann.hasAnnotation(Scope.class));
      Assert.assertFalse(ann.hasAnnotation(NormalScope.class));
   }

}
