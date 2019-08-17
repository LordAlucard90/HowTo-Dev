# **[IntelliJ Idea](https://www.jetbrains.com/idea/)**

Contents :
- [Import Maven Project](#import-maven-project)
- [Spring Enable Livereload](#spring-enable-livereload)
- [Create Multiple Run Configurations](#create-multiple-run-configurations)
- [Select Folders Type](#select-folders-type)
- [Plugins](#plugins)


## Import Maven Project

1. `file` > `new` > `Project from Existing Sources`
2. Select unzipped folder
3. `Import project from external model` > `Maven`
4. `next` until finish
7. `build` > `Build Project`
8. now you can run it

---

## Spring Enable Livereload

1. `File` > `Setting` > `Build, Execution, Deployment` > `Compiler`
2. check `Build project automatically`
3. `Help` > `Find Action..`
4. search `registry`
5. check `compiler.automake.allow.when.app.running`

---

## Create Multiple Run Configurations

- `Run` > `Edit Configurations`
- `+` > `Spring Boot`
    - `Name` = Configuration Name
    - `Main Class` = PackageName.ClassName
    - `Override Parameters` > `+`
        - `name` = Parameter Name
        - `value` = New Parameter Value

---

## Select Folders Type

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

- **Lombok plugin**
