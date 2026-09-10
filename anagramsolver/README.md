# Countdown Letters Solver

A Python solver for the Countdown letters game, created for a Theory of Algorithms project. Given nine letters with at least three vowels and four consonants, it searches a word list for the longest valid anagrams.

## Run on Windows

Install Python 3 and ensure `python` is available on your PATH. Keep the word list used by the solver in the project folder; the original external word list is not included in this archive.

From PowerShell in this project's `anagramsolver` folder:

```powershell
python solver.py
```

Enter a nine-letter conundrum when prompted. The input must contain only letters, at least three vowels, and at least four consonants.

## Supporting Scripts

```powershell
python preproc.py
python compare.py
```

`preproc.py` contains word-list preparation helpers. `compare.py` runs repeated timing comparisons and displays a graph; it requires the plotting dependencies used by that script.

## Project Layout

- `solver.py` — Validates input, filters candidate words, and searches for anagrams.
- `preproc.py` — Preprocessing helpers for the dictionary.
- `compare.py` — Repeated timing comparison.
- `extras/` — Earlier scraping, filtering, and dictionary-cleaning scripts.

The solver was designed for words between three and nine letters and treats the dictionary as an input dataset rather than a complete Oxford English Dictionary.
