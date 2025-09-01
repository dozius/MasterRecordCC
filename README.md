# Master Record CC Bitwig Extension

[![Download](https://img.shields.io/github/downloads/dozius/MasterRecordCC/total.svg)](https://github.com/dozius/MasterRecordCC/releases/latest)
[![Donate](https://img.shields.io/badge/donate-paypal-blue.svg)](https://skullzy.ca/tip)

Bitwig extension to start and stop master recording with MIDI CC messages.

Precompiled releases can be found [here](https://github.com/dozius/MasterRecordCC/releases).

## Compiling

### Requirements

- [OpenJDK 21 LTS](https://adoptium.net/)
- [Maven >= 3.9.9](https://maven.apache.org/)

### Build and install

1. Follow the installation instructions for each of the above requirements.
2. Run `mvn install`.

### Debugging

1. Set an environment variable `BITWIG_DEBUG_PORT` to an unused port number.
2. Restart Bitwig.
3. Setup your debugger to connect to the port from step 1.
