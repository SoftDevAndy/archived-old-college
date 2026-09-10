# Word Cloud API

A Java word-cloud application created as a college project to practise object-oriented design and design patterns. It accepts a local text file or URL, removes words from a blacklist, and generates a word-cloud image through either a command-line or GUI interface.

## Run on Windows

Install a Java Development Kit (JDK) and ensure `java` is available on your PATH.

Run the included JAR from PowerShell in this project's `wordcloud` folder:

```powershell
java -jar wordcloud.jar cmd default "https://www.independent.co.uk/us"
```

The command takes three arguments:

```text
java -jar wordcloud.jar <gui|cmd> <blacklist-file-or-default> <word-file-or-url>
```

- `gui` opens the graphical interface; `cmd` prints progress in the console.
- Use `default` to use the internal stop-word list, or provide a path to a blacklist file.
- Provide a local text-file path or URL as the word source.

Example with local files:

```powershell
java -jar wordcloud.jar cmd stopwords.txt warandpeace.txt
```

The program writes a generated word-cloud image to the working folder. Run it from the project folder so the bundled resources under `src/txt` remain available.

## Build with Ant

Apache Ant is required to use the included `build.xml`:

```powershell
ant
```

The build creates compiled classes and a runnable JAR under `dist/`. The existing `wordcloud.jar` is the easiest way to run the archived project.

## Project Layout

- `wordcloud.jar` — Included runnable JAR.
- `src/ie/gmit/sw/` — Java source, UI, parsing, and word-model classes.
- `src/txt/` — Bundled stop-word and sample text files.
- `lib/` — Jsoup, Commons Validator, and JUnit dependencies.
- `build.xml` — Apache Ant build file.
