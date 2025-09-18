# VW Digital:Hub - Backend Technical Challenge

**Author:** Mathis Paturle

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Project Requirements & Assumptions](#project-requirements--assumptions)
3. [Architecture & Design Decisions](#architecture--design-decisions)
4. [Domain Model](#domain-model)
5. [Application Layer](#application-layer)
6. [Infrastructure Layer](#infrastructure-layer)
7. [CLI Usage](#cli-usage)
8. [Running Tests](#running-tests)
9. [Technical Stack](#technical-stack)
10. [Improvements / Next Steps](#improvements--next-steps)
11. [Contact](#contact)

---

## Project Overview

This project implements a solution to control autonomous cleaning robots for the Volkswagen Wolfsburg Factory.

**Key Requirements:**

- A rectangular workspace (factory floor) divided into a grid.
- Robots with initial positions and orientations (N, E, S, W).
- Robots can execute instructions:
    - `L` → turn 90° left
    - `R` → turn 90° right
    - `M` → move forward one grid unit
- Robots operate sequentially.
- Output the final position and orientation of each robot.

---

## Project Requirements & Assumptions

- **Input format:**
    1. First line → upper-right coordinates of workspace
    2. For each robot:
        - Line 1 → initial position (`x y orientation`)
        - Line 2 → instructions (`L`, `R`, `M`)
- **Output format:** `x y orientation` for each robot, in deployment order.
- Robots cannot move outside workspace boundaries. Attempting to do so throws an exception.
- Each robot must have a corresponding instruction set.
- The project targets **Kotlin**, using **Gradle** as build system.
- Hexagonal architecture and DDD tactical patterns are applied.
- Assumptions:
    - Robots operate sequentially
    - The grid is empty (no obstacles)
    - Input strictly follows the format described in the test
    - Java 17+ is installed and available

---

## Architecture & Technical Design Decisions

- Language: **Kotlin** (mainly used in VW projects)
- Build system: **Gradle** with Kotlin DSL
- **Hexagonal Architecture:**
    - **Domain Layer:** Encapsulates business rules (`Robot`, `Workspace`, `Position`, `Orientation`)
    - **Application Layer:** `RobotController` orchestrates domain operations
    - **Infrastructure Layer:** CLI adapters handle input/output
- **Rich Domain Model:**
    - Domain objects encapsulate their own behavior
    - Reduces the need for service classes and centralizes business logic
- **Separation of Concerns:**
    - Domain and application are independent from I/O
    - Makes code testable and maintainable

---

## Domain Model

### **Orientation (Value Object)**
- Enum representing cardinal directions: `N`, `E`, `S`, `W`.
- Methods:
    - `left()` → rotate 90° left
    - `right()` → rotate 90° right
- Fully tested for single and full rotations.

### **Position (Value Object)**
- Holds `x`, `y` coordinates and `orientation`.
- `move()` returns a new `Position` one unit forward in the current orientation.

### **Robot**
- Encapsulates `Position`.
- `executeInstructions()` processes a string of `L`, `R`, `M` commands.
- Throws exception on unknown commands.

### **Workspace**
- Defines boundaries (`0..maxX`, `0..maxY`).
- Holds robots and validates positions.
- Throws exception if a robot is deployed outside bounds.

---

## Application Layer

### **RobotController**
- Deploys robots sequentially and executes instructions.
- Returns final positions in the required output format.
- Validates that each robot has a corresponding instruction set.

---

## Infrastructure Layer

### **CLIInputAdapter**
- Reads workspace size and robot data from console.
- Handles input validation and formatting.
- Uses `readln()` / `readlnOrNull()` (Kotlin 1.9+) for modern input handling.

### **CLIOutputAdapter**
- Prints the final positions of robots to console.
- Isolated from domain logic for testability.

---

## CLI Usage

Build the fat JAR (includes all dependencies):

```bash
./gradlew clean fatJar
```
Run the program:

```bash
java -jar build/libs/vw-challenge-1.0.jar
```
⚠️ Running the fat JAR requires a Java runtime (JDK or JRE) installed and accessible via the java command.

Example Input
```bash
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

Expected Output
```bash
1 3 N
5 1 E
```

## Running Tests
Unit tests ensure correctness of the domain logic:

```bash
./gradlew test
```

- Covers Orientation, Position, Robot logic
- Handles edge cases (moving outside boundaries, invalid commands)

## Technical Stack
- Language: Kotlin
- Build System: Gradle (Kotlin DSL)
- Architecture: Hexagonal / Domain-Driven Design
- Testing: Kotlin Test (JUnit)
- Packaging: Fat JAR for standalone execution

## Improvements / Next Steps
- Obstacle Handling: Support dynamic obstacles and pathfinding.
- Multiple Robots in Parallel: Concurrent robot movements with collision avoidance.
- Enhanced Input Validation: User-friendly error handling for invalid commands.
- Persistence and Logging: Store robot states and movement logs.
- Automated Testing Improvements: CLI integration tests, additional edge cases.
- Visualization: Terminal or web-based visualization of the grid.

## Contact
- Author: Mathis Paturle
- Email: mathispaturle@gmail.com
