# Assignment 2: The X+Game

## Overview

This Java project implements part of the logic for **The X+Game**, a two-player board game where the objective is to form an **X-shape** or **+shape** with your tiles. This assignment involves designing a dictionary-based hash table, representing board configurations, and evaluating the game state based on the provided rules and recursive game-tree logic.

## Classes Implemented

### 1. `Data.java`
Stores individual board configurations and their associated scores.

- `public Data(String config, int score)` — Constructor to initialize a configuration and its score.
- `public String getConfiguration()` — Returns the stored board configuration.
- `public int getScore()` — Returns the associated score.

### 2. `HashDictionary.java`
Implements a hash table using **separate chaining** to store configurations and their scores.

Implements the `DictionaryADT` interface with:
- `public int put(Data record)` — Inserts a record, returns 1 if collision occurs, 0 otherwise.
- `public void remove(String config)` — Removes the record with the given configuration.
- `public int get(String config)` — Returns the score for the config or -1 if not found.
- `public int numRecords()` — Returns the number of stored records.

**Note:** You are required to implement your own hash function and cannot use Java’s built-in hashing classes.

### 3. `Configurations.java`
Handles board manipulation, move simulation, and win/draw detection.

Key methods:
- `public HashDictionary createDictionary()` — Returns a new hash dictionary.
- `public int repeatedConfiguration(HashDictionary dict)` — Checks if current config exists in the dictionary.
- `public void addConfiguration(HashDictionary dict, int score)` — Adds current config and score to dictionary.
- `public void savePlay(int row, int col, char symbol)` — Saves a move to the board.
- `public boolean squareIsEmpty(int row, int col)` — Checks if a board cell is empty.
- `public boolean wins(char symbol)` — Checks for an X or + win of required length.
- `public boolean isDraw()` — Checks for draw condition.
- `public int evalBoard()` — Evaluates board: COMPUTER_WINS = 3, UNDECIDED = 1, DRAW = 2, HUMAN_WINS = 0.

## Game Description

- Players take turns placing tiles on an `n x n` board.
- Human uses `'X'`, computer uses `'O'`.
- To win, a player must form an X-shape or +shape with at least `k` tiles.
- The computer uses a recursive tree-based algorithm to evaluate plays.

## Scoring System

- `0`: Human wins
- `1`: Undecided
- `2`: Draw
- `3`: Computer wins

## Hash Table Requirements

- Use polynomial rolling hash or similar custom hash function.
- Handle collisions using separate chaining with `LinkedList`.
- Dictionary must support storing and retrieving board configurations efficiently.

## How to Run

This project is intended to be run using the `Play.java` class (provided separately on OWL). You must compile all `.java` files and run:

```bash
java Play <board size> <length to win> <max recursion level>
```

Example:

```bash
java Play 4 5 2
```

## Testing

Two sets of tests are expected:
- Functionality of the `HashDictionary` using `TestDict.java`.
- Game logic using `Play.java`.

## Evaluation

- Total: 20 marks
  - HashDictionary & Configurations: 8 marks
  - Code implementation & style: 8 marks
  - Compilation & correctness: 4 marks

## Submission Notes

- Submit only your `.java` files (no folders or compressed files).
- Submit via OWL before the due date.

## License

This project is part of Western University’s CS 2210a — Data Structures and Algorithms coursework. For academic use only.
