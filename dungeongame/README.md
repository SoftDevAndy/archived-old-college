# Dungeon Game

A Java game project from my second year of college. It was built to practise object-oriented design, Java libraries, and game development.

## Run on Windows

Install a Java Development Kit (JDK) and ensure `java` and `javac` are available on your PATH.

Open PowerShell in this project's `dungeongame` folder and compile the source:

```powershell
javac -d out src/game_package/*.java
```

Start the game:

```powershell
java -cp "out;src" game_package.RunMe
```

Keep the `src/Images`, `src/MonsterImages`, and `src/Music` folders beside the compiled classes so the game can load its assets. The project also includes `MapCopy.txt` and `PlayerStats.txt` as data files.

## Project Layout

- `src/game_package/` — Game logic, entities, UI, and entry point.
- `src/Images/` — UI and environment images.
- `src/MonsterImages/` — Monster artwork.
- `src/Music/` — MIDI music.
- `MapCopy.txt` — Map data.
- `PlayerStats.txt` — Player data.
