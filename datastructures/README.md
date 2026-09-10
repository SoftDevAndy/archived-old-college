# File Encoding and Data Structures

A Java college project exploring file parsing, code-book generation, text encoding and decoding, serialization, and a basic one-time-pad exercise.

## Run on Windows

Install a Java Development Kit (JDK) and ensure `java` and `javac` are available on your PATH.

Open PowerShell in this project's `datastructures` folder and compile the source:

```powershell
javac -d out src/gmit/*.java
```

Run the main demonstration from the project folder so its text files can be found:

```powershell
java -cp out gmit.TestRunner
```

## Included Data

- `WarPeace.txt` and `Zimmerman.txt` — Source text used for encoding exercises.
- `CodeBook.txt` and `CommonWords.txt` — Code-book and common-word data.
- `Encoded.txt` and `Decoded.txt` — Example encoded and decoded output.
- `Secret.txt` — Serialized-data exercise input.

## Source Code

- `TestRunner.java` — Runs the project demonstrations.
- `CodeBuhk.java` and `DecodeBuhk.java` — Code-book creation and lookup.
- `FileEncoder.java` and `FileDecoder.java` — File encoding and decoding.
- `BookWriter.java` — Writes code-book files.
- `Serializer.java` and `Secret.java` — Serialization exercise.
- `BasicOneTimePass.java` — Basic one-time-pad demonstration.
- `FileParser.java` and `Dupe.java` — File parsing helpers.

The project includes compiled classes under `bin/`; the source under `src/` is the authoritative version for rebuilding.
