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

import com.bitwig.extension.api.util.midi.ShortMidiMessage;
import com.bitwig.extension.callback.ShortMidiMessageReceivedCallback;
import com.bitwig.extension.controller.api.Action;
import com.bitwig.extension.controller.api.Application;
import com.bitwig.extension.controller.api.ControllerHost;
import com.bitwig.extension.controller.api.MidiIn;
import com.bitwig.extension.controller.api.Preferences;
import com.bitwig.extension.controller.api.SettableRangedValue;
import com.bitwig.extension.controller.ControllerExtension;

public class MasterRecordCCExtension extends ControllerExtension
{
  private MidiIn midiIn;
  private int channel;
  private int startCC;
  private int stopCC;
  private Action startMasterRecording;
  private Action stopMasterRecording;

  protected MasterRecordCCExtension(final MasterRecordCCExtensionDefinition definition,
                                    final ControllerHost host)
  {
    super(definition, host);
  }

  @Override
  public void init()
  {
    final ControllerHost host = getHost();
    final Application app = host.createApplication();

    startMasterRecording = app.getAction("start_master_recording");
    stopMasterRecording = app.getAction("stop_master_recording");

    loadPreferences();

    midiIn = host.getMidiInPort(0);
    midiIn.setMidiCallback((ShortMidiMessageReceivedCallback) msg -> handleMIDI(msg));
  }

  @Override
  public void exit()
  {
  }

  @Override
  public void flush()
  {
  }

  /** Loads the extension preferences and sets up observers to allow for interactive updates. */
  private void loadPreferences()
  {
    final Preferences preferences = getHost().getPreferences();

    final SettableRangedValue channelPref = preferences.getNumberSetting("Channel", "Options", 1,
                                                                         16, 1, null, 1);

    channel = (int) channelPref.getRaw();
    channelPref.addRawValueObserver((value) -> {
      channel = (int) value;
    });

    final SettableRangedValue startCCPref = preferences.getNumberSetting("Start CC", "Options", 0,
                                                                         127, 1, null, 0);

    startCC = (int) startCCPref.getRaw();
    startCCPref.addRawValueObserver((value) -> {
      startCC = (int) value;
    });

    final SettableRangedValue stopCCPref = preferences.getNumberSetting("Stop CC", "Options", 0,
                                                                        127, 1, null, 1);

    stopCC = (int) stopCCPref.getRaw();
    stopCCPref.addRawValueObserver((value) -> {
      stopCC = (int) value;
    });
  }

  private void handleMIDI(ShortMidiMessage msg)
  {
    if (msg.getChannel() != (channel - 1)) {
      return;
    }

    if (!msg.isControlChange()) {
      return;
    }

    if (msg.getData1() == startCC && msg.getData2() > 0) {
      startMasterRecording.invoke();
    }
    else if (msg.getData1() == stopCC && msg.getData2() > 0) {
      stopMasterRecording.invoke();
    }
  }
}
