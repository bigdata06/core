/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Eclipse Public License version 1.0, available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.jboss.forge.aesh.commands;

import org.jboss.aesh.console.Console;
import org.jboss.forge.ui.UICommandID;
import org.jboss.forge.ui.base.SimpleUICommandID;

/**
 * @author <a href="mailto:stale.pedersen@jboss.org">Ståle W. Pedersen</a>
 */
public class StopCommand extends ExitCommand
{
   public StopCommand(Console console)
   {
      super(console);
   }

   @Override
   public UICommandID getId()
   {
      return new SimpleUICommandID("stop", "Exit the shell");
   }
}
