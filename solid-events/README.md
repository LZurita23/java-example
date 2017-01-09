# SOLID Demo Application

- S = extract methods, then extract classes
- O = create interfaces and inject them
- L = create some classes for the processing of events, have some mutate and others not
- I = think about another importer, and it needs different config properties
- D = remove config properties - NOTE about how these all kind of work together

## Description

Since there are tests passing, you can move to DRY and SOLID.

### DRY

Clean up the EventProcessor to remove obvious duplication.

### Single Responsibility

First extract some methods, then extract some classes to do things like:

- Read the JSON file
- Read the database and return JSON
- Make the transformations
- Output to a table or to JSON
- _consider_ making the import/export one class, so that Interface Segregation works better

### Open Closed

- Inject all the classes from SRP
- Also turn the event formatter into their own classes
  - NOTE that in real life those would likely be much more complex :)

### Liskov

- One of the event formatter classes mutates - which breaks a contract

### Interface Segregation

- DB and File should implement an interface.  But intentionally add more methods to it than necessary.
- Then make them smaller ones (or none at all??)

### DI

- Remove filename config to a separate class, maybe with PropertySource
