# Escape the Maze

A Java dungeon game in which the player navigates a maze, collects food and weapons, and searches for the exit. The project also experiments with monster pathfinding, fuzzy logic for hunger, and several traversal algorithms.

## Controls

| Key | Action |
| --- | --- |
| H | Open the hints screen |
| W, A, S, D | Move the player |
| E | Consume food |
| M | Toggle music |
| , | Cycle weapons |
| / | Cycle food |
| Z | Show the full map |

## Features

- Hint and win screens.
- Zoomed-out map view.
- Inventory, food, and weapons.
- Monster movement and combat systems.
- A* search, beam search, and best-first search implementations.
- Fuzzy-logic hunger model using `fcl/hunger.fcl`.

## Run on Windows

Install a Java Development Kit (JDK) and ensure `java` and `javac` are available on your PATH.

The project includes `libs/jFuzzyLogic_2.0.6.jar`. Include it on the classpath when compiling and running from PowerShell in this project's `escapethemaze` folder:

```powershell
javac -cp "libs\jFuzzyLogic_2.0.6.jar" -d out (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp "out;libs\jFuzzyLogic_2.0.6.jar;Images" ie.gmit.ai.GameRunner
```

Keep the `Images`, `src/Sound`, `fcl`, and `libs` folders available at runtime. Compatibility with current Java versions has not been verified.

## Project Layout

- `src/ie/gmit/ai/` — Game view, maze, monsters, and runner.
- `src/ie/gmit/maze/` — Maze generation algorithms.
- `src/ie/gmit/traverser/` — A*, beam, and best-first traversal.
- `src/ie/gmit/weapon/` and `src/ie/gmit/food/` — Items and interactions.
- `src/ie/gmit/fuzzy/` — Hunger model.
- `Images/` — Game artwork.
- `fcl/hunger.fcl` — Fuzzy-logic rules.
