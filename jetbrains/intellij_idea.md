# **[IntelliJ Idea](https://www.jetbrains.com/idea/)**

Contents :
- [Add Project Module](#add-project-module)
  - New Module
  - From Existing Source
- [Create Multiple Run Configurations](#create-multiple-run-configurations)
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

- **Lombok plugin**
