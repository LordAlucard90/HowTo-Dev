# Model Mapper

## Contents
- [Introduction](#introduction)
- [Mappings](#mappings)
- [Custom Mappings](#custom-mappings)
- [Configuration](#configuration)

---

## Introduction
[**ModelMapper**](http://modelmapper.org/) is used to automatically map an object into another.

It determines automatically the properties that belongs to both and
copies the values from the source to the destination.

It is possible to use it stand alone o integrated with other frameworks
like *Spring*, the complete list is available [here](http://modelmapper.org/downloads/).

The complete guide is available [here](http://modelmapper.org/user-manual/configuration).

It is possible to find some code examples [here](https://github.com/LordAlucard90/HowTo-Test/tree/master/java/modelmapper).

---

## Mappings

It is possible both to create a new destination object during the mapping:

```java
ModelMapper modelMapper = new ModelMapper();
DstClass dstObj = modelMapper.map(srcObj, DstClass.class);
```

And update an existing object one:

```java
modelMapper.map(srcObj, dstObj);
```

---

## Custom Mappings

It is possible to define specific mappings when the automatic map is not able to
find the correct mapping between the source and the destination objects:

```java
modelMapper.addMappings(
    new PropertyMap<SrcClass, DstClass>() {
        @Override
        protected void configure() {
            map().setDstFieldA(source.srcField.a);
            map(source.srcFiled.b, destination.dstFieldB);
        }
    }
);
```

---

## Configuration

There are many possible configuration to extend the power of the model mapper.

#### Access Level

This configuration allows to easily access to private or even protected fields. 

```java
modelMapper.getConfiguration()
  .setFieldMatchingEnabled(true)
  .setFieldAccessLevel(AccessLevel.PRIVATE);
```
#### Matching Strategy

It is the strategy used to match the source and destination properties:

- **STANDARD** 
- **LOOSE** 
- **STRICT** 

```java
modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
```

#### Naming Conventions

Defines what kind of property names are eligible for matching:

- **NONE** 
- **JAVABEANS_ACCESSOR** 
- **JAVABEANS_MUTATOR** 

```java
modelMapper.getConfiguration()
  .setSourceNamingConvention(NamingConventions.NONE);
  .setDestinationNamingConvention(NamingConventions.NONE);
```

#### Naming Tokenizer

Defines what type of name tokenizer to use: 

- **CAMEL_CASE** 
- **UNDERSCORE** 

```java
modelMapper.getConfiguration();
  .setSourceNameTokenizer(NameTokenizers.UNDERSCORE);
  .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE);
```
