# Lombok

## Contents
- [Introduction](#introduction)
- [Variable Declaration (Val And Var)](#variable-declaration-val-and-var)
- [NotNull Method Parameter](#notnull-method-parameter) 
- [CleanUp](#cleanup)
- [Accessors](#accessors)
- [ToString](#tostring)
- [Equals And Hashcode](#equalsandhashcode)
- [Constructors](#constructors)
- [Data And Value](#data-and-value)
- [Builder](#builder)
- [SneakyThrows](#sneakythrows)
- [Synchronized](#synchronized)
- [With](#with)
- [Log](#log)
- [Experimental](#experimental)

---

## Introduction

[Project Lombok](https://projectlombok.org/) is a powerful way to auto generate code using simple annotations.

All the available annotations can be found [here](https://projectlombok.org/features/all) or in the [javadoc](https://projectlombok.org/api/lombok/package-summary.html).

Some annotations can be placed both on the class, on the filed or on the method.

An example of these annotation usage can be also found [here](https://github.com/LordAlucard90/HowTo-Test/tree/master/java/lombok)

**Note:** if a method has already an user defined implementation then any code will be generated. 

---

## Variable Declaration (Val And Var)

In java types definitions can be very long, especially in for loops.
To avoid long declaration can be used `val` or `var`.

Their main difference is the mutability, `val` is not mutable as opposed to `var` that is mutable.

```java
// val examples (immutable)
val someText = "some text";
System.out.println(someText);

List<String> strings = Arrays.asList("a", "b", "c", "d");
for (val aString: strings) {
    System.out.println(aString);
}

// val examples (mutable)
var mutableString = "initial";
System.out.println(mutableString);
mutableString = "changed";
System.out.println(mutableString);

List<String> strings = Arrays.asList("a", "b", "c", "d");
for (var aString: strings) {
    System.out.println(aString);
}
```
For both these annotation can declared how to consider their usage in the configuration file:
- `lombok.val.flagUsage` = [warning | error]
- `lombok.var.flagUsage` = [warning | error]

---

## NotNull Method Parameter 

It is possible to annotate a method parameter as `@NotNull` to generate an automatic null check that throws an exception if it is null.

When the annotation is placed on a constructor then null check is placed after the `super()` or `this()` calls.

```java
class ExampleClass extends Object {
    private String field;

    public ExampleClass(@NonNull String field) {
        super();
        this.field = field;
    }

    public void setfield(@NonNull String field) {
        this.field = field;
    }
}
```

For this annotation can be specified:
- The exception type: `lombok.nonNull.exceptionType` = [NullPointerException | IllegalArgumentException | Assertion].
- The usage policy: `lombok.nonNull.flagUsage` = [warning | error]

---

## CleanUp

TBD

## Accessors

When a field is defined in a class usually are also defined getters and setters resulting in many not so useful lines of code.

'@Getter' and '@Setter' annotation produce the same code in only one line.

The annotations can be placed either on the class and on the field. 

Using the 'AccessLevel' it is possible to hide or exclude specific methods.
The available access levels are:
- `NONE` - disable the getter/setter
- `PRIVATE`
- `MODULE`
- `PACKAGE`
- `PROTECTED`
- `PUPLIC`
```java
@Getter
@Setter
class AccessorsClass {
    String string;
    boolean aBoolean;

    @Setter(AccessLevel.PRIVATE)
    String readOnly;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    String excluded;
}
```

**Note:** on enums setters are not allowed.

It is possible customize the doc adding the description to the filed:
```java
@Getter
@Setter
class AccessorsClass {
  /**
   * Some description.
   * 
   * @param string the setter description
   * @return the getter description
   */
    String string;
}
```

For these annotations can be specified:
- Accessors chain (like builder): `lombok.accessors.chain` = [true | false]
- Remove get/set prefixes: `lombok.accessors.fluent` = [true | false]
- Remove a prefix variable name from the get/set method name: `lombok.accessors.prefix` += <PREFIX>
- Reset boolean field from is to get: `lombok.getter.noIsPrefix` = [true | false]
- Setters usage policy: `lombok.setter.flagUsage` = [warning | error]
- Getters usage policy: `lombok.getter.flagUsage` = [warning | error]
- Annotations copyable from fields to getters/setters: `lombok.copyableAnnotations` = [A list of fully qualified types]

**Note:** the accessors annotation can be defined/overridden by the `@Accessors` annotation.

#### Getter Lazy
TBD

## ToString

This annotation creates automatically an implementation for the `toString` method. 
By default will be included all the non static fields with name and value.

It is possible to include or exclude single field using `@ToString.Include` `@ToString.Exclude`.
The `@ToString.Include` annotation allows to define:
- Print order: `rank`, the order is value descending and definition descending.
- Print Label: `name`, by default the filed name.

The `@ToString` annotation allows to define:
- Print filed names: `includeFieldNames`, default true
- Use getter (if exists) to retrieve the value:`doNotUseGetters`, default false (are used)
- Print only some fields: `onlyExplicitlyIncluded`, default false
- Call the super to string: `callSuper`, default false 
- Exclude some fields: `exclude`, default {}
- Define the fields to use: `of`, default {} (= "all")

```java
@ToString
class ToStringBaseClass {
    static String staticString = "static";
    String baseString = "base";
    @ToString.Exclude
    String excludedString = "excluded";
}

@ToString(onlyExplicitlyIncluded = true)
class ToStringOnlyExplicitClass {
    @ToString.Include
    String includedString = "included";
    String excludedString = "excluded";
}

@ToString(doNotUseGetters = true)
class ToStringUsingGettersClass {
    String baseString = "other";

    public String getBaseString() {
        return "base";
    }
}

@ToString
class ToStringChangeOrderAndNamesClass {
    @ToString.Include(name = "secondString", rank = -1)
    String first = "first";
    @ToString.Include(name = "firstString", rank = 1)
    String second = "second";
}

@ToString(of = {"includedString"})
class ToStringOfClass {
    String includedString = "included";
    String excludedString = "excluded";
}

@ToString(exclude = {"excludedString"})
class ToStringExcludeClass {
    String includedString = "included";
    String excludedString = "excluded";
}

@ToString
class ToStringParentClass {
    String parentString = "parent";
}

@ToString(callSuper = true)
class ToStringChildClass extends ToStringParentClass {
    String childString = "child";
}
```

For this annotation can be specified:
- Print filed names policy: `lombok.toString.includeFieldNames` = [true | false]
- Use getters policy:`lombok.toString.doNotUseGetters` = [true | false]
- Call super policy: `lombok.toString.callSuper` = [call | skip | warn]
- Usage policy: `lombok.toString.flagUsage` = [warning | error]

## EqualsAndHashCode
This annotation creates automatically an implementation for the `equals` and `hasCode` methods. 
By default will be included all the non static fields with name and value.

The `@EqualsAndHashCode` annotation allows to define:
- Use getter (if exists) to retrieve the value:`doNotUseGetters`, default false (are used)
- Use only some fields: `onlyExplicitlyIncluded`, default false
- Call the super method: `callSuper`, default false 
- Exclude some fields: `exclude`, default {}
- Define the fields to use: `of`, default {} (= "all")

```java
@EqualsAndHashCode
class EqualsAndHashCodeBaseClass {
    static String staticString = "static";
    String baseString = "base";
    @EqualsAndHashCode.Exclude
    String excludedString = "excluded";
}

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
class EqualsAndHashCodeOnlyExplicitClass {
    @EqualsAndHashCode.Include
    String fixedString = "included";
    String varyingString;

    public EqualsAndHashCodeOnlyExplicitClass(String varyingString) {
        this.varyingString = varyingString;
    }
}

@EqualsAndHashCode(doNotUseGetters = true)
class EqualsAndHashCodeUsingGettersClass {
    String fixedString = "included";

    public EqualsAndHashCodeUsingGettersClass(String fixedString) {
        this.fixedString = fixedString;
    }

    public String getFixedString() {
        return "fixed";
    }
}

@EqualsAndHashCode(of = {"fixedString"})
class EqualsAndHashCodeOfClass {
    String fixedString = "included";
    String varyingString;

    public EqualsAndHashCodeOfClass(String varyingString) {
        this.varyingString = varyingString;
    }
}

@EqualsAndHashCode(exclude = {"varyingString"})
class EqualsAndHashCodeExcludeClass {
    String fixedString = "included";
    String varyingString;

    public EqualsAndHashCodeExcludeClass(String varyingString) {
        this.varyingString = varyingString;
    }
}

@EqualsAndHashCode
class EqualsAndHashCodeParentClass {
    String parentString = "parent";
}

@EqualsAndHashCode(callSuper = true)
class EqualsAndHashCodeChildClass extends EqualsAndHashCodeParentClass {
    String childString = "child";
}
```

For this annotation can be specified:
- Use getters policy:`lombok.equalsAndHashCode.doNotUseGetters` = [true | false]
- Call super policy: `lombok.equalsAndHashCode.callSuper` = [call | skip | warn]
- Usage policy: `lombok.equalsAndHashCode.flagUsage` = [warning | error]

## Constructors
Are available three different annotation for the constructors: @NoArgsConstructor, @RequiredArgsConstructor, @AllArgsConstructor.

These annotation creates automatically an implementation for the chosen `contructor`. 

For RequiredArgsConstructor by default will be included all the non null and final not initialized fields.

For AllArgsConstructor by default will be included all the fields except for final already initialized.

These annotations allow to define:
- The Visibility: `access`
- The name of the static method used to call the constructor: `staticName`
- The annotations on the constructor: `onConstructor`

```java
@NoArgsConstructor
class NoArgsConstructorClass {
    String string;

    public NoArgsConstructor(String string) {
        this.string = string;
    }
}

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class NoArgsConstructorPrivateClass {
    String string;
}

@NoArgsConstructor(staticName = "of")
class NoArgsConstructorStaticNameClass {
    String string;
}

@RequiredArgsConstructor
class RequiredArgsConstructorClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@RequiredArgsConstructor
class RequiredArgsConstructorNoSetFinalClass {
    final int finalInt = 42;
    @NonNull
    String requiredString;
}

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class RequiredArgsConstructorPrivateClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@RequiredArgsConstructor(staticName = "of")
class RequiredArgsConstructorStaticNameClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@AllArgsConstructor
class AllArgsConstructorClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@AllArgsConstructor
class AllArgsConstructorNoSetFinalClass {
    final int finalInt = 42;
    String requiredString;
}


@AllArgsConstructor(access = AccessLevel.PRIVATE)
class AllArgsConstructorPrivateClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@AllArgsConstructor(staticName = "of")
class AllArgsConstructorStaticNameClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}
```

For these annotations can be specified:
- Usage policy:`lombok.[allArgsConstructor|requiredArgsConstructor|noArgsConstructor|anyConstructor].flagUsage` = [warning | error]
- Call super policy: `lombok.equalsAndHashCode.callSuper` = [call | skip | warn]
- Annotations copyable from fields to getters/setters: `lombok.copyableAnnotations` = [A list of fully qualified types]
- Use `@java.beans.ConstructorProperties` annotation on generated constructors: `lombok.anyConstructor.addConstructorProperties` = [true | false]


## Data And Value 
Data And Value are used to wrap other annotations.

**Data**: 
- `@ToString`
- `@EqualsAndHashCode`
- `@Getter` on all fields
- `@Setter` on all non-final fields
- `@RequiredArgsConstructor`


**Value**: 
- `final` on class
- `private` and `final` on all fields
- `@ToString`
- `@EqualsAndHashCode`
- `@Getter` on all fields
- `@AllArgsConstructor`

```java
@Data
class DataClass {
    final int finalInt;
    @NonNull
    @Setter(AccessLevel.PRIVATE)
    String requiredString;
    @ToString.Exclude
    @Getter(AccessLevel.PRIVATE)
    Object optional;
}

@Data(staticConstructor = "of")
class DataStaticConstructorClass {
    final int finalInt;
    @NonNull
    String requiredString;
    Object optional;
}

@Value
class ValueClass {
    int finalInt = 0;
    @NonNull
    String requiredString;
    @ToString.Exclude
    Object optional;
}

@Value(staticConstructor = "of")
class ValueStaticConstructorClass {
    int finalInt = 0;
    @NonNull
    String requiredString;
    Object optional;
}
```

For these annotations can be specified:
- Usage policy:`lombok.[data|value].flagUsage` = [warning | error]

## Builder
This annotation allows to create a readable way to create a new object using 
'set like' method called between a `builder()` and a `build()` method.

The annotation automatically implements:
- a public static inner class builder
- a set like method for each field on the builder class
- a `builder()` method that returns a new instance of the inner builder class
- a `build()` method on the inner builder class that create a new instance of the annotated class with the given parameters

With this annotation it is possible to define:
- The visibility: `access`
- The builder method name: `builderMethodName`
- The build method name: `buildMethodName`
- The possibility to create a new builder from an instance using as default vales the instance ones: `toBuilder`

It is also possible to define default values using `@Builder.Default` and 
add elements of a list, set or map one by one using `@Singular`.

```java
@Builder
class BuilderBaseClass {
    static String staticString;
    String aString;
    int anInt;
    boolean aBoolean;
}

@Builder(access = AccessLevel.PRIVATE)
class BuilderPrivateClass {
    private String aString;
}

@Builder(builderMethodName = "start", buildMethodName = "end")
class BuilderDifferentMethodsNamesClass {
    private String aString;
}

@Builder(toBuilder = true)
class BuilderToBuilderClass {
    String first;
    String second;
}

@Builder
class BuilderSingularClass {
    List<String> multipleString;
    @Singular("singularStrings")
    List<String> singularStrings;
}

@Builder
class BuilderDefaultClass {
    String string;
    @Builder.Default String stringWithDefault = "default";
}

class BuilderConstructorClass {
    String aString;
    int anInt;

    @Builder
    public BuilderConstructorClass(String aString, int anInt) {
        this.aString = aString;
        this.anInt = anInt;
    }
}

class BuilderMethodClass {
    String aString;
    int anInt;

    private BuilderMethodClass(){}

    @Builder
    public static BuilderMethodClass methodBuilder(String aString, int anInt) {
        BuilderMethodClass obj = new BuilderMethodClass();
        obj.aString = aString;
        obj.anInt = anInt;
        return obj;
    }
}
```
For these annotations can be specified:
- Usage policy:`lombok.builder.flagUsage` = [warning | error]
- Default builder class name:`lombok.builder.className` = 'Builder'
- Automatically use singular annotation:`lombok.singular.auto` = [true | false]

## SneakyThrows
TBD

## Synchronized
TBD

## With
This annotation is used easily create a copy constructor given an instance with only one different field.

With this annotation it is possible to define:
- The visibility: `access`

````java
@With
class WithClass {
    String string;
    int anInt;
    @With(value = AccessLevel.NONE)
    String hidden;
}
````

For these annotations can be specified:
- Usage policy:`lombok.builder.flagUsage` = [warning | error]

## Log
This annotation is used easily instantiate a logger.

Same loggers supported are:
- `CommonsLog`, `Flogger`, `JBossLog`, `Log`, `Log4j`, `Log4j2`, `Slf4j`, `XSlf4j`
- `CustomLog` is used to set a custom logger.

```java
@Slf4j
class LogClass {
    public static void infoLog() {
        log.info("info");
    }

    public static void warningLog() {
        log.warn("warning");
    }

    public static void errorLog() {
        log.error("error");
    }

    public static void debugLog() {
        log.debug("debug");
    }
}
```
For these annotations can be specified:
- Field name:`lombok.fieldName` = 'log'
- If the logger is static:`lombok.log.fieldIsStatic` = [true | false] 
- Custom logger declaration: `lombok.log.custom.declaration` = LoggerType LoggerFactoryType.loggerFactoryMethod(loggerFactoryMethodParams)(loggerFactoryMethodParams)
- Usage policy:`lombok.[logger_name].flagUsage` = [warning | error]

## Experimental

Feature not still stable.

