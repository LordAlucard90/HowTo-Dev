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
- [Lok](#log)
- [Experimental](#experimental)

---

## Introduction

[Project Lombok](https://projectlombok.org/) is a powerful way to auto generate code using simple annotations.

All the available annotations can be found [here](https://projectlombok.org/features/all) or in the [javadoc](https://projectlombok.org/api/lombok/package-summary.html).

Some annotations can be placed both on the class, on the filed or on the method.

An example of these annotation usage can be also found [here](https://github.com/LordAlucard90/HowTo-Test/blob/master/lombok/readme.md)

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

For this annotation can be specified:
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
- Use getters to retrieve the value:`doNotUseGetters`, default false (are used)
- Print only some fields: `onlyExplicitlyIncluded`, default false
- Call the super to string: `callSuper`, default false 
- Exclude some fields: `exclude`, default {}
- Define the filed: `of`, default {} (= "all")

```java
@ToString
class ToStringBaseClass {
    static String staticString = "static";
    String baseString = "base";
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


## EqualsAndHashCode
TBD

## Constructors
TBD

## Data And Value
TBD

## Builder
TBD

## SneakyThrows
TBD

## Synchronized
TBD

## With
TBD

## Log
TBD

## Experimental
TBD

