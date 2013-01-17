/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.jboss.forge.ui;

/**
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public final class ResultSuccess extends Result
{
   ResultSuccess(String message)
   {
      super(message);
   }

   ResultSuccess(Class<? extends UICommand> command)
   {
      super(command);
   }

   ResultSuccess(Class<? extends UICommand> command, String message)
   {
      super(command, message);
   }
}
