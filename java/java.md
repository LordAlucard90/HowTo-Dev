# Configuration

## Contents :

- [Installation](#installation)
- [Change JRE Version](#change-jre-version)
- [Set Java Home](#set-java-home)

## Installation

It is possible to find all the current supported version on [Oracle official Java SE page](https://www.oracle.com/technetwork/java/javase/downloads/index.html).

#### Linux

Go to the official page or type in the terminal:

```bash
# java runtime rnvironment
sudo apt install openjdk-8-jre  # java  8
sudo apt install openjdk-11-jre # java 11

# java development kit
sudo apt install openjdk-8-jdk  # java  8
sudo apt install openjdk-11-jdk # java 11
```

#### Windows

Download the installer from the official page.

## Change JRE Version

#### Linux

Select the JRE version executing

```bash
sudo update-alternatives --config java
```

#### Windows

Change Java Home

## Set Java Home

TBD
