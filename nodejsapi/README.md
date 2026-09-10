# Galway Semantic Web API

A Node.js and JavaScript project created for a fourth-year Semantic Web module. It combines Galway tourism and recreation GeoJSON datasets in a local PouchDB database and exposes an HTTP API with a browser-based information page.

## Data Sources

The project includes local copies of datasets covering Galway attractions, parks, scenic routes, structures, and recreation areas. The API can display the original files and the records stored in the local database.

## Run on Windows

Install Node.js and ensure `node` and `npm` are available on your PATH.

Open PowerShell in the `Project` folder:

```powershell
cd Project
npm install
node runme.js
```

Open `http://127.0.0.1:8000/` in a browser. The server serves the project pages and initializes the local `semantic_pouchDB` database.

## API Routes

The information page documents the available routes. The main route groups include:

- Original GeoJSON files for attractions, parks, scenic routes, structures, and recreation data.
- Reading all database entries and counting them.
- Adding, updating, and deleting database entries.
- Clearing and rebuilding the local database.

Deleting the database through the API requires restarting `runme.js` so the database can be initialized again.

## Project Layout

- `Project/runme.js` — Express server, routing, and database initialization.
- `Project/package.json` — Node.js dependencies and metadata.
- `Project/data/` — Local GeoJSON datasets.
- `Project/semantic_pouchDB/` — Local PouchDB data.
- `Project/js/googlemaps.js` — Google Maps integration.
- `Project/index.html` — API information page.

## Dependencies

The project uses Express, PouchDB, GeoJSON, and supporting Node.js modules listed in `Project/package.json`. The original Google Maps integration may require a valid API key and current browser support.
