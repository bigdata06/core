/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.facets;

/**
 * A base convenience {@link Facet} abstract class.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>, <a
 *         href="http://community.jboss.org/people/kenfinni">Ken Finnigan</a>
 */
public abstract class BaseFacet<FACETED extends Faceted> implements Facet<FACETED>
{
   protected FACETED origin;

   protected BaseFacet(FACETED origin)
   {
      if (origin == null)
         throw new IllegalArgumentException("Origin must not be null");

      this.origin = origin;
   }

   @Override
   public FACETED getOrigin()
   {
      return this.origin;
   }

   @Override
   public boolean uninstall()
   {
      return false;
   }

   /**
    * Facet instances are the same if they are registered to the same project.
    */
   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = (prime * result) + ((origin == null) ? 0 : origin.hashCode());
      return result;
   }

   @Override
   public boolean equals(final Object obj)
   {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      BaseFacet<?> other = (BaseFacet<?>) obj;
      if (origin == null)
      {
         if (other.origin != null)
            return false;
      }
      else if (!origin.equals(other.origin))
         return false;
      return true;
   }

}