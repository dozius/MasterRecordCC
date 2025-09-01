/*
 * Copyright 2025 Dan Smith
 *
 * This file is part of Master Record CC.
 *
 * Master Record CC is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * Master Record CC is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with Master Record
 * CC. If not, see <https://www.gnu.org/licenses/>.
 *
 */
package ca.skullzy;

import java.util.UUID;

import com.bitwig.extension.api.PlatformType;
import com.bitwig.extension.controller.AutoDetectionMidiPortNamesList;
import com.bitwig.extension.controller.ControllerExtensionDefinition;
import com.bitwig.extension.controller.api.ControllerHost;

public class MasterRecordCCExtensionDefinition extends ControllerExtensionDefinition
{
  private static final UUID DRIVER_ID = UUID.fromString("e9cf7a1e-6831-4e3d-952c-031287788000");

  public MasterRecordCCExtensionDefinition()
  {
  }

  @Override
  public String getName()
  {
    return "Master Record CC";
  }

  @Override
  public String getAuthor()
  {
    return "Dan Smith";
  }

  @Override
  public String getVersion()
  {
    return "1.0.0";
  }

  @Override
  public UUID getId()
  {
    return DRIVER_ID;
  }

  @Override
  public String getHardwareVendor()
  {
    return "Utilities";
  }

  @Override
  public String getHardwareModel()
  {
    return "Master Record CC";
  }

  @Override
  public int getRequiredAPIVersion()
  {
    return 24;
  }

  @Override
  public int getNumMidiInPorts()
  {
    return 1;
  }

  @Override
  public int getNumMidiOutPorts()
  {
    return 0;
  }

  @Override
  public void listAutoDetectionMidiPortNames(final AutoDetectionMidiPortNamesList list,
                                             final PlatformType platformType)
  {
    if (platformType == PlatformType.WINDOWS) {
      list.add(new String[] {"Input Midi"}, new String[] {});
    }
    else if (platformType == PlatformType.MAC) {
      list.add(new String[] {"Input Midi"}, new String[] {});
    }
    else if (platformType == PlatformType.LINUX) {
      list.add(new String[] {"Input Midi"}, new String[] {});
    }
  }

  @Override
  public MasterRecordCCExtension createInstance(final ControllerHost host)
  {
    return new MasterRecordCCExtension(this, host);
  }
}
