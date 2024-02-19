### Tests and linter status:
![example workflow](https://github.com/a88217/java-project-78/actions/workflows/workflow.yml/badge.svg)
### Codeclimate Maintainability:
[![Maintainability](https://api.codeclimate.com/v1/badges/f5b26342a172dc6af069/maintainability)](https://codeclimate.com/github/a88217/java-project-78/maintainability)
### Codeclimate TestCoverage:
[![Test Coverage](https://api.codeclimate.com/v1/badges/f5b26342a172dc6af069/test_coverage)](https://codeclimate.com/github/a88217/java-project-78/test_coverage)

# Data Validator

Data Validator is a Java library that helps you to validate some data types. The library supports three data types: Strings, Integers and Maps. To start to work with this just create a new Validator object and choose one of the proposed schemes:

```java
var validator = new Validator();
```

### StringSchema

This schema validate String objects. To choose this schema write:

```java
var schema = validator.string();
```

StringSchema has three validation methods:
* __required()__ - makes the fields required and limits the possibility to use null or empty String.
* __minLength()__ - adds a minimum length constraint for the String. The String must be equal or longer than a specified number. Requires an integer parameter of minimum length.
* __contains()__ - adds a String content constraint. The String must contain a substring passed in the method parameter.

Usage example:

```java
StringSchema schema = validator.string().required().minLength(5).contains("hex");
schema.isValid(""); // false
schema.isValid("hex"); // false
schema.isValid("telxeh"); // false
schema.isValid("hexlet"); // true
```

### NumberSchema

This schema validate Integer objects. To choose this schema write:

```java
var schema = validator.number();
```

NumberSchema has three validation methods:
* __required()__ - makes the fileds required and limits the possibility to use null.
* __positive()__ - adds a constraint to use negative numbers.
* __range()__ - adds a range constraint (inclusive). Requires two integer parameters of the first and the last numbers of range.

Usage example:

```java
NumberSchema schema = validator.number().required().positive().range(2, 7);
schema.isValid(-5); // false
schema.isValid(8); // false
schema.isValid(5); // true
schema.isValid(7); // true
```

### MapSchema

This schema validate Map objects. To schoose this schema write:

```java
var schema = validator.map();
```

MapSchema has three validation methods:
* __required()__ - makes the fields required and limits the possibility to use null.
* __sizeOf()__ - adds a map size constraint. The K-V count must be equal to the number passed in the method parameter.
* __shape()__ - adds constraints to map values. Accepts as a parameter a map of keys whose values need to be validated and schemas that would validate the values.

Usage example:

```java
Map<String, BaseSchema> schemas = new HashMap<>();
schemas.put("name", validator.string().required());
schemas.put("age", validator.number().positive());

MapSchema schema = validator.map().sizeof(2).shape(schemas);

Map<String, Object> human1 = new HashMap<>();
human2.put("name", "");
human2.put("age", null);
schema.isValid(human1); // false

Map<String, Object> human2 = new HashMap<>();
human1.put("name", "Kolya");
human1.put("age", 100);
schema.isValid(human2); // true
```
