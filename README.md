Jest to kopia GeoSMP3-modpack ale zrobione dla ludzi by było łatwiej

WAŻNE: Przed commitowaniem wykonujcie `bin/packwiz refresh` albo was github actions dojedzie (a potem goteusz)

# Instalacja modpacka
Do zarządzania paczką używamy [packwiza](https://packwiz.infra.link/tutorials/creating/getting-started/). Macie gotowe buildy w `bin/packwiz` albo `bin/packwiz.exe` w zależności od systemu.

Wyeksportuj paczkę i załaduj do aplikacji:
`bin/packwiz curseforge export` lub `bin/packwiz modrinth export`

Do Prism Launchera można załadować poprzez komendę (po eksporcie do modrintha):
```bash
prismlauncher -I Geo\ SMP\ 3-1.0.0.mrpack
```

Instalacja na serwer:
```bash
java -jar packwiz-installer-bootstrap.jar -g -s pack.toml
```
`-g` wyłącza GUI
`-s` pobiera mody tylko server-side

instrukcja do installera: https://packwiz.infra.link/tutorials/installing/packwiz-installer/

# Dodawanie modów

- zalecane jest dodawanie przez CLI:
```bash
bin/packwiz modrinth add [URL|slug|search]
bin/packwiz curseforge add [URL|slug|search]
```
- ewentualnie można dodać `jar`y z curseforge, dodając je do `mods/`, a następnie
```bash
bin/packwiz curseforge detect
```
wykryje to automatycznie ID moda i zamieni go w TOML.
- jeśli ktoś używa Prism Launchera to można skopiować plik konfiguracyjny z `mods/.index/modid.pw.toml` (nie zawsze działa, uważajcie z tym)
