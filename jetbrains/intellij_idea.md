# **[IntelliJ Idea](https://www.jetbrains.com/idea/)**

## Contents :

- [Add Project Module](#add-project-module)
  - New Module
  - From Existing Source
- [Add Java JDK](#add-java-jdk)
  - Using JB Plugin
  - From Other Source
- [Configure Android SDK](#configure-android-sdk)
- [Create Multiple Run Configurations](#create-multiple-run-configurations)
- [Docker](#docker)
- [Enable Livereload](#enable-livereload)
- [Import Project](#import-project)
  - Maven
- [Set Folders Type](#set-folders-type)
- [Plugins](#plugins)

---

## Add Project Module

Useful do use an unique project for different services.

#### New Module

1. `file` > `new` > `Module`
2. Select module type:
  - Module SDK
  - Module type
  - Optional plugins
3. `next`
4. Insert module info:
  - `Module name` - the name displayed in **[...]**
  - `Content root` - the location of the module's files
  - `Module file location` - the location `.iml` file
5. `finish`

#### From Existing Source

TBD

---

## Add Java JDK

#### Using JB Plugin

1. `Help` > `Find Action..`
2. search `Plugins`
3. in `Marketplace` search `JB SDK Bintray Downloader`
4. `install` > `restart ide`
5. `Help` > `Find Action..`
6. search `JB SDK Bintray Downloader`
7. select a **jbsdk** (choose the sdk version with the highest **u** version and correct architecture)
8. `download` > `install` > `exit` >`automatic restart`
9. `file` > `Project Structure`
0. `SDKS` > `+` > `JDK` or `Project` > `new` > `JDK`
1. depending on OS select sdk folder:
  - Linux `~/.<intellij_version>/config/jdks/<installed_version>`
  - Windows `~\<intellij_version>\config\jdks\<installed_version>
2. `ok`


#### From Other Source

1. `file` > `Project Structure`
2. `SDKS` > `+` > `JDK` or `Project` > `new` > `JDK`
3. select the installation folder
4. `ok`

---

## Configure Android SDK

1. Download [Android Studio Command Line Tools](https://developer.android.com/studio#command-tools)
2. Extract to `~/android-sdk`
3. Add `~/android-sdk/tools/bin` to **PATH**
4. Download the latest [SDK Platform](https://developer.android.com/studio/releases/platforms)

  ```bash
  # version
  sdkmanager --version

  # update manager
  sdkmanager --update

  # see all packages
  sdkmanager --list

  # download android platform
  sdkmanager "platforms;android-<PLATFORM_VERSION>"

  # download build-tools
  sdkmanager "build-tools;<BUILD_TOOLS_VERSION>"

  # download extras
  sdkmanager "extras;google;m2repository"
  sdkmanager "extras;android;m2repository"

  # see installed SDKs
  avdmanager list target
  ```
5. Add the Android SDk
  - `file` > `Project Structure`
  - `SDKS` > `+` > `Android SDK` or `Project` > `new` > `Android SDK`
  - select the installation folder (should be `~/android-sdk`)
  - select **Android SDK** and **Java JDK** versions
  - `ok`
6. Ensure that **Android Support** plugin is installed and activated

---
## Create Multiple Run Configurations

Useful for multiple project profiles, or to run more instance of the same service at different ports.

- `Run` > `Edit Configurations`
- `+` > `Spring Boot`
- `Name` = Configuration Name
- `Main Class` = PackageName.ClassName
- `Override Parameters` > `+`
- `name` = Parameter Name
- `value` = New Parameter Value

---

## Docker

TBD

---

## Enable Livereload

Useful for Spring Dev Tools

1. `File` > `Setting` > `Build, Execution, Deployment` > `Compiler`
2. check `Build project automatically`
3. `Help` > `Find Action..`
4. search `registry`
5. check `compiler.automake.allow.when.app.running`

---

## Import Project

####  Maven

1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish
7. `build` > `Build Project`
8. now you can run it

---

## Set Folders Type

Useful to add test and resources folders.

- `File` > `Project Structure`
- `Modules`
    - Select Folder > Click on Type:
        - `Sources`
        - `Tests`
        - `Resources`
        - `Test Resources`
        - `Executed`

---

## Plugins

Some useful plugins for java

- **Lombok plugin** - automatic code generator
- **JB SDK Bintray Downloader** - Java SDK downloader
